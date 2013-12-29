/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.dbmanager;

import java.io.File;

import javax.servlet.ServletContextEvent;

import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Timbu
 */
public class WebAppContextListener implements ServletContextListener {

    static Logger log = Logger.getLogger(WebAppContextListener.class);
    DbManager manager;

    public void contextInitialized(ServletContextEvent sce) {
        
        //init loggersce
        String homeDir = sce.getServletContext().getRealPath("/");
        File propertiesFile = new File(homeDir, "WEB-INF/log4j.properties");
        PropertyConfigurator.configure(propertiesFile.toString());
        //end init logger
        
        
        String dburl = sce.getServletContext().getInitParameter("dburl");

        try {
            manager = new DbManager(dburl);
            sce.getServletContext().setAttribute("dbmanager", manager);
        } catch (Exception ex) {
            log.warn(ex);
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        manager.close();
    }

}
