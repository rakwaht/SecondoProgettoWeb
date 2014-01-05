
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/layout/head.jsp" %>
<a href="/SecondoProgettoWeb/LogoutServlet">logout</a>

<c:choose>
    <c:when test="${empty user.last_login_date}">
        <h1>Benvenuto, ${user.username} ! </h1><br />
    </c:when>
    <c:otherwise>
        <h1>Bentornato, ${user.username} ! </h1><br /> last_login date:${user.last_login_date}<br />
    </c:otherwise>
</c:choose>

<!-- AVATAR -->        
<c:choose>
    <c:when test="${empty user.avatar_name}">
        <img src="${pageContext.request.contextPath}/res/images/default_avatar.png" style="margin:0 auto; width:100px; heigth:100px;" />
    </c:when>
    <c:otherwise>
        <img src="${pageContext.request.contextPath}/resource/avatar/${user.id}_${user.avatar_name}" style="margin:0 auto; width:100px; heigth:100px;" />
    </c:otherwise>
</c:choose>

        <br />
psw: ${user.password}<br />
email:${user.email}<br />
avatar:${user.avatar_name}<br />
id:${user.id}<br />


<br />
<br />
<a href="/SecondoProgettoWeb/HomeServlet?next=edit">Edita il mio profilo</a><br />
<a href="/SecondoProgettoWeb/HomeServlet?next=groups">Gruppi</a><br />
<a href="/SecondoProgettoWeb/secure/InviteServlet">Inviti</a><br />
<c:if test="${user.moderator}">
    <a href="/SecondoProgettoWeb/secure/ModeratorServlet">ModeratorPage</a>
</c:if>

<%@include file="/layout/foot.jsp" %>
