<%-- 
    Document   : email_accept_invite
    Created on : 7-gen-2014, 10.46.55
    Author     : francesco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/layout/head.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h1>Acceta invito via email</h1>

            <c:choose>
                <c:when test="${param.message == 'accepted'}">
                    <p>Hai accetato l'invito al gruppo <b>${crew.name}</b></p>
                    <a href="login.jsp">Entra</a> per partecipare alla discussione.
                </c:when>
                <c:when test="${param.message == 'not_valid'}">
                    <p>Invito non valido.</p>
                </c:when>
                <c:otherwise>
                    <c:redirect url="login.jsp"/>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

<%@include file="/layout/foot.jsp" %>
