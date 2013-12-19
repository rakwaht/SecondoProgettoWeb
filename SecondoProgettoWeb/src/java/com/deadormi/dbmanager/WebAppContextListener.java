/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.dbmanager;

import com.deadormi.dbmanager.DbManager;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;
import java.util.logging.Level;

import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;

import javax.servlet.ServletContextListener;

/**
 *
 * @author Timbu
 */
public class WebAppContextListener implements ServletContextListener {

    DbManager manager;

    public void contextInitialized(ServletContextEvent sce) {
        String dburl = sce.getServletContext().getInitParameter("dburl");

        try {
            manager = new DbManager(dburl);
            sce.getServletContext().setAttribute("dbmanager", manager);
        } catch (Exception ex) {
            Logger.getLogger(WebAppContextListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        manager.close();
    }

}
