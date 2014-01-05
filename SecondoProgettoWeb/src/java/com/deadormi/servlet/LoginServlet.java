/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.servlet;

import com.deadormi.controller.UserController;
import com.deadormi.entity.User;
import com.deadormi.util.CurrentDate;
import com.deadormi.util.Md5;
import com.deadormi.util.Parser;
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
 * @author Davide
 */
public class LoginServlet extends HttpServlet {

    static Logger log = Logger.getLogger(LoginServlet.class);
    
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
        UserController uc = new UserController();
        User u = null;
        String new_user = request.getParameter("new_user");
        //registrazione
        if (new_user != null && new_user.equals("true")) {
            //controllo validazione form di registrazione
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String password2 = request.getParameter("password-confirm");
            String email = request.getParameter("email");
            Parser parser = new Parser();
            try {
                if(username.trim().equals("") || password.trim().equals("")){
                      //torna a login con messaggio di errore
                    String message = "Username e password obbligatorie!";
                    response.sendRedirect("login.jsp?message_registration=" + URLEncoder.encode(message, "UTF-8"));
                }
                else if (uc.username_already_exist(username)) {
                    //torna a login con messaggio di errore
                    String message = "Username gia esistene!";
                    response.sendRedirect("login.jsp?message_registration=" + URLEncoder.encode(message, "UTF-8"));
                } else if (!password.equals(password2)) {
                    //torna a login con messaggio di errore
                    String message = "Le password devono coincidere !";
                    response.sendRedirect("login.jsp?message_registration=" + URLEncoder.encode(message, "UTF-8"));
                } else if (!parser.isEmail(email)) {
                    //torna a login con messaggio di errore
                    String message = "L'Email deve essere un email valida !";
                    response.sendRedirect("login.jsp?message_registration=" + URLEncoder.encode(message, "UTF-8"));
                }else if (uc.email_already_exist(email)) {
                    //torna a login con messaggio di errore
                    String message = "Email gia registrata!";
                    response.sendRedirect("login.jsp?message_registration=" + URLEncoder.encode(message, "UTF-8"));
                }
                else {
                    //ok registro il nuovo utente
                    u = new User();
                    u.setUsername(username);
                    u.setPassword(Md5.getMD5(password));
                    u.setEmail(email);
                    u.setLogin_date(null); //empty string non ha mai loggato
                    u.setModerator(Boolean.FALSE);
                    try {
                        uc.create_user(u);
                    } catch (SQLException ex) {
                        log.warn(ex);
                    }
                }
            } catch (SQLException ex) {
                log.warn(ex);
            }
        }

        //autenticazione
        try {
            u = uc.authenticate(request.getParameter("username"), Md5.getMD5(request.getParameter("password")));
        } catch (SQLException ex) {
            log.warn(ex);
        }
        if (u != null && !response.isCommitted()) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", u);
            response.sendRedirect("secure/home.jsp");
        } else if(!response.isCommitted()){
            String message = "Username/password non esistente !";
            response.sendRedirect("login.jsp?message_login=" + URLEncoder.encode(message, "UTF-8"));
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
