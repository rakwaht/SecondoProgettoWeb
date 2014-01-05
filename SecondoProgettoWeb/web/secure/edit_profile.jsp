
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/layout/head.jsp" %>
<h1>Sono la pagina che edita il profilo di ${user.username}</h1>

<div class="box">
    <form method="post" action="EditProfileServlet">
        Inserisci il nuovo nome utente: <input type="text" name="new_username" value="${user.username}"/>
        <input type="hidden" name="whatEdit" value="editName"/>
        <input type="submit" value="Modifica"/>
        <p>${param.message_username}</p>
    </form>
</div>    

<div class="box">

    <form method="post" action="EditProfileServlet">
        Cambia password:<br />
        Inserisci vecchia password: <input type="password" name="old_password" value=""/><br />
        Inserisci nuova password: <input type="password" name="new_password" value=""/><br />
        Ripeti nuova password: <input type="password" name="new_password_confirm" value=""/><br />
        <input type="hidden" name="whatEdit" value="editPassword"/>
        <input type="submit" value="Modifica"/>
        <p>${param.message_password}</p>
    </form>
</div>           
<div class="box">

    <form method="post" action="EditProfileServlet">
        Inserisci nuovo avatar <input type="text" name="username" value="${user.username}"/>
        <input type="hidden" name="whatEdit" value="editAvatar"/>
        <input type="submit" value="Modifica"/>
    </form>

</div>

        
<%@include file="/layout/foot.jsp" %>
