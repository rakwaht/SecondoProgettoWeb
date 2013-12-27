/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.controller;

import com.deadormi.dbmanager.DbManager;
import com.deadormi.entity.Crew;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Davide
 */
public class CrewController {

    private static Connection con;

    public CrewController() {
        con = DbManager.getConnection();
    }
    
    public ArrayList<Crew> findCrewsByUserId(Integer id) throws SQLException {
        PreparedStatement stm = con.prepareStatement("SELECT * FROM secondoprogettoweb.crew C JOIN secondoprogettoweb.crew_user U ON C.id=U.id_crew  WHERE id_user=? AND crew_enabled=true AND crew_user_enabled=true");
        ArrayList<Crew> result = new ArrayList<Crew>();
        try {
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            try {
                while (rs.next()) {
                    Crew c = new Crew();
                    c.setCreation_date(rs.getString("creation_date"));
                    c.setCrew_enabled(rs.getBoolean("crew_enabled"));
                    c.setCrew_private(rs.getBoolean("crew_private"));
                    c.setDescription(rs.getString("description"));
                    c.setId(rs.getInt("id"));
                    c.setId_admin(rs.getInt("id_admin"));
                    c.setName(rs.getString("name"));
                    result.add(c);
                }
            } finally {
                rs.close();
            }
        } finally {
            stm.close();
        }
        System.out.println("trovati privati" + result.size());
        return result;
    }

    public ArrayList<Crew> findPublicCrew() throws SQLException {
        PreparedStatement stm = con.prepareStatement("SELECT * FROM secondoprogettoweb.crew WHERE crew_private=false AND crew_enabled=true");
        ArrayList<Crew> result = new ArrayList<Crew>();
        try {
            ResultSet rs = stm.executeQuery();
            try {
                while (rs.next()) {
                    Crew c = new Crew();
                    c.setCreation_date(rs.getString("creation_date"));
                    c.setCrew_enabled(rs.getBoolean("crew_enabled"));
                    c.setCrew_private(rs.getBoolean("crew_private"));
                    c.setDescription(rs.getString("description"));
                    c.setId(rs.getInt("id"));
                    c.setId_admin(rs.getInt("id_admin"));
                    c.setName(rs.getString("name"));
                    result.add(c);
                }
            } finally {
                rs.close();
            }
        } finally {
            stm.close();
        }
        System.out.println("trovati pubblici" + result.size());
        return result;
    }

    public void create_crew(Crew c) throws SQLException {
        PreparedStatement stm = con.prepareStatement("INSERT INTO secondoprogettoweb.CREW(id_admin,name,description,crew_private,creation_date,crew_enabled) VALUES (?,?,?,?,?,?)");
        ArrayList<Crew> result = new ArrayList<Crew>();
        try {
            stm.setInt(1,c.getId_admin());
            stm.setString(2,c.getName());
            stm.setString(3,c.getDescription());
            stm.setBoolean(4, c.isCrew_private());
            stm.setString(5, c.getCreation_date());
            stm.setBoolean(6,c.isCrew_enabled());
            stm.executeUpdate();
            
        } finally {
            stm.close();
        }
   
    }
    
    
    
}
