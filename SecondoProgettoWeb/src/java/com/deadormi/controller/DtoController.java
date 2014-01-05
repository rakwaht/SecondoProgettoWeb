/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.controller;

import com.deadormi.dbmanager.DbManager;
import com.deadormi.entity.Crew;
import com.deadormi.entity.Post;
import com.deadormi.entity.User;
import com.deadormi.entitydto.ModeratorDto;
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
public class DtoController {
    
    static Logger log = Logger.getLogger(DtoController.class);
    private static Connection con;

    public DtoController() {
        con = DbManager.getConnection();
    }

    public ArrayList<ModeratorDto> getAllModeratorDto() throws SQLException {
         ArrayList<ModeratorDto> moderatordtos = new ArrayList();
         PreparedStatement stm = con.prepareStatement("SELECT * FROM secondoprogettoweb.CREW");
         ResultSet rs;
         //istanzio i controller necessari
         UserController uc = new UserController();
         PostController pc = new PostController();
        try {
           rs=stm.executeQuery();
           while(rs.next()){
               ModeratorDto md = new ModeratorDto();
               //creo il gruppo e lo aggiungo a md
               Crew c = new Crew();
               User u = uc.findUserbyId(rs.getInt("id_admin"));
               c.setAdmin(u);
               c.setCrew_enabled(rs.getBoolean("crew_enabled"));
               c.setCreation_date(rs.getString("creation_date"));
               c.setCrew_private(rs.getBoolean("crew_private"));
               c.setDescription(rs.getString("description"));
               c.setId(rs.getInt("id"));
               c.setName(rs.getString("name"));
               md.setCrew(c);
               //creo la lista di posts e lo aggiungo al md
               ArrayList<Post> posts = new ArrayList();
               posts = pc.getPostsByCrewId(c.getId());
               md.setPosts(posts);
               //creo lista utenti e la aggiungo al md
               ArrayList<User> users = new ArrayList();
               users = uc.getUsersInGroup(c.getId());
               md.setUsers(users);
               moderatordtos.add(md);
           }
        } finally {
            stm.close();
        }
        return moderatordtos;
    }
    
    
    
}
