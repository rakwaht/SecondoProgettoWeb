<%@include file="/layout/head.jsp" %>
<h1>MOD PAGE</h1>
<c:forEach var="c" items="${crews}">
    ---------<br/>
    <c:out value="${c.name}" /><br />
    NUMERO DI UTENTI<br/>
    <c:out value="${c.crew_private}" /><br />
    <a href="/SecondoProgettoWeb/ShowGroupServlet?id_group=${c.id}">Link</a><br />
    NUMERO DI POST<br/>
    ---------<br/><br/>
</c:forEach>


<%@include file="/layout/foot.jsp" %>