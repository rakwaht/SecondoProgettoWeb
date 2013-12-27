/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.servlet;

import com.deadormi.dbmanager.DbManager;
import com.deadormi.entity.Invite;
import com.deadormi.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Timbu
 */
public class InviteController {

    private static Connection con;

    public InviteController() {
        con = DbManager.getConnection();
    }

    void create_invite(Invite i) throws SQLException {
        PreparedStatement stm = con.prepareStatement("INSERT INTO secondoprogettoweb.INVITE(id_receiver,id_sender,id_crew,invite_enabled) VALUES (?, ?,?,?)");
        
       
        try {
            stm.setInt(1,i.getId_receiver());
            stm.setInt(2,i.getId_sender());
            stm.setInt(3,i.getId_crew());
            stm.setBoolean(4,i.isInvite_enabled());
            stm.executeUpdate();
            
        } finally {
            stm.close();
        }
     
    }
}
