<%-- 
    Document   : invites
    Created on : 24-dic-2013, 19.07.23
    Author     : Davide
--%>



<%@include file="/layout/head.jsp" %>
<h1>Inviti!</h1>
<c:if test="${not empty invites}">
    <form action='InviteServlet' method='POST'>
        <c:forEach var="i" items="${invites}">
            <c:out value="${i.id_crew}" />
            <input type='checkbox' name='groups' value='${i.id_crew}'/><br/>
        </c:forEach>
        <input type='submit' value='Accetta'/>
    </form>
</c:if>
</body>
<%@include file="/layout/foot.jsp" %>