/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.controller;

import com.deadormi.dbmanager.DbManager;
import com.deadormi.entity.Password_Change;
import com.deadormi.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 *
 * @author francesco
 */
public class Password_ChangeController {

    static Logger log = Logger.getLogger(Password_ChangeController.class);
    private static Connection con;

    public Password_ChangeController() {
        con = DbManager.getConnection();
    }

    public void insert(Password_Change pc) throws SQLException {
        PreparedStatement stm = con.prepareStatement("INSERT INTO secondoprogettoweb.PASSWORD_CHANGE (id,id_user,password_date) VALUES (?,?,?)");
        try {
            stm.setString(1, pc.getId());
            stm.setInt(2, pc.getUser().getId());
            stm.setString(3, pc.getPassword_date());
            stm.executeUpdate();
        } finally {
            stm.close();
        }
    }

    public Password_Change getPassword_ChangeById(String id) throws SQLException {
        PreparedStatement stm = con.prepareStatement("SELECT * FROM secondoprogettoweb.PASSWORD_CHANGE WHERE id=?");

        Password_Change pc = new Password_Change();
        UserController uc = new UserController();

        try {
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            try {
                while (rs.next()) {
                    pc.setId(rs.getString("id"));
                    User u = uc.findUserbyId(rs.getInt("id_user"));
                    pc.setUser(u);
                    pc.setPassword_date(rs.getString("password_date"));
                    
                    log.debug("id:" + rs.getString("id"));
                    log.debug("psw_date:" + rs.getString("password_date"));
                    log.debug("username: " + u.getUsername());
                    
                    return pc;
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
