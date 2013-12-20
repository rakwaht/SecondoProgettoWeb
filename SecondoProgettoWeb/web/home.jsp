<%-- 
    Document   : home
    Created on : 20-dic-2013, 10.07.59
    Author     : Davide
--%>

<jsp:useBean id="login" class="com.deadormi.javabeans.LoginBean" scope="request"/>
<jsp:setProperty name="login" property="*"/> 

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        Hai scritto:<BR>
        Username: <%= login.getUsername() %><BR>
        Password: <%= login.getPassword() %><BR>
    </body>
</html>
