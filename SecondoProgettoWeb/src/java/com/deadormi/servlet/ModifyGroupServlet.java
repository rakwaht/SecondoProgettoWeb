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
import com.deadormi.entity.Invite;
import com.deadormi.entity.User;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author Timbu
 */
public class ModifyGroupServlet extends HttpServlet {

    static Logger log = Logger.getLogger(ModifyGroupServlet.class);

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
        ArrayList<User> followers = null; // persone nel gruppo
        ArrayList<User> not_followers = null; //persone non nel gruppo, non invitate
        ArrayList<User> invited = null; // invitate ma ancora da risp
        Crew crew = null;
        CrewController cu = new CrewController();
        UserController uc = new UserController();

        Integer id_crew = Integer.parseInt(request.getParameter("id_crew"));        

        try {
            crew = cu.find_crew_by_id(id_crew);
            followers = uc.getUsersInGroup(id_crew);
            not_followers = uc.getUserNotInGroup(id_crew);
            invited = uc.getUserInvitedToGroup(id_crew);
        } catch (SQLException ex) {
            log.warn(ex);
        }
        for (int i = 0; i < followers.size(); i++) {
            if (followers.get(i).getId().equals(crew.getAdmin().getId())) {
                followers.remove(i);
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher("modify_group.jsp");
        request.setAttribute("followers", followers);
        request.setAttribute("not_followers", not_followers);
        request.setAttribute("invited", invited);
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
        Integer id_crew = Integer.parseInt(request.getParameter("id_crew"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        CrewController cu = new CrewController();
        Crew crew = null;
        String type = request.getParameter("type");
        UserController uc = new UserController();
        Crew_UserController cuc = new Crew_UserController();
        InviteController ic = new InviteController();
        String[] followers = request.getParameterValues("followers");
        String[] not_followers = request.getParameterValues("not_followers");
        
        try {
            crew = cu.find_crew_by_id(id_crew);
        } catch (SQLException ex) {
            log.warn(ex);

        }
        if (followers != null) {
            for (int i = 0; i < followers.length; i++) {
                Integer id_follower = Integer.parseInt(followers[i]);
                try {
                    cuc.removeCrewUserById(id_follower, id_crew);
                } catch (SQLException ex) {
                    log.warn(ex);

                }

            }
        }
        if (not_followers != null) {
            for (int i = 0; i < not_followers.length; i++) {
                Integer id_not_follower = Integer.parseInt(not_followers[i]);
                try {
                    Invite in = new Invite();
                    in.setCrew(crew);
                    in.setReceiver(uc.findUserbyId(id_not_follower));
                    in.setSender(crew.getAdmin());
                    in.setInvite_enabled(Boolean.TRUE);
                    ic.create_invite(in);

                } catch (SQLException ex) {
                    log.warn(ex);
                }

            }
        }
        if (title.trim().equals("") || description.trim().equals("")) {
            //ERROR
            String message = "empty_params";
            response.sendRedirect("ModifyGroupServlet?id_crew=" + crew.getId() + "&message_editgroup=" + URLEncoder.encode(message, "UTF-8"));
        } else {
            crew.setDescription(description);
            crew.setName(title);
                        
            if (crew.isCrew_private() && type.equals("public")) {
                crew.setCrew_private(Boolean.FALSE);
            } else if (!crew.isCrew_private() && type.equals("private")) {
                crew.setCrew_private(Boolean.TRUE);
            }
            
            try {
                cu.updateCrew(crew);
            } catch (SQLException ex) {
                log.warn(ex);
            }
            response.sendRedirect("/SecondoProgettoWeb/ShowGroupServlet?id_crew=" + crew.getId());
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
