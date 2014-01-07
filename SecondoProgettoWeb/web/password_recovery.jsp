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

<!-- MESSAGGIO SE L'UTENTE NON CAMBIA LA PASSWORD ENTRO 3 MIN -->
<c:if test="">
</c:if>



<c:choose>
    <c:when test="${param.message_email == 'sent'}">
        <p>Messaggio inviato con successo.</p>
    </c:when>
    <c:when test="${param.message_email == 'not_sent'}">
        <p>Errore, messaggio non inviato.</p>
    </c:when>
    <c:when test="${param.message_user == 'user_not_found'}">
        <p>Non è stato registrato alcun utente con questa email.</p>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>


<%@include file="layout/foot.jsp" %>
