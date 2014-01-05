/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.servlet;

import com.deadormi.controller.CrewController;
import com.deadormi.controller.Crew_UserController;
import com.deadormi.controller.PostController;
import com.deadormi.entity.Crew;
import com.deadormi.entity.User;
import com.deadormi.util.Parser;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Davide
 */
public class NewPostServlet extends HttpServlet {

    static Logger log = Logger.getLogger(NewPostServlet.class);

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
        response.sendRedirect(request.getServletContext().getContextPath() + "/GroupsServlet");
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
        CrewController cc = new CrewController();
        Crew_UserController cu = new Crew_UserController();
        PostController pc = new PostController();
        String crew_id_string = request.getParameter("id_crew");
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        if (crew_id_string == null || u == null || !Parser.isNumeric(crew_id_string)) {
            /*
             1) stringa id nulla = stringa sbagliata
             2) utente nullo = utente non loggato
             3) stringa che non da un numero = stringa sbagliata
             * 
             * In ogni caso redirect a gruppi
             */
            log.debug("Errore nella richiesta parametri mancanti: \n"
                    + "id_crew = " + crew_id_string + "\n user = " + u.getUsername() + 
                    "\nid isNumeric = " + Parser.isNumeric(crew_id_string));
            response.sendRedirect(request.getServletContext().getContextPath() + "/GroupsServlet");
        } else {
            try {
                Integer crew_id = Integer.parseInt(crew_id_string);
                Crew crew = cc.find_crew_by_id(crew_id);
                if (crew == null) {
                    response.sendRedirect(request.getServletContext().getContextPath() + "/GroupsServlet");
                } else if (cu.crew_belongs_to_user(crew.getId(), u.getId())) {
                    //se l'utente è iscritto alla crew posso processare la richiesta
                    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
                    if (isMultipart) {
                        log.debug("è multipart percio processo");
                        int error = PostController.creaPost(request);
                    }
                    response.sendRedirect(request.getServletContext().getContextPath() + "/ShowGroupServlet?id_group="+crew_id_string);
                } else {
                    log.debug("non puoi creare il post per altri motivi. Es: gruppo pubblico ma non sei iscritto");
                    response.sendRedirect(request.getServletContext().getContextPath() + "/GroupsServlet");
                }
            } catch (SQLException ex) {
                log.warn("Errore sql in new post servlet" + ex);
            } catch (FileUploadException ex) {
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
