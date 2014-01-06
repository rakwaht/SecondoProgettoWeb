/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.servlet;

import com.deadormi.controller.Password_ChangeController;
import com.deadormi.controller.UserController;
import com.deadormi.entity.Password_Change;
import com.deadormi.entity.User;
import com.deadormi.util.CurrentDate;
import com.deadormi.util.HashGenerator;
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
                
                Password_Change pc = new Password_Change();
                Password_ChangeController pcc = new Password_ChangeController();
                HashGenerator hg = new HashGenerator();
                
                // Genera id univoco per la password change
                String psw_change_id = hg.generateId();
                
                // Setto l'oggetto pc
                pc.setId(psw_change_id);
                pc.setUser(u);
                pc.setPassword_date(CurrentDate.getCurrentDate());
                // Salvo nel DB
                pcc.insert(pc);
                
                // Nuovo oggetto Mailer() per invio email
                Mailer m = new Mailer();
                // Oggetto del messaggio
                String subject = "[SecondoProgettoWeb] Recupero password";
                
                // Link di recovery
                String link_path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
                String link_recovery = "<a href='" + link_path + "/secure/password_change.jsp?psw_change_id=" + psw_change_id + "'>qui</a>";
                
                // Testo del messaggio in HTML
                String text = "Ciao " + u.getUsername() + ",<br /><br />"; 
                text += "E' arrivata una richista di recupero password per l'utente " + u.getEmail() + "<br /><br />";
                text += "Se vuoi cambiare la password clicca ";
                text += link_recovery + "<br /><br />";
                text += "Altrimenti ignora il messaggio. <br /><br />";
                text += "A presto,<br /><br />DeaDormi Team";
                
                // Invia email
                if (m.sendEmail(u, subject, text)) {
                    message = "Messaggio inviato correttamente.";
                } else {
                    message = "Errore, messaggio non inviato.";
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
