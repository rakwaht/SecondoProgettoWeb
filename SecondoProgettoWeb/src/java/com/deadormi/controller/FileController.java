/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.controller;

import com.deadormi.dbmanager.DbManager;
import com.deadormi.entity.Post_File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Davide
 */
public class FileController {

    static Logger log = Logger.getLogger(FileController.class);
    private static Connection con;

    public FileController() {
        con = DbManager.getConnection();
    }

    public boolean isFile(String trovata, String crew_id_string) throws SQLException {
        PreparedStatement stm = con.prepareStatement("SELECT * FROM secondoprogettoweb.POST P JOIN secondoprogettoweb.POST_FILE F ON P.id=F.id_post WHERE id_crew=? AND name=? AND F.id=?");
        ResultSet rs;
        if (!trovata.contains("-")) {
            return false;
        } else {
            String nome_file = trovata.substring(trovata.indexOf("-") + 1, trovata.length());
            String id_file = trovata.substring(0, trovata.indexOf("-"));
            try {
                Integer.parseInt(id_file);
            } catch (NumberFormatException e) {
                return false;
            }
            // only got here if we didn't return false


            try {
                stm.setInt(1, Integer.parseInt(crew_id_string));
                stm.setString(2, nome_file);
                stm.setInt(3, Integer.parseInt(id_file));
                rs = stm.executeQuery();
                try {
                    if (rs.next()) {
                        return true;
                    }
                } finally {
                    rs.close();
                }
            } finally {
                stm.close();
            }
            return false;
        }
    }

    public Integer salvaFile(String fileName, Integer post_id) throws SQLException {
        PreparedStatement stm = con.prepareStatement("INSERT INTO secondoprogettoweb.POST_FILE (name,id_post) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
        log.debug("salvo file " + fileName);
        try {
            stm.setString(1, fileName);
            stm.setInt(2, post_id);
            stm.executeUpdate();
            ResultSet generated_keys = stm.getGeneratedKeys();
            if (generated_keys.next()) {
                return generated_keys.getInt(1);
            } else {
                return -1;
            }
        } finally {
            stm.close();
        }
    }

    ArrayList<Post_File> getFileByPostId(Integer id) throws SQLException {
        PreparedStatement stm = con.prepareStatement("SELECT * FROM secondoprogettoweb.post_file WHERE id_post=?");
        ArrayList<Post_File> result = new ArrayList<Post_File>();
        try {
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            try {
                while (rs.next()) {
                    Post_File pf = new Post_File();
                    pf.setId(rs.getInt("id"));
                    pf.setId_post(rs.getInt("id_post"));
                    pf.setName(rs.getString("name"));
                    result.add(pf);
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
