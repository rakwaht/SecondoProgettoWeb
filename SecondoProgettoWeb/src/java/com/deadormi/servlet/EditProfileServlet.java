/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.servlet;

import com.deadormi.controller.FileController;
import com.deadormi.controller.UserController;
import com.deadormi.entity.User;
import com.deadormi.util.Md5;
import com.deadormi.util.Parser;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author francesco
 */
@WebServlet(name = "EditProfileServlet", urlPatterns = {"/secure/EditProfileServlet"})
public class EditProfileServlet extends HttpServlet {

    static Logger log = Logger.getLogger(GroupsServlet.class);

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("edit_profile.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");

        String edit = request.getParameter("whatEdit");

        log.debug("edit profile POST with param: " + edit);


        if ("editAvatar".equals(edit)) {
            log.debug("editAvatar");

            FileController fc = new FileController();
            try {
                Integer result = fc.changeAvatar(request);

                String message = "";
                if (result.equals(0)) {
                    message = "changed";
                } else if (result.equals(1)) {
                    message = "too_big";
                } else if (result.equals(2)) {
                    message = "not_image";
                } else if (result.equals(3)) {
                    message = "not_uploaded";
                }

                response.sendRedirect("edit_profile.jsp?message_avatar=" + URLEncoder.encode(message, "UTF-8"));


            } catch (SQLException ex) {
                log.error("Errore avatar: " + ex);
            }

        }

        if ("editName".equals(edit)) {
            log.debug("editName");
            String new_username = request.getParameter("new_username");
            UserController uc = new UserController();
            try {
                if (uc.username_already_exist(new_username)) {
                    //torna a login con messaggio di errore
                    String message = "already_exists";
                    response.sendRedirect("edit_profile.jsp?message_username=" + URLEncoder.encode(message, "UTF-8"));
                } else if (new_username.length() < 4) {
                    String message = "too_short";
                    response.sendRedirect("edit_profile.jsp?message_username=" + URLEncoder.encode(message, "UTF-8"));
                } else if (Parser.isHTML(new_username)) {
                    String message = "html";
                    response.sendRedirect("edit_profile.jsp?message_username=" + URLEncoder.encode(message, "UTF-8"));
                } else {
                    u = uc.updateUsername(u, new_username);
                    String message = "changed";
                    response.sendRedirect("edit_profile.jsp?message_username=" + URLEncoder.encode(message, "UTF-8"));
                }
            } catch (SQLException ex) {
                log.warn(ex);
            }
        }

        if ("editPassword".equals(edit)) {
            log.debug("editPassword");
            String old_password = request.getParameter("old_password");
            String new_password = request.getParameter("new_password");
            String new_password_confirm = request.getParameter("new_password_confirm");
            UserController uc = new UserController();
            String message;
            try {
                if (uc.authenticate(u.getUsername(), Md5.getMD5(old_password)) == null) {
                    message = "old_psw_error";
                    response.sendRedirect("edit_profile.jsp?message_password=" + URLEncoder.encode(message, "UTF-8"));
                } else {
                    if (new_password.equals(new_password_confirm) && !new_password.trim().equals("")) {
                        u = uc.updatePassword(u, Md5.getMD5(new_password));
                        message = "changed";
                        response.sendRedirect("edit_profile.jsp?message_password=" + URLEncoder.encode(message, "UTF-8"));
                    } else {
                        message = "new_psw_error";
                        response.sendRedirect("edit_profile.jsp?message_password=" + URLEncoder.encode(message, "UTF-8"));
                    }

                }
            } catch (SQLException ex) {
                log.warn(ex);

            }
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
