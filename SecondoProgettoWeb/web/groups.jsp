
<%@include file="/layout/head.jsp" %>

<c:choose>
    <c:when test="${empty user}">
        <h1>Lista gruppi pubblici</h1>
    </c:when>
    <c:otherwise>
        <h1>Gruppi di ${user.username}! </h1><br />
    </c:otherwise>
</c:choose>

Eccoti i nome dei gruppi Pubblici:<br />
<c:forEach var="g" items="${public_groups}">
    <c:out value="${g.name}" /> <a href="ShowGroupServlet?id_group=${g.id}" >Show Gruppo</a><br />
</c:forEach>

<br /><br />

<c:if test="${not empty user}">
    Eccoti i nomi dei gruppi a cui sei iscritto:<br />
    <c:forEach var="myg" items="${my_groups}">
        <c:out value="${myg.name}" /><a href="ShowGroupServlet?id_group=${myg.id}" >Show Gruppo</a><br />
    </c:forEach>
    <a href="secure/NewGroupServlet">NUOVO GRUPPO</a>
</c:if>



<%@include file="/layout/foot.jsp" %>

