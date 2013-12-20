<%-- 
    Document   : login
    Created on : 20-dic-2013, 9.56.44
    Author     : Davide
--%>

<%@page import="com.deadormi.javabeans.LoginBean" %>
<jsp:useBean id="loginBean" class="com.deadormi.javabeans.LoginBean"/>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login Page!</h1>
        <form method="post" action="home.jsp">
            Nome <input type="text" name="username" />
            Password <input type="password" name="password"/>
            <input type="submit" />
        </form>
    </body>
</html>
