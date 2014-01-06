/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.controller;

import com.deadormi.dbmanager.DbManager;
import com.deadormi.entity.Password_Change;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
