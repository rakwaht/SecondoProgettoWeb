/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.controller;

import com.deadormi.dbmanager.DbManager;
import com.deadormi.entity.User;
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
public class UserController {

    static Logger log = Logger.getLogger(UserController.class);
    private static Connection con;

    public UserController() {
        con = DbManager.getConnection();
    }

    public User findUserbyId(int id) throws SQLException {
        PreparedStatement stm = con.prepareStatement("SELECT * FROM secondoprogettoweb.user WHERE id=?");

        User u = new User();
        try {
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            try {
                while (rs.next()) {
                    u.setUsername(rs.getString("username"));
                    u.setPassword(rs.getString("password"));
                    u.setLogin_date(rs.getString("login_date"));
                    u.setId(rs.getInt("id"));
                    u.setEmail(rs.getString("email"));
                    u.setAvatar_name(rs.getString("avatar_name"));
                    u.setModerator(rs.getBoolean("moderator"));
                    return u;
                }
            } finally {
                rs.close();
            }
        } finally {
            stm.close();
        }
        return null;
    }

    public User authenticate(String username, String password) throws SQLException {
        PreparedStatement stm = con.prepareStatement("SELECT * FROM secondoprogettoweb.user WHERE username=? AND password=?");

        User u = new User();
        try {
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            try {
                while (rs.next()) {
                    u.setUsername(rs.getString("username"));
                    u.setPassword(rs.getString("password"));
                    u.setLogin_date(rs.getString("login_date"));
                    u.setId(rs.getInt("id"));
                    u.setEmail(rs.getString("email"));
                    u.setAvatar_name(rs.getString("avatar_name"));
                    u.setModerator(rs.getBoolean("moderator"));
                    return u;
                }
            } finally {
                rs.close();
            }
        } finally {
            stm.close();
        }
        return null;
    }

    public void create_user(User u) throws SQLException {
        PreparedStatement stm = con.prepareStatement("INSERT INTO SECONDOPROGETTOWEB.USER (username,password,avatar_name,email,login_date,moderator) VALUES (?,?,?,?,?,?)");

        try {
            stm.setString(1, u.getUsername());
            stm.setString(2, u.getPassword());
            stm.setString(3, u.getAvatar_name());
            stm.setString(4, u.getEmail());
            stm.setString(5, u.getLogin_date());
            stm.setBoolean(6, u.isModerator());
            stm.executeUpdate();
        } finally {
            stm.close();
        }
    }

    public boolean username_already_exist(String username) throws SQLException {
        PreparedStatement stm = con.prepareStatement("SELECT * FROM secondoprogettoweb.user WHERE username=?");
        try {
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            try {
                if (rs.next()) {
                    return true;
                } else {
                    return false;
                }
            } finally {
                rs.close();
            }
        } finally {
            stm.close();
        }
    }

    public boolean email_already_exist(String email) throws SQLException {
        PreparedStatement stm = con.prepareStatement("SELECT * FROM secondoprogettoweb.user WHERE email=?");
        try {
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            try {
                if (rs.next()) {
                    return true;
                } else {
                    return false;
                }
            } finally {
                rs.close();
            }
        } finally {
            stm.close();
        }
    }

    public ArrayList<User> getAllUsers() throws SQLException {
         PreparedStatement stm = con.prepareStatement("SELECT * FROM secondoprogettoweb.user");
        ArrayList<User> users = new ArrayList();
         try {
            ResultSet rs = stm.executeQuery();
            try {
                while (rs.next()) {
                    User u = new User();
                    u.setId(rs.getInt("id"));
                    u.setAvatar_name(rs.getString("avatar_name"));
                    u.setEmail(rs.getString("email"));
                    u.setLogin_date(rs.getString("login_date"));
                    u.setModerator(rs.getBoolean("moderator"));
                    u.setPassword(rs.getString("password"));
                    u.setUsername(rs.getString("username"));
                    users.add(u);
                } 
            } finally {
                rs.close();
            }
        } finally {
            stm.close();
        }
         return users;
    }

    public ArrayList<User> getUsersInGroup(Integer id_crew) throws SQLException {
         ArrayList<User> users = new ArrayList();
         PreparedStatement stm = con.prepareStatement("SELECT * FROM secondoprogettoweb.crew_user t1 JOIN secondoprogettoweb.user t2 ON t2.id=t1.id_user WHERE id_crew=? AND crew_user_enabled=? ");
         User u;
         try {
            stm.setInt(1, id_crew);
            stm.setBoolean(2, true);
            ResultSet rs = stm.executeQuery();
            try {
                while (rs.next()) {
                    u = new User();
                    u.setId(rs.getInt("id"));
                    u.setAvatar_name(rs.getString("avatar_name"));
                    u.setEmail(rs.getString("email"));
                    u.setLogin_date(rs.getString("login_date"));
                    u.setModerator(rs.getBoolean("moderator"));
                    u.setPassword(rs.getString("password"));
                    u.setUsername(rs.getString("username"));
                    users.add(u);
                }
            } finally {
                rs.close();
            }
        } finally {
            stm.close();
        }
         return users;
    }

    public ArrayList<User> getUserNotInGroup(Integer id_crew) throws SQLException {
        ArrayList<User> users = new ArrayList();
        PreparedStatement stm = con.prepareStatement("select distinct * from secondoprogettoweb.user WHERE id NOT IN (select id_user from secondoprogettoweb.crew_user where id_crew=? AND crew_user_enabled=?) AND id  NOT IN(select id_receiver FROM secondoprogettoweb.invite where invite_enabled=? )");
        User u;
         try {
            stm.setInt(1, id_crew);
            stm.setBoolean(2, true);
            stm.setBoolean(3, true);
            ResultSet rs = stm.executeQuery();
            try {
                while (rs.next()) {
                    u = new User();
                    u.setId(rs.getInt("id"));
                    u.setAvatar_name(rs.getString("avatar_name"));
                    u.setEmail(rs.getString("email"));
                    u.setLogin_date(rs.getString("login_date"));
                    u.setModerator(rs.getBoolean("moderator"));
                    u.setPassword(rs.getString("password"));
                    u.setUsername(rs.getString("username"));
                    users.add(u);
                }
            } finally {
                rs.close();
            }
        } finally {
            stm.close();
        }
         
         return users;
   }

    public ArrayList<User> getUserInvitedToGroup(Integer id_crew) throws SQLException {
          ArrayList<User> users = new ArrayList();
         PreparedStatement stm = con.prepareStatement("SELECT * FROM secondoprogettoweb.invite t1 JOIN secondoprogettoweb.user t2 ON t1.id_receiver=t2.id WHERE id_crew=? AND invite_enabled=? ");
         User u;
         try {
            stm.setInt(1, id_crew);
            stm.setBoolean(2, true);
            ResultSet rs = stm.executeQuery();
            try {
                while (rs.next()) {
                    u = new User();
                    u.setId(rs.getInt("id"));
                    u.setAvatar_name(rs.getString("avatar_name"));
                    u.setEmail(rs.getString("email"));
                    u.setLogin_date(rs.getString("login_date"));
                    u.setModerator(rs.getBoolean("moderator"));
                    u.setPassword(rs.getString("password"));
                    u.setUsername(rs.getString("username"));
                    users.add(u);
                }
            } finally {
                rs.close();
            }
        } finally {
            stm.close();
        }
         return users;
    }
}
