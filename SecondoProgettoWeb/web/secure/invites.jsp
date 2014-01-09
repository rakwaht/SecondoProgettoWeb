<%-- 
    Document   : invites
    Created on : 24-dic-2013, 19.07.23
    Author     : Davide
--%>

<%@include file="/layout/head.jsp" %>
<%@include file="/layout/navigation.jsp" %>

<div class="main container">
<div class="box">
    <h1>Inviti!</h1>
    <c:if test="${not empty invites}">
        <form action='InviteServlet' method='POST'>
            <p>
                <c:forEach var="i" items="${invites}">
                    Hai ricevuto un invito da <b><c:out value="${i.sender.username}" /></b>  per il gruppo
                    <c:if test="${i.crew.crew_private}"> privato </c:if>
                    <c:if test="${not i.crew.crew_private}"> pubblico </c:if>
                    <b><c:out value="${i.crew.name}" /></b>
                    <input type='checkbox' name='groups' value='${i.crew.id}'/><br/>
                </c:forEach>
            </p>
            <button type='submit' class="btn btn-green btn-lg">Accetta</button>
        </form>
    </c:if>

    <c:if test="${empty invites}">
        Non ci sono inviti! <a href="home.jsp">Torna alla home</a>
    </c:if>
</div>
    </div>
<%@include file="/layout/foot.jsp" %>