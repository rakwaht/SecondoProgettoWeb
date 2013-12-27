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
           
            ResultSet rs = stm.executeQuery();
            try {
                while (rs.next()) {
                   
                    
                }
            } finally {
                rs.close();
            }
        } finally {
            stm.close();
        }
     
    }
}
