
<%@include file="/layout/head.jsp" %>

<h1>MODIFICA GRUPPO ${crew.name}</h1><br />

<c:if test="${not empty followers}"><br />
    FOLLOW: 
<c:forEach var="followers" items="${followers}">
    <br /><c:out value="${followers.username}" />
</c:forEach>
</c:if>
    
<c:if test="${not empty not_followers}"><br />
    NOT FOLLOW:
<c:forEach var="not_followers" items="${not_followers}">
    <br /><c:out value="${not_followers.username}" />
</c:forEach>
</c:if>
 
<c:if test="${not empty invited}"><br />
    INVITED:
<c:forEach var="invited" items="${invited}">
    <br /><c:out value="${invited.username}" />
</c:forEach>
</c:if>

<%@include file="/layout/foot.jsp" %>

