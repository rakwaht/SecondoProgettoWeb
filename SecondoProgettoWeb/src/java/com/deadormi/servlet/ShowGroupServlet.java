/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.servlet;

import com.deadormi.controller.CrewController;
import com.deadormi.controller.Crew_UserController;
import com.deadormi.controller.PostController;
import com.deadormi.entity.Crew;
import com.deadormi.entity.Post;
import com.deadormi.entity.User;
import com.deadormi.util.Parser;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class ShowGroupServlet extends HttpServlet {

    static Logger log = Logger.getLogger(ShowGroupServlet.class);

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
        PostController pc = new PostController();
        String crew_id_string = request.getParameter("id_crew");
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");

        if (crew_id_string == null) {
            //se non trovo l'id url sbagliato torno a gruppi
            log.debug("crew_id null");
            response.sendRedirect("GroupsServlet");
        } else {
            if (Parser.isNumeric(crew_id_string)) {
                //se l'id è un numero procedo
                try {
                    Integer crew_id = Integer.parseInt(crew_id_string);
                    Crew crew = cc.find_crew_by_id(crew_id);
                    //testo la crew trovata
                    if (crew == null) {
                        //se non trovo il gruppo torno a gruppi
                        log.debug("caso crew non trovata");
                        response.sendRedirect("GroupsServlet");
                    } else if (!crew.isCrew_private()) {
                        log.debug("gruppo pubblico quindi lo mostro");
                        //se il gruppo è pubblico lo mostro
                        if(u != null && cu.crew_belongs_to_user(crew.getId(), u.getId())){
                            //se sono loggato e ho accettato l'invito a questo gruppo posso anche scrivere
                            request.setAttribute("user_can_edit", true);
                        }
                        ArrayList<Post> posts = pc.getPostsByCrewId(crew_id);
                        RequestDispatcher rd = request.getRequestDispatcher("show_group.jsp");
                        request.setAttribute("crew", crew);
                        request.setAttribute("posts", posts);
                        if (u != null && crew.getAdmin().getId().equals(u.getId())) {
                            request.setAttribute("admin", true);
                        } else {
                            request.setAttribute("admin", false);
                        }
                        rd.forward(request, response);
                    } else if (u == null) {
                        log.debug("utente non loggato quindi non mostro gruppi privati");
                        //se il gruppo è privato e io non sono loggato torno a gruppi
                        response.sendRedirect("GroupsServlet");
                    } else if (cu.crew_belongs_to_user(crew.getId(), u.getId())) {
                        log.debug("utente e crew matchano quindi mostro il gruppo");
                        //se sono loggato e il gruppo è privato ed io sono iscritto lo mostro
                        ArrayList<Post> posts = pc.getPostsByCrewId(crew_id);
                        RequestDispatcher rd = request.getRequestDispatcher("show_group.jsp");
                        request.setAttribute("user_can_edit", true);
                        request.setAttribute("crew", crew);
                        request.setAttribute("posts", posts);
                        if (crew.getAdmin().getId().equals(u.getId())) {
                            request.setAttribute("admin", true);
                        } else {
                            request.setAttribute("admin", false);
                        }
                        rd.forward(request, response);
                    } else {
                        log.debug("else finale non puoi vedere il gruppo");
                        //altrimenti torno a gruppi
                        response.sendRedirect("GroupsServlet");
                    }
                } catch (SQLException ex) {
                    log.warn(ex);
                }
            } else {
                log.debug("Gruppo id non è un numero quindi torno a gruppi");
                //altrimenti torno a gruppi
                response.sendRedirect("GroupsServlet");
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
