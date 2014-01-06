/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.servlet;

import com.deadormi.controller.Password_ChangeController;
import com.deadormi.entity.Password_Change;
import com.deadormi.util.CurrentDate;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author francesco
 */
public class PasswordChangeServlet extends HttpServlet {

    static Logger log = Logger.getLogger(PasswordChangeServlet.class);

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PasswordChangeservlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PasswordChangeservlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String psw_change_id = request.getParameter("psw_change_id");
        String password = request.getParameter("password");
        String password_confirm = request.getParameter("password_confirm");

        String now_date = CurrentDate.getCurrentDate();
        Password_ChangeController pcc = new Password_ChangeController();
        String db_date = "";
        Password_Change pc = null;

        try {
            pc = pcc.getPassword_ChangeById(psw_change_id);
        } catch (SQLException ex) {
            log.warn(ex);
        }

        log.debug("CAZZO");
        


        /* ------------------------------------------------- */

        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PasswordChangeservlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<ul>");
            out.println("<li>psw_change_id: " + psw_change_id + "</li>");
            out.println("<li>id: " + pc.getId() + "</li>");
            out.println("<li>now_date: " + now_date + "</li>");
            out.println("<li>passw: " + password + "</li>");
            out.println("<li>passw_conf: " + password_confirm + "</li>");
            out.println("</ul>");
            out.println("<p>dio canenenenenen</p>");

            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
