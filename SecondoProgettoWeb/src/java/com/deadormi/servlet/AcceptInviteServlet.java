/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.servlet;

import com.deadormi.controller.CrewController;
import com.deadormi.controller.Crew_UserController;
import com.deadormi.controller.InviteController;
import com.deadormi.controller.UserController;
import com.deadormi.entity.Crew;
import com.deadormi.entity.Crew_User;
import com.deadormi.entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
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
 * @author francesco
 */
public class AcceptInviteServlet extends HttpServlet {

    static Logger log = Logger.getLogger(AcceptInviteServlet.class);

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

        UserController uc = new UserController();

        String email_receiver = request.getParameter("e_r");
        Integer id_crew = Integer.parseInt(request.getParameter("id_c"));

        User receiver = null;

        try {
            receiver = uc.findUserByEmail(email_receiver);
        } catch (SQLException ex) {
            log.warn(ex);
        }

        InviteController ic = new InviteController();
        Crew_UserController cuc = new Crew_UserController();
        Crew_User cu = new Crew_User();
        
        cu.setId_crew(id_crew);
        cu.setId_user(receiver.getId());
        cu.setCrew_user_enabled(Boolean.TRUE);

        try {
            ic.disableInvite(receiver.getId(), id_crew);
            cuc.create_crew_user(cu);
        } catch (SQLException ex) {
            log.warn(ex);
        }
        
        CrewController crew_c = new CrewController();
        Crew crew = null;
        try {
            crew = crew_c.find_crew_by_id(id_crew);
        } catch (SQLException ex) {
            log.warn(ex);
        }
        
        String message = "accepted";
        //response.sendRedirect("email_accept_invite.jsp?message=" + URLEncoder.encode(message, "UTF-8"));

        RequestDispatcher rd = request.getRequestDispatcher("email_accept_invite.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
        request.setAttribute("crew", crew);
        rd.forward(request, response);
        
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
