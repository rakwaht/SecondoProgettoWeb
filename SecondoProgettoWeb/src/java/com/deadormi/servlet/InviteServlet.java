/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.servlet;

import com.deadormi.controller.Crew_UserController;
import com.deadormi.controller.InviteController;
import com.deadormi.controller.UserController;
import com.deadormi.entity.Crew_User;
import com.deadormi.entity.Invite;
import com.deadormi.entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * @author Timbu
 */
public class InviteServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        ArrayList<Invite> invites = null;

        InviteController ic = new InviteController();

        try {
            invites = ic.getInvitesByIdUser(u.getId());
            System.out.println(invites.size());
        } catch (SQLException ex) {
            Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher rd = request.getRequestDispatcher("invites.jsp");
        
        request.setAttribute("invites", invites);

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
       HttpSession session = request.getSession();
       InviteController ic = new InviteController();
       Crew_UserController cuc = new Crew_UserController();
       User u = (User) session.getAttribute("user");
       String[] groups = request.getParameterValues("groups");
       if(groups!=null){
           for(int i=0; i<groups.length; i++){
               Integer id_group = Integer.parseInt(groups[i]);
               Crew_User cu = new Crew_User();
               cu.setId_crew(id_group);
               cu.setId_user(u.getId());
               cu.setCrew_user_enabled(Boolean.TRUE);
               try {
                   ic.disableInvite(u.getId(), id_group);
                   cuc.create_crew_user(cu);
               } catch (SQLException ex) {
                   Logger.getLogger(InviteServlet.class.getName()).log(Level.SEVERE, null, ex);
               }
               
           }
       }
       response.sendRedirect("InviteServlet");
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
