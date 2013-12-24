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

/**
 *
 * @author Davide
 */
public class UserController {

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
}
