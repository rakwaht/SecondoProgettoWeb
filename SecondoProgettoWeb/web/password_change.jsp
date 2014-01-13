<%-- 
    Document   : password_change
    Created on : 6-gen-2014, 10.49.03
    Author     : francesco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/layout/head.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h1>Cambia password</h1>

            <form method="post" action="PasswordChangeServlet?psw_change_id=${param.psw_change_id}" role="form">
                <div class="form-group">
                    <label>Nuova password</label>
                    <input type='password' name='password' class="form-control" />
                </div>
                <div class="form-group">
                    <label>Ripeti nuova passvord</label>
                    <input type='password' name='password_confirm' class="form-control" />
                </div>
                <button type='submit' class="btn btn-green btn-lg">Invia</button>
            </form>


            <c:choose>
                <c:when test="${param.message_password == 'password_changed'}">
                    <div class="alert alert-success">
                        <p>Password cambiata con successo. <a href='login.jsp'>Entra</a>.</p>
                    </div>
                </c:when>
                <c:when test="${param.message_password == 'password_not_valid'}">
                    <div class="alert alert-danger">
                        Nuova password non valida. Le password devono coincidere ed essere di lunghezza minima 5 caratteri.
                    </div>
                </c:when>
            </c:choose>

        </div>
    </div>
</div>

<%@include file="/layout/foot.jsp" %>

