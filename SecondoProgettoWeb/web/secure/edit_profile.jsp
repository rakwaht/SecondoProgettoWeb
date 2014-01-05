
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/layout/head.jsp" %>
<h1>Sono la pagina che edita il profilo di ${user.username}</h1>

<div class="box">
    <form method="post" action="EditProfileServlet?whatEdit=editName">
        Inserisci il nuovo nome utente: <input type="text" name="new_username" value="${user.username}"/>
        <input type="submit" value="Modifica"/>
    </form>
    <p>${param.message_username}</p>
</div>    

<div class="box">
    <form method="post" action="EditProfileServlet?whatEdit=editPassword">
        Cambia password:<br />
        Inserisci vecchia password: <input type="password" name="old_password" value=""/><br />
        Inserisci nuova password: <input type="password" name="new_password" value=""/><br />
        Ripeti nuova password: <input type="password" name="new_password_confirm" value=""/><br />
        <input type="submit" value="Modifica"/>
    </form>
    <p>${param.message_password}</p>
</div>           

<div class="box">
    <form method="post" action="EditProfileServlet?whatEdit=editAvatar" enctype="multipart/form-data">
        Carica nuovo avatar <input type="file" name="avatar"/>
        <input type="submit" value="Modifica"/>    
    </form>

    <p>${param.message_avatar}</p>

    <!-- -------- DEFAULT AVATAR -------- -->
    <c:if test="${user.avatar_name == null}">
        <img src="${pageContext.request.contextPath}/res/images/default_avatar.png" style="margin:0 auto; width:100px; heigth:100px;" />
    </c:if>

    <!-- -------- USER AVATAR -------- -->
    <c:if test="${user.avatar_name != null}">
        <img src="${pageContext.request.contextPath}/resource/avatar/${user.id}_${user.avatar_name}" style="margin:0 auto; width:100px; heigth:100px;" />
    </c:if>
</div>


<%@include file="/layout/foot.jsp" %>
