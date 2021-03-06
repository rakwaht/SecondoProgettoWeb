/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.dbmanager;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

/**
 *
 * @author Timbu
 */
public class DbManager implements Serializable {

    private static Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    DbManager(String dburl) throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dburl, "root", "password");
        } catch (Exception e) {
            throw e;
        }
    }

    void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
                DriverManager.getConnection("jdbc:mysql:;shutdown=true");
            }
        } catch (Exception e) {
        }
        // This manually deregisters JDBC driver, which prevents Tomcat 7 from complaining about memory leaks wrto this class
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                
            }

        }
    }
    
    public static Connection getConnection(){
        return connection;
    }
}
