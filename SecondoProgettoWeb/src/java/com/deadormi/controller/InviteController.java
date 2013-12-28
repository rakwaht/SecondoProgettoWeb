/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.controller;

import com.deadormi.dbmanager.DbManager;
import com.deadormi.entity.Invite;
import com.deadormi.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Timbu
 */
public class InviteController {

    private static Connection con;

    public InviteController() {
        con = DbManager.getConnection();
    }

    public void create_invite(Invite i) throws SQLException {
        PreparedStatement stm = con.prepareStatement("INSERT INTO secondoprogettoweb.INVITE(id_receiver,id_sender,id_crew,invite_enabled) VALUES (?, ?,?,?)");


        try {
            stm.setInt(1, i.getId_receiver());
            stm.setInt(2, i.getId_sender());
            stm.setInt(3, i.getId_crew());
            stm.setBoolean(4, i.isInvite_enabled());
            stm.executeUpdate();

        } finally {
            stm.close();
        }

    }

    public ArrayList<Invite> getInvitesByIdUser(Integer id) throws SQLException {
        ArrayList<Invite> invites = new ArrayList<Invite>();
        
        PreparedStatement stm = con.prepareStatement("SELECT * FROM secondoprogettoweb.INVITE WHERE id_receiver=? AND invite_enabled=? ");
        try {
            stm.setInt(1, id);
            stm.setBoolean(2, true);
            ResultSet rs = stm.executeQuery();
            try {
                while (rs.next()) {
                    Invite i = new Invite();
                    i.setId(rs.getInt("id"));
                    i.setId_crew(rs.getInt("id_crew"));
                    i.setId_receiver(rs.getInt("id_receiver"));
                    i.setId_sender(rs.getInt("id_sender"));
                    i.setInvite_enabled(rs.getBoolean("invite_enabled"));
                    System.out.println(i.getId() + " "  + i.getId_crew());
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

    public void disableInvite(Integer id, Integer id_group) throws SQLException {
       PreparedStatement stm = con.prepareStatement("UPDATE secondoprogettoweb.INVITE SET invite_enabled=?  WHERE id_receiver=? AND id_crew=? AND invite_enabled=? ");
       try {
             stm.setBoolean(1, false);
            stm.setInt(2, id);
            stm.setInt(3, id_group);
            stm.setBoolean(4, true);
             System.out.println("CIAOOOIFWPOIPODGIPFDGIEPIGPOI");
            stm.executeUpdate();
            
        } finally {
            stm.close();
        }
    }
}
