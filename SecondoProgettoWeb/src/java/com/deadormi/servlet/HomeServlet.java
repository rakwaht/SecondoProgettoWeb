/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.servlet;

import com.deadormi.controller.PostController;
import com.deadormi.entity.Post;
import com.deadormi.entity.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author Davide
 */
public class HomeServlet extends HttpServlet {

    static Logger log = Logger.getLogger(HomeServlet.class);

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
        String next = request.getParameter("next");
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        if (next != null && next.equals("edit")) {
            response.sendRedirect("secure/edit_profile.jsp");
        } else if (next != null && next.equals("groups")) {
            response.sendRedirect("GroupsServlet");
        } else if (u != null) {
            RequestDispatcher rd = request.getRequestDispatcher("secure/home.jsp");
            PostController pc = new PostController();
            List<Post> posts;
            try {
                posts = pc.post_from_last_login(u);
                request.setAttribute("posts", posts);
                rd.forward(request, response);
            } catch (SQLException ex) {
                log.warn(ex);
                response.sendRedirect("login.jsp");
            }

        } else {
            response.sendRedirect("login.jsp");
        }

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
        processRequest(request, response);
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