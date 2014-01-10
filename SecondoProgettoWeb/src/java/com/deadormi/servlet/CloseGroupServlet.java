/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.servlet;

import com.deadormi.controller.CrewController;
import com.deadormi.controller.PostController;
import com.deadormi.entity.Crew;
import com.deadormi.entity.Post;
import com.deadormi.entity.User;
import com.deadormi.util.CurrentDate;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Timbu
 */
public class CloseGroupServlet extends HttpServlet {

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
            out.println("<title>Servlet CloseGroupServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CloseGroupServlet at " + request.getContextPath() + "</h1>");
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
        //settare gruppo chiuso
        //scrive un post di chiusura
        String id_crew = request.getParameter("id_crew");
        CrewController cc = new CrewController();
        PostController pc = new PostController();
        User user = (User)request.getSession().getAttribute("user");
        Crew crew = null;
        Post post = new Post();
        if(id_crew!=null){
            try {
                crew = cc.find_crew_by_id(Integer.parseInt(id_crew));
            } catch (Exception ex) {
                response.sendRedirect("/SecondoProgettoWeb/GroupsServlet");
            }
            crew.setCrew_enabled(false);
            try {
                cc.updateCrew(crew);
            } catch (SQLException ex) {
                Logger.getLogger(CloseGroupServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            post.setCreation_date(CurrentDate.getCurrentDate());
            post.setId_crew(crew.getId());
            post.setText("Questo gruppo è stato chiuso da un amministratore!");
            post.setWriter(user);
            try {
                pc.createCloseGroupPost(post);
            } catch (SQLException ex) {
                Logger.getLogger(CloseGroupServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("/SecondoProgettoWeb/ShowGroupServlet?id_crew="+ crew.getId());
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
