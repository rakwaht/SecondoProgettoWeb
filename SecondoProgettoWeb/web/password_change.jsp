<%-- 
    Document   : password_change
    Created on : 6-gen-2014, 10.49.03
    Author     : francesco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/layout/head.jsp" %>

<h1>Cambia password</h1>

<form method="post" action="PasswordChangeServlet?psw_change_id=${param.psw_change_id}">
Nuova password: <input type='password' name='password' /><br />
Ripeti nuova passvord: <input type='password' name='password_confirm' /><br />
<input type='submit' value="Invia"/>
</form>

<p>${param.message_password}</p>

<%@include file="/layout/foot.jsp" %>

