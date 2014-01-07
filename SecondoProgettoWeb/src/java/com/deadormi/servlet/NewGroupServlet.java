/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.servlet;

import com.deadormi.controller.InviteController;
import com.deadormi.controller.Crew_UserController;
import com.deadormi.controller.CrewController;
import com.deadormi.controller.UserController;
import com.deadormi.entity.Crew;
import com.deadormi.entity.Crew_User;
import com.deadormi.entity.Invite;
import com.deadormi.entity.User;
import com.deadormi.util.CurrentDate;
import com.deadormi.util.Mailer;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
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
 * @author Timbu
 */
public class NewGroupServlet extends HttpServlet {

    static Logger log = Logger.getLogger(NewGroupServlet.class);

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
        ArrayList<User> users = null;

        UserController uc = new UserController();

        try {
            users = uc.getAllUsers();
        } catch (SQLException ex) {
            log.warn(ex);
        }
        RequestDispatcher rd = request.getRequestDispatcher("create_group.jsp");
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(u.getId())) {
                users.remove(i);
            }
        }
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
        UserController uu = new UserController();
        InviteController ic = new InviteController();

        //controllo validazione form di registrazione
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String type = request.getParameter("type");
        String[] users = request.getParameterValues("users");
        Integer id_crew;
        if (name.trim().equals("") || description.trim().equals("")) {
            //torna a login con messaggio di errore
            String message = "Nome e/o descrizione obbligatorie!";
            response.sendRedirect("NewGroupServlet?message_newgroup=" + URLEncoder.encode(message, "UTF-8"));
        } else {
            //ok registro il nuovo gruppo
            Crew c = new Crew();
            c.setAdmin(u);
            c.setCreation_date(CurrentDate.getCurrentDate());
            c.setCrew_enabled(Boolean.TRUE);
            c.setName(name);
            c.setDescription(description);
            if (type.equals("private")) {
                c.setCrew_private(Boolean.TRUE);
            } else {
                c.setCrew_private(Boolean.FALSE);
            }
            try {
                id_crew = cc.create_crew(c);
                if (users != null) {
                    for (int i = 0; i < users.length; i++) {
                        Invite in = new Invite();
                        User r = uu.findUserbyId(Integer.parseInt(users[i]));
                        in.setCrew(cc.find_crew_by_id(id_crew));
                        in.setReceiver(r);
                        in.setSender(u);
                        in.setInvite_enabled(Boolean.TRUE);
                        ic.create_invite(in);
                        
                        // Preparo dati per l'invito via email
                        Mailer m = new Mailer();
                        // Oggetto
                        String subject = "[SecondoProgettoWeb] Hai ricevuto un invito per " + c.getName();

                        // Messaggio HTML
                        String link_path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
                        String link_invites = "<a href='" + link_path + "/AcceptInviteServlet?id_gruppo=" + in.getCrew().getId() + "&email_receiver=" + r.getEmail() + "&email_sender=" + u.getEmail() + "'>qui</a>";

                        String text = "Ciao " + r.getUsername() + ",<br /><br />";
                        text += "<b>" + u.getUsername() + "</b> ti ha invitato a partecipare al gruppo <b>" + c.getName() + "</b>.<br />";
                        text += "Clicca " + link_invites + " per accettare l'invito. Altrimenti ignora il messaggio.";
                        
                        // Invio email: se la email non dovesse essere mandata per errori del server, ecc.
                        // l'invito non arriva per email
                        m.sendEmail(r, subject, text);
                        
                    }

                }
                Crew_UserController cuc = new Crew_UserController();
                Crew_User cu = new Crew_User();
                cu.setId_user(u.getId());
                cu.setId_crew(id_crew);
                cu.setCrew_user_enabled(Boolean.TRUE);
                cuc.create_crew_user(cu);

            } catch (SQLException ex) {
                log.warn(ex);
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
