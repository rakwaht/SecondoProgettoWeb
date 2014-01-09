/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.controller;

import com.deadormi.dbmanager.DbManager;
import com.deadormi.entity.Post;
import com.deadormi.entity.User;
import com.deadormi.util.CurrentDate;
import com.deadormi.util.Parser;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Davide
 */
public class PostController {

    static Logger log = Logger.getLogger(PostController.class);
    final static String FILE_RESOURCE_PATH = "/resource/files";
    private static Connection con;

    public PostController() {
        con = DbManager.getConnection();
    }

    public ArrayList<Post> getPostsByCrewId(Integer crew_id) throws SQLException {
        PreparedStatement stm = con.prepareStatement("SELECT * FROM secondoprogettoweb.post WHERE id_crew=? ORDER BY id DESC");
        ArrayList<Post> result = new ArrayList<Post>();
        UserController uc = new UserController();
        FileController fc = new FileController();
        try {
            stm.setInt(1, crew_id);
            ResultSet rs = stm.executeQuery();
            try {
                while (rs.next()) {
                    Post p = new Post();
                    p.setCreation_date(rs.getString("creation_date"));
                    p.setId(rs.getInt("id"));
                    p.setId_crew(rs.getInt("id_crew"));
                    p.setWriter(uc.findUserbyId(rs.getInt("id_writer")));
                    p.setText(rs.getString("text"));
                    p.setFiles(fc.getFileByPostId(p.getId()));
                    result.add(p);
                }
            } finally {
                rs.close();
            }
        } finally {
            stm.close();
        }
        return result;
    }

    public int creaPost(HttpServletRequest request) throws SQLException, FileUploadException {
        log.debug("inizio salvataggio file");
        String crew_id_string = request.getParameter("id_crew");
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        FileController fc = new FileController();
        PreparedStatement stm = con.prepareStatement("INSERT INTO secondoprogettoweb.post (id_writer,id_crew,creation_date,text) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        //estraggo il testo dal form
        String testo = null;
        Integer post_id;
        int maxFileSize = 10 * 1024 * 1024;
        if (request.getContentLength() >= maxFileSize) {
            log.debug("Troppo grande " + request.getContentLength());
            return 1; //file troppo grande
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxFileSize); //max file size 10 mega
        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File("c:\\temp"));
        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        // maximum file size to be uploaded.
        upload.setSizeMax(maxFileSize);
        List fileItems = upload.parseRequest(request);
        // Create a new file upload handler
        Iterator i = fileItems.iterator();
        //preparo le directory
        File file;
        String filePath = request.getServletContext().getRealPath(FILE_RESOURCE_PATH + "/" + crew_id_string);
        File directory = new File(filePath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        //itero gli input
        while (i.hasNext()) {
            FileItem fi = (FileItem) i.next();
            if (fi.isFormField() && fi.getFieldName().equals("testo")) {
                try {
                    testo = fi.getString("UTF-8");
                } catch (UnsupportedEncodingException ex) {
                    log.error(ex);
                }
            }
        }
        log.debug("parso il testo");
        testo = Parser.checkTesto(request, testo, crew_id_string);

        if (testo.trim().equals("")) {
            return 2; // testo vuoto
        } else {    //id_writer,id_crew,creation_date,text
            ResultSet rs;
            try {
                stm.setInt(1, u.getId());
                stm.setInt(2, Integer.parseInt(crew_id_string));
                stm.setString(3, CurrentDate.getCurrentDate());
                stm.setString(4, testo);
                stm.executeUpdate();
                rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    post_id = rs.getInt(1);
                    log.debug("file creato");
                } else {
                    post_id = -1;
                }
            } finally {
                stm.close();
            }
            ListIterator i2 = fileItems.listIterator();
            // Create a new file upload handler
            while (i2.hasNext()) {
                FileItem fi = (FileItem) i2.next();
                if (!fi.isFormField() && fi.getFieldName().equals("file") && fi.getSize() > 0) {
                    // Get the uploaded file parameters
                    String fieldName = fi.getFieldName();
                    String fileName = fi.getName();
                    fileName = fileName.replace('\'', '-');
                    log.debug("Name after check " + fileName);
                    String contentType = fi.getContentType();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();
                    // Write the file
                    Integer file_id = fc.salvaFile(fileName, post_id);
                    file = new File(filePath + "/" + file_id + "-" + fileName);
                    try {
                        fi.write(file);
                    } catch (Exception ex) {
                        log.error(ex);
                    }

                }
            }
        }
        return 0;

    }
}
