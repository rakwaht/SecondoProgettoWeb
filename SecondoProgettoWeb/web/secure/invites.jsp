<%-- 
    Document   : invites
    Created on : 24-dic-2013, 19.07.23
    Author     : Davide
--%>

<%@include file="/layout/head.jsp" %>
<%@include file="/layout/navigation.jsp" %>

<div class="main container">
    <div class="box">
        <h1>Inviti</h1>
        <c:if test="${not empty invites}">
            <p>Seleziona gli inviti</p>
            <form action='InviteServlet' method='POST'>
                <ul class="list-group">
                    <c:forEach var="i" items="${invites}">
                        <li class="list-group-item">
                            <input type='checkbox' name='groups' value='${i.crew.id}'/>
                            <b><c:out value="${i.sender.username}"></c:out></b> ti ha invitato a partecipare al gruppo
                            <c:if test="${i.crew.crew_private}"> privato <c:out value="${i.crew.name}"></c:out></c:if>
                            <c:if test="${not i.crew.crew_private}"> pubblico <a href="/SecondoProgettoWeb/ShowGroupServlet?id_crew=${i.crew.id}" ><c:out value="${i.crew.name}"></c:out></a></c:if>
                            </li>
                    </c:forEach>
                </ul>
                <button type='submit' class="btn btn-green">Accetta</button>
            </form>
        </c:if>

        <c:if test="${empty invites}">
            Non ci sono inviti! <a href="/SecondoProgettoWeb/HomeServlet">Torna alla home</a>
        </c:if>
    </div>
</div>
<%@include file="/layout/foot.jsp" %>