/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.servlet;

import com.deadormi.controller.CrewController;
import com.deadormi.controller.UserController;
import com.deadormi.entity.Crew;
import com.deadormi.entity.Invite;
import com.deadormi.entity.User;
import com.deadormi.util.Md5;
import com.deadormi.util.Parser;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
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
public class NewGroupServlet extends HttpServlet {

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
            out.println("<title>Servlet NewGroupServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewGroupServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        ArrayList<User> users = null;

        UserController uc = new UserController();

        try {
            users = uc.getAllUsers();
        } catch (SQLException ex) {
            Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher rd = request.getRequestDispatcher("create_group.jsp");
        request.setAttribute("users", users);

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
        User u = (User) session.getAttribute("user");

        CrewController cc = new CrewController();
        //controllo validazione form di registrazione
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String type = request.getParameter("type");
        String[] users = request.getParameterValues("users");
        InviteController ic = new InviteController();
        if (name.trim().equals("") || description.trim().equals("")) {
            //torna a login con messaggio di errore
            String message = "Nome e/o descrizione obbligatorie!";
            response.sendRedirect("create_group.jsp?message_newgroup=" + URLEncoder.encode(message, "UTF-8"));
        } else {
            //ok registro il nuovo utente
            Crew c = new Crew();
            c.setId_admin(u.getId());
            c.setCreation_date("26/07/1992;12:55");
            c.setCrew_enabled(Boolean.TRUE);
            c.setName(name);
            c.setDescription(description);
            if (type.equals("private")) {
                c.setCrew_private(Boolean.TRUE);
            } else {
                c.setCrew_private(Boolean.FALSE);
            }
            try {
                cc.create_crew(c);
                if (users != null) {
                    for (int i = 0; i < users.length; i++) {
                        Invite in = new Invite();
                        in.setId_crew(i);
                        ic.create_invite(in);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (!response.isCommitted()) {
            response.sendRedirect("/SecondoProgettoWeb/GroupsServlet");
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
