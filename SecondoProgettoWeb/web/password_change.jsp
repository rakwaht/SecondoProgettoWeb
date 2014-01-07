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


<c:choose>
    <c:when test="${param.message_password == 'password_changed'}">
        <p>Password cambiata con successo. <a href='login.jsp'>Entra</a>.</p>
    </c:when>
    <c:when test="${param.message_password == 'password_not_valid'}">
        <p>Nuova password non valida. Le password devono coincidere ed essere di lunghezza minima 5 caratteri.</p>
    </c:when>
    <c:when test="${param.message_session == 'session_expired'}">
        <p>La sessione è scaduta. Inserisci di nuovo la email per inviare una nuova richiesta di cambio password oppure torna alla <a href="login.jsp">home</a>.</p>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>



<%@include file="/layout/foot.jsp" %>

