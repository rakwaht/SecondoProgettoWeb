/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.controller;

import com.deadormi.dbmanager.DbManager;
import com.deadormi.entity.Invite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author Timbu
 */
public class InviteController {

    static Logger log = Logger.getLogger(InviteController.class);
    private static Connection con;

    public InviteController() {
        con = DbManager.getConnection();
    }

    public void create_invite(Invite i) throws SQLException {
        PreparedStatement stm = con.prepareStatement("SELECT * FROM secondoprogettoweb.invite WHERE id_receiver=? AND id_crew=? AND id_sender=?");
        ResultSet rs;

        try {
            stm.setInt(1, i.getReceiver().getId());
            stm.setInt(2, i.getCrew().getId());
            stm.setInt(3, i.getSender().getId());

            rs = stm.executeQuery();
            if (rs.next()) {
                log.debug("Già presente in db setto true");

                stm = con.prepareStatement("UPDATE secondoprogettoweb.invite SET invite_enabled=? WHERE id_receiver=? AND id_crew=? AND id_sender=?  ");
                stm.setBoolean(1, true);
                stm.setInt(2, i.getReceiver().getId());
                stm.setInt(3, i.getCrew().getId());
                stm.setInt(4, i.getSender().getId());

                stm.executeUpdate();
            } else {
                log.debug("Inserisco nel db");

                stm = con.prepareStatement("INSERT INTO secondoprogettoweb.invite (id_receiver,id_sender,id_crew,invite_enabled) VALUES(?,?,?,?)");
                stm.setInt(1, i.getReceiver().getId());
                stm.setInt(3, i.getCrew().getId());
                stm.setInt(2, i.getSender().getId());
                stm.setBoolean(4, true);

                stm.executeUpdate();
            }
        } finally {
            stm.close();
        }

    }

    public ArrayList<Invite> getInvitesByIdUser(Integer id) throws SQLException {
        ArrayList<Invite> invites = new ArrayList<Invite>();

        CrewController cc = new CrewController();
        UserController uc = new UserController();

        PreparedStatement stm = con.prepareStatement("SELECT * FROM secondoprogettoweb.INVITE WHERE id_receiver=? AND invite_enabled=? ");
        try {
            stm.setInt(1, id);
            stm.setBoolean(2, true);
            ResultSet rs = stm.executeQuery();
            try {
                while (rs.next()) {
                    Invite i = new Invite();
                    i.setId(rs.getInt("id"));
                    i.setCrew(cc.find_crew_by_id(rs.getInt("id_crew")));
                    i.setReceiver(uc.findUserbyId(rs.getInt("id_receiver")));
                    i.setSender(uc.findUserbyId(rs.getInt("id_sender")));
                    i.setInvite_enabled(rs.getBoolean("invite_enabled"));
                    invites.add(i);
                }
            } finally {
                rs.close();
            }
        } finally {
            stm.close();
        }
        return invites;
    }

    public void disableInvite(Integer id_receiver, Integer id_group) throws SQLException {
        PreparedStatement stm = con.prepareStatement("UPDATE secondoprogettoweb.INVITE SET invite_enabled=?  WHERE id_receiver=? AND id_crew=? AND invite_enabled=? ");
        try {
            stm.setBoolean(1, false);
            stm.setInt(2, id_receiver);
            stm.setInt(3, id_group);
            stm.setBoolean(4, true);
            stm.executeUpdate();

        } finally {
            stm.close();
        }
    }
}
