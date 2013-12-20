<%-- 
    Document   : check_login2
    Created on : 20-dic-2013, 13.22.55
    Author     : francesco
--%>

<%@page import="com.deadormi.entity.User"%>

<jsp:useBean id="lc" scope="page" class="com.deadormi.controller.LoginController" />

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    User user = lc.authenticate(username, password);

    if (user != null) {
        session.setAttribute("user", user);
        response.sendRedirect("home.jsp");
    } else {
        session.setAttribute("message", "Username/password non esistente!");
        response.sendRedirect("login2.jsp");
    }
%>
