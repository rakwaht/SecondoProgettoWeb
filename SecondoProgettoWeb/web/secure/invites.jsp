<%-- 
    Document   : invites
    Created on : 24-dic-2013, 19.07.23
    Author     : Davide
--%>

<%@include file="/layout/head.jsp" %>
<%@include file="/layout/navigation.jsp" %>

<h1>Inviti!</h1>
<c:if test="${not empty invites}">
    <form action='InviteServlet' method='POST'>
        <c:forEach var="i" items="${invites}">
            Hai ricevuto un invito da <b><c:out value="${i.sender.username}" /></b>  per il gruppo <b><c:out value="${i.crew.name}" /></b>
            <input type='checkbox' name='groups' value='${i.crew.id}'/><br/>
        </c:forEach>
        <input type='submit' value='Accetta'/>
    </form>
</c:if>

<c:if test="${empty invites}">
    Non ci sono inviti! <a href="home.jsp">Torna alla home</a>
</c:if>

</body>
<%@include file="/layout/foot.jsp" %>