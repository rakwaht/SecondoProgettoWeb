/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.controller;

import com.deadormi.dbmanager.DbManager;
import com.deadormi.entity.Crew;
import com.deadormi.entity.Crew_User;
import com.deadormi.entity.Invite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Timbu
 */
public class Crew_UserController {
     private static Connection con;

    public Crew_UserController() {
        con = DbManager.getConnection();
    }

    public void create_crew_user(Crew_User cu) throws SQLException {
        PreparedStatement stm = con.prepareStatement("INSERT INTO secondoprogettoweb.CREW_USER(id_user,id_crew,crew_user_enabled) VALUES (?, ?,?)");
        
       
        try {
            stm.setInt(1,cu.getId_user());
            stm.setInt(2,cu.getId_crew());
            stm.setBoolean(3,cu.isCrew_user_enabled());
            stm.executeUpdate();
            
        } finally {
            stm.close();
        }
     
    }

    public boolean crew_belongs_to_user(Integer id_crew, Integer id_user) throws SQLException {
        PreparedStatement stm = con.prepareStatement("SELECT * FROM secondoprogettoweb.crew_user WHERE id_user=? AND id_crew=?  AND crew_user_enabled=true");
        ArrayList<Crew> result = new ArrayList<Crew>();
        try {
            stm.setInt(1, id_user);
            stm.setInt(2, id_crew);
            ResultSet rs = stm.executeQuery();
            try {
                while (rs.next()) {
                    return true;
                }
            } finally {
                rs.close();
            }
        } finally {
            stm.close();
        }
        return false;
    }
}
