
<%@include file="/layout/head.jsp" %>

<h1>Pagina show gruppo del gruppo: ${crew.name}</h1><br />

Dettagli gruppo:<br />
nome: ${crew.name}<br />
desctizione: ${crew.description}<br />
propietario: ${crew.admin.username}<br />
data creazione: ${crew.creation_date}<br />

<br />Ecco i post:<br />
<c:forEach var="p" items="${posts}">
    <c:out value="${p.text}" /><br />
</c:forEach>

<c:if test="${admin}">  
    <a href='secure/ModifyGroupServlet?id_crew=${crew.id}'>Modify Group</a>
</c:if>
<%@include file="/layout/foot.jsp" %>

