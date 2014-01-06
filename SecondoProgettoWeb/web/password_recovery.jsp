<%-- 
    Document   : password_recovery
    Created on : 5-gen-2014, 23.43.34
    Author     : francesco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="layout/head.jsp" %>

<h1>Ripristino password</h1>
<form method="post" action="RecoveryPasswordServlet">
    Inserisci l'indirizzo email <input type='email' name='email' />
    <input type='submit' value='Invia'/>
</form>
    <p>${param.message_email}</p>
    
    <!-- MESSAGGIO SE L'UTENTE NON CAMBIA LA PASSWORD ENTRO 3 MIN -->
    <p>${param.message_password}</p>
<%@include file="layout/foot.jsp" %>
