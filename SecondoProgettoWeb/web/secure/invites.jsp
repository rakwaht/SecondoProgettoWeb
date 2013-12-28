<%-- 
    Document   : invites
    Created on : 24-dic-2013, 19.07.23
    Author     : Davide
--%>



<%@include file="/layout/head.jsp" %>
<h1>Inviti!</h1>
<c:forEach var="i" items="${invites}">
    <c:out value="${i.id_crew}" />
</c:forEach>
</body>
<%@include file="/layout/foot.jsp" %>