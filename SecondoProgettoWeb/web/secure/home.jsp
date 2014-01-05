
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/layout/head.jsp" %>
<a href="/SecondoProgettoWeb/LogoutServlet">logout</a>

<h1>Benvenuto, ${user.username} ! </h1><br />
psw: ${user.password}<br />
email:${user.email}<br />
login date:${user.login_date}<br />
avatar:${user.avatar_name}<br />
id:${user.id}<br />
login_date:${user.login_date}

<br />
<br />
<a href="/SecondoProgettoWeb/HomeServlet?next=edit">Edita il mio profilo</a><br />
<a href="/SecondoProgettoWeb/HomeServlet?next=groups">Gruppi</a><br />
<a href="/SecondoProgettoWeb/secure/InviteServlet">Inviti</a><br />
<c:if test="${user.moderator}">
<a href="/SecondoProgettoWeb/secure/ModeratorServlet">ModeratorPage</a>
</c:if>

<%@include file="/layout/foot.jsp" %>
