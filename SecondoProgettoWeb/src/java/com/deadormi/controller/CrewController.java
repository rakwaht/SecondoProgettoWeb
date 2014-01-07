/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.controller;

import com.deadormi.dbmanager.DbManager;
import com.deadormi.entity.Crew;
import com.deadormi.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author Davide
 */
public class CrewController {

    static Logger log = Logger.getLogger(CrewController.class);
    private static Connection con;

    public CrewController() {
        con = DbManager.getConnection();
    }

    public ArrayList<Crew> findCrewsByUserId(Integer id) throws SQLException {
        PreparedStatement stm = con.prepareStatement("SELECT * FROM secondoprogettoweb.crew C JOIN secondoprogettoweb.crew_user U ON C.id=U.id_crew  WHERE id_user=? AND crew_enabled=true AND crew_user_enabled=true");
        ArrayList<Crew> result = new ArrayList<Crew>();
        UserController uc = new UserController();
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
                    int id_admin = rs.getInt("id_admin");
                    c.setAdmin(uc.findUserbyId(id_admin));
                    c.setName(rs.getString("name"));
                    result.add(c);
                }
            } finally {
                rs.close();
            }
        } finally {
            stm.close();
        }
        return result;
    }

    public ArrayList<Crew> findPublicCrew() throws SQLException {
        PreparedStatement stm = con.prepareStatement("SELECT * FROM secondoprogettoweb.crew WHERE crew_private=false AND crew_enabled=true");
        ArrayList<Crew> result = new ArrayList<Crew>();
        UserController uc = new UserController();
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
                    int id_admin = rs.getInt("id_admin");
                    c.setAdmin(uc.findUserbyId(id_admin));
                    c.setName(rs.getString("name"));
                    result.add(c);
                }
            } finally {
                rs.close();
            }
        } finally {
            stm.close();
        }
        return result;
    }

    public int create_crew(Crew c) throws SQLException {
        Integer id_gruppo = null;
        PreparedStatement stm = con.prepareStatement("INSERT INTO secondoprogettoweb.CREW(id_admin,name,description,crew_private,creation_date,crew_enabled) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        ResultSet generated_keys;
        ArrayList<Crew> result = new ArrayList<Crew>();
        try {
            stm.setInt(1, c.getAdmin().getId());
            stm.setString(2, c.getName());
            stm.setString(3, c.getDescription());
            stm.setBoolean(4, c.isCrew_private());
            stm.setString(5, c.getCreation_date());
            stm.setBoolean(6, c.isCrew_enabled());
            stm.executeUpdate();
            generated_keys = stm.getGeneratedKeys();
            if (generated_keys.next()) {
                //mando gli inviti

                id_gruppo = generated_keys.getInt(1);

            }
        } finally {
            stm.close();
        }
        return id_gruppo;
    }

    public Crew find_crew_by_id(Integer crew_id) throws SQLException {
        PreparedStatement stm = con.prepareStatement("SELECT * FROM secondoprogettoweb.crew WHERE id=? AND crew_enabled=true");
        UserController uc = new UserController();
        try {
            stm.setInt(1, crew_id);
            ResultSet rs = stm.executeQuery();
            try {
                while (rs.next()) {
                    Crew c = new Crew();
                    c.setCreation_date(rs.getString("creation_date"));
                    c.setCrew_enabled(rs.getBoolean("crew_enabled"));
                    c.setCrew_private(rs.getBoolean("crew_private"));
                    c.setDescription(rs.getString("description"));
                    c.setId(rs.getInt("id"));
                    int id_admin = rs.getInt("id_admin");
                    c.setAdmin(uc.findUserbyId(id_admin));
                    c.setName(rs.getString("name"));
                    return c;
                }
            } finally {
                rs.close();
            }
        } finally {
            stm.close();
        }
        return null;
    }

    public void updateCrew(Crew crew) throws SQLException {
        PreparedStatement stm = con.prepareStatement("UPDATE secondoprogettoweb.crew SET name=?,description=?,crew_private=? WHERE id=? ");
        try {
            stm.setString(1, crew.getName());
            stm.setString(2, crew.getDescription());
            stm.setInt(4, crew.getId());
            stm.setBoolean(3, crew.getCrew_private());
            stm.executeUpdate();
        } finally {
            stm.close();
        }


    }

    public ArrayList<Crew> getAllCrews() throws SQLException {
        ArrayList<Crew> crews = new ArrayList();
        PreparedStatement stm = con.prepareStatement("SELECT * FROM secondoprogettoweb.CREW");
        ResultSet rs;
        UserController uc = new UserController();
        try {
            rs = stm.executeQuery();
            while (rs.next()) {
                Crew c = new Crew();
                User u = uc.findUserbyId(rs.getInt("id_admin"));
                c.setAdmin(u);
                c.setCrew_enabled(rs.getBoolean("crew_enabled"));
                c.setCreation_date(rs.getString("creation_date"));
                c.setCrew_private(rs.getBoolean("crew_private"));
                c.setDescription(rs.getString("description"));
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                crews.add(c);

            }
        } finally {
            stm.close();
        }
        return crews;
    }

}
