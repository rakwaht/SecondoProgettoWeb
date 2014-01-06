/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.servlet;

import com.deadormi.controller.UserController;
import com.deadormi.entity.User;
import com.deadormi.util.Mailer;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
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
public class RecoveryPasswordServlet extends HttpServlet {

    static Logger log = Logger.getLogger(RecoveryPasswordServlet.class);

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
        response.sendRedirect("password_recovery.jsp");
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

        String email = request.getParameter("email");
        UserController uc = new UserController();
        User u = null;
        String message = null  ;
        try {
            u = uc.findUserByEmail(email);
            if (u != null) {
                // Nuovo oggetto Mailer() per invio email
                Mailer m = new Mailer();
                // Oggetto del messaggio
                String subject = "[SecondoProgettoWeb] Recupero password";
                // Testo del messaggio
                String text = "Ciao " + u.getUsername() + ",\n\n" + "Istruzioni per cabiare password...";
                // Invia email
                if (m.sendEmail(u, subject, text)) {
                    message = "Messaggio inviato correttamente.";
                } else {
                    message = "Errore, messaggio non inivato.";
                }

                response.sendRedirect("password_recovery.jsp?message_email=" + URLEncoder.encode(message, "UTF-8"));
            } else {
                message = "Non è stato registrato alcun utente con questa email.";
                response.sendRedirect("password_recovery.jsp?message_email=" + URLEncoder.encode(message, "UTF-8"));
            }
        } catch (SQLException ex) {
            log.error(ex);
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
