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
}
