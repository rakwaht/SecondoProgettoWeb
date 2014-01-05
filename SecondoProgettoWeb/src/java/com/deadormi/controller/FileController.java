/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.controller;

import static com.deadormi.controller.PostController.log;
import com.deadormi.dbmanager.DbManager;
import com.deadormi.entity.Post_File;
import com.deadormi.entity.User;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
public class FileController {

    static Logger log = Logger.getLogger(FileController.class);
    final static String AVATAR_RESOURCE_PATH = "/resource/avatar";
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

    public ArrayList<Post_File> getFileByPostId(Integer id) throws SQLException {
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

    public Integer changeAvatar(HttpServletRequest request) throws SQLException {
        log.debug("Inizio salvataggio avatar");
        
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");

        FileController fc = new FileController();
        String avatar_path = request.getServletContext().getRealPath(AVATAR_RESOURCE_PATH + "/");

        int maxFileSize = 4 * 1024 * 1024;
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxFileSize); //max file size 4 mega
        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File("c:\\temp"));
        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        // maximum file size to be uploaded.
        upload.setSizeMax(maxFileSize);
        List fileItems = null;
        if (request.getContentLength() >= maxFileSize) {
            log.debug("Troppo grande " + request.getContentLength());
            return 1; //file troppo grande
        }
        try {
            fileItems = upload.parseRequest(request);
        } catch (FileUploadException ex) {
            log.error(ex);
        }
        // Create a new file upload handler
        Iterator i = fileItems.iterator();
        //preparo le directory
        File file;
        File directory = new File(avatar_path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        //itero gli input
        while (i.hasNext()) {
            FileItem fi = (FileItem) i.next();
            if (!fi.isFormField() && fi.getFieldName().equals("avatar") && fi.getSize() > 0) {
                // Get the uploaded file parameters
                String fieldName = fi.getFieldName();
                String fileName = fi.getName();
                fileName = fileName.replace("'", "-");
                String contentType = fi.getContentType();
                boolean isInMemory = fi.isInMemory();
                long sizeInBytes = fi.getSize();
                //check if is an image
                String mimeType = request.getServletContext().getMimeType(fileName);
                if (mimeType.startsWith("image")) {

                    UserController uc = new UserController();
                    uc.updateAvatar(u, fileName);

                    file = new File(avatar_path + "/" + u.getId() + "_" + fi.getName());
                    try {
                        fi.write(file);
                    } catch (Exception ex) {
                        log.error(ex);
                    }
                    return 0;// a buon fine
                } else {
                    return 2; // file non è un immagine
                }
            }
        }
        return 3; //nessun campo nel form
    }
}
