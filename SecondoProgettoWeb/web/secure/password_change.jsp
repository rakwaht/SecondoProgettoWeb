<%-- 
    Document   : password_change
    Created on : 6-gen-2014, 10.49.03
    Author     : francesco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/layout/head.jsp" %>

<h1>Ciao imposta la tua nuova password!</h1>
<form method="post" action="PasswordChangeServlet">
Nuova password: <input type='password' name='change_psw' /><br />
Ripeti nuova passvord: <input type='password' name='change_psw_confirm' /><br />
<input type='submit' value="Invia"/>
</form>

<%@include file="/layout/foot.jsp" %>

