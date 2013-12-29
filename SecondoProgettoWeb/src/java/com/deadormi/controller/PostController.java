/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.controller;

import com.deadormi.dbmanager.DbManager;
import com.deadormi.entity.Crew;
import com.deadormi.entity.Post;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author Davide
 */
public class PostController {
    
    static Logger log = Logger.getLogger(PostController.class);
    
    private static Connection con;

    public PostController() {
        con = DbManager.getConnection();
    }

    public ArrayList<Post> getPostsByCrewId(Integer crew_id) throws SQLException {
       PreparedStatement stm = con.prepareStatement("SELECT * FROM secondoprogettoweb.post WHERE id_crew=?");
        ArrayList<Post> result = new ArrayList<Post>();
        try {
            stm.setInt(1, crew_id);
            ResultSet rs = stm.executeQuery();
            try {
                while (rs.next()) {
                    Post p = new Post();
                    p.setCreation_date(rs.getString("creation_date"));
                    p.setId(rs.getInt("id"));
                    p.setId_crew(rs.getInt("id_crew"));
                    p.setId_writer(rs.getInt("id_writer"));
                    p.setText(rs.getString("text"));
                    result.add(p);
                }
            } finally {
                rs.close();
            }
        } finally {
            stm.close();
        }
        return result;
    }
    
    
}
