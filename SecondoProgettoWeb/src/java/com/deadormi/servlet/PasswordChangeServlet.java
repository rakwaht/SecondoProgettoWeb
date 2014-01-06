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
import com.deadormi.util.Md5;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author francesco
 */
public class PasswordChangeServlet extends HttpServlet {

    static Logger log = Logger.getLogger(PasswordChangeServlet.class);

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
            out.println("<title>Servlet PasswordChangeservlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PasswordChangeservlet at " + request.getContextPath() + "</h1>");
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

        String psw_change_id = request.getParameter("psw_change_id");
        String password = request.getParameter("password");
        String password_confirm = request.getParameter("password_confirm");

        Password_ChangeController pcc = new Password_ChangeController();
        String db_date = "";
        Password_Change pc = null;
        String message;

        if (psw_change_id.equals("") || psw_change_id == null) {

            log.debug("------------------------------------");
            message = "La sessione è scaduta. Inserisci di nuovo la email per inviare una nuova richiesta di cambio password.";
            response.sendRedirect("password_recovery.jsp?message_password=" + URLEncoder.encode(message, "UTF-8"));
        } else {

            log.debug("eccccckckckckckckckckckck");

            try {
                pc = pcc.getPassword_ChangeById(psw_change_id);
                db_date = pc.getPassword_date();
            } catch (SQLException ex) {
                log.warn(ex);
            }

            Date date_now = null;
            Date date_db = null;
            try {
                date_now = CurrentDate.toDate(CurrentDate.getCurrentDate());
                date_db = CurrentDate.toDate(db_date);
            } catch (ParseException ex) {
                java.util.logging.Logger.getLogger(PasswordChangeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }


            log.debug("adesso: " + date_now);
            log.debug("dbdb: " + date_db);

            long diff = (date_now.getTime() - date_db.getTime()) / 1000;
            log.debug("diff: " + diff);

            // Se passano più di 180 sec la sessione scade e bye bye
            if (diff > 180) {
                message = "La sessione è scaduta. Inserisci di nuovo la email per inviare una nuova richiesta di cambio password.";
                response.sendRedirect("password_recovery.jsp?message_password=" + URLEncoder.encode(message, "UTF-8"));
            } else {
                UserController uc = new UserController();
                User u = pc.getUser();

                if (password.equals(password_confirm) && !password.trim().equals("") && password.length() >= 5) {
                    try {
                        u = uc.updatePassword(u, Md5.getMD5(password));
                        message = "Password cambiata con successo";
                        response.sendRedirect("password_change.jsp?message_password=" + URLEncoder.encode(message, "UTF-8"));
                    } catch (SQLException ex) {
                        log.warn(ex);
                    }
                } else {
                    message = "Nuova password non valida. Le password devono coincidere ed essere di lunghezza minima 5 caratteri.";
                    response.sendRedirect("password_change.jsp?psw_change_id=" + psw_change_id + "&message_password=" + URLEncoder.encode(message, "UTF-8"));
                }
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
