/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.servlet;

import com.deadormi.controller.CrewController;
import com.deadormi.controller.Crew_UserController;
import com.deadormi.entity.Crew;
import com.deadormi.entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Davide
 */
public class ShowGroupServlet extends HttpServlet {

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
        //istanzio oggetti che mi servono
        CrewController cc = new CrewController();
        Crew_UserController cu = new Crew_UserController();
        Integer crew_id = (Integer) request.getAttribute("id_group");
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");

        if (crew_id == null) {
            //se non trovo l'id url sbagliato torno a gruppi
            response.sendRedirect("GroupsServlet");
        } else {
            try {
                Crew crew = cc.find_crew_by_id(crew_id);
                if (crew == null) {
                    //se non trovo il gruppo torno a gruppi
                    response.sendRedirect("GroupsServlet");
                } else if (!crew.isCrew_private()) {
                    //se il gruppo è pubblico lo mostro
                    RequestDispatcher rd = request.getRequestDispatcher("show_group.jsp");
                    request.setAttribute("crew", crew);
                    rd.forward(request, response);
                } else if (u == null) {
                    //se il gruppo è privato e io non sono loggato torno a gruppi
                    response.sendRedirect("GroupsServlet");
                } else if (cu.crew_belongs_to_user(crew.getId(), u.getId())) {
                    //se sono loggato e il gruppo è privato ed io sono iscritto lo mostro
                    RequestDispatcher rd = request.getRequestDispatcher("show_group.jsp");
                    request.setAttribute("crew", crew);
                    rd.forward(request, response);
                } else {
                    //altrimenti torno a gruppi
                    response.sendRedirect("GroupsServlet");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ShowGroupServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
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
