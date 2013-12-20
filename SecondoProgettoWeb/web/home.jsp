
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="layout/head.jsp" %>
        <h1>Benvenuto, ${user.username} ! </h1><br />
        psw: ${user.password}<br />
        email:${user.email}<br />
        login date:${user.login_date}<br />
        avatar:${user.avatar_name}<br />
        id:${user.id}<br />
<%@include file="layout/foot.jsp" %>
