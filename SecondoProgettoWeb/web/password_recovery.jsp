<%-- 
    Document   : password_recovery
    Created on : 5-gen-2014, 23.43.34
    Author     : francesco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="layout/head.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h1>Ripristino password</h1>
            <form method="post" action="RecoveryPasswordServlet" role="form">
                <div class="form-group">
                    <label>Inserisci l'indirizzo email</label> 
                    <input type='email' name='email' class="form-control" />
                </div>
                <button type="submit" class="btn btn-green btn-lg">Invia</button>
            </form>

            <c:choose>
                <c:when test="${param.message_email == 'sent'}">
                    <div class="alert alert-success">
                        <p>Messaggio inviato con successo.</p>    </div>
                    </c:when>
                    <c:when test="${param.message_email == 'not_sent'}">
                    <div class="alert alert-danger"><p>Errore, messaggio non inviato.</p>    </div>
                </c:when>
                <c:when test="${param.message_user == 'user_not_found'}">
                    <div class="alert alert-danger"><p>Non è stato registrato alcun utente con questa email.</p>    </div>

                </c:when>
                <c:when test="${param.message_session == 'session_expired'}">
                    <div class="alert alert-danger"><p>La sessione è scaduta. Inserisci di nuovo la email per inviare una nuova richiesta di cambio password oppure torna alla <a href="login.jsp">home</a>.</p>    </div>
                </c:when>
            </c:choose>
        </div>
    </div>
</div>

<%@include file="layout/foot.jsp" %>
