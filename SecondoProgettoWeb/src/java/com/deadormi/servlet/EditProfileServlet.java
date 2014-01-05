/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.servlet;

import com.deadormi.controller.UserController;
import com.deadormi.entity.User;
import static com.deadormi.servlet.LoginServlet.log;
import com.deadormi.util.Md5;
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

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");

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

        if ("editAvatar".equals(edit)) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            try {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet NewServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>CAMBIA AVATAR</h1>");
                out.println("</body>");
                out.println("</html>");
            } finally {
                out.close();
            }
        }

        if ("editName".equals(edit)) {
            String new_username = request.getParameter("new_username");
            UserController uc = new UserController();
            try {
                if (uc.username_already_exist(new_username)) {
                    //torna a login con messaggio di errore
                    String message = "Username già esistene!";
                    response.sendRedirect("edit_profile.jsp?message_username=" + URLEncoder.encode(message, "UTF-8"));
                } else if (new_username.length() < 4) {
                    String message = "Username troppo corto";
                    response.sendRedirect("edit_profile.jsp?message_username=" + URLEncoder.encode(message, "UTF-8"));
                } else {
                    uc.updateUsername(u, new_username);
                    String message = "Il nuovo username è " + u.getUsername();
                    response.sendRedirect("edit_profile.jsp?message_username=" + URLEncoder.encode(message, "UTF-8"));
                }
            } catch (SQLException ex) {
                log.warn(ex);
            }
        }

        if ("editPassword".equals(edit)) {
            String old_password = request.getParameter("old_password");
            String new_password = request.getParameter("new_password");
            String new_password_confirm = request.getParameter("new_password_confirm");
            UserController uc = new UserController();
            String message;
            try {
                if (uc.authenticate(u.getUsername(), Md5.getMD5(old_password)) == null) {
                    message = "Password vecchia non corretta!";
                    response.sendRedirect("edit_profile.jsp?message_password=" + URLEncoder.encode(message, "UTF-8"));
                } else {
                    if (new_password.equals(new_password_confirm) && !new_password.trim().equals("")) {
                        uc.updatePassword(u, Md5.getMD5(new_password));
                        message = "Password cambiata con successo!";
                        response.sendRedirect("edit_profile.jsp?message_password=" + URLEncoder.encode(message, "UTF-8"));
                    } else {
                        message = "Password nuove non coicidono!";
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
