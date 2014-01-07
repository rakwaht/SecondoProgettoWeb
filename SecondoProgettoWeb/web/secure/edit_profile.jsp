
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/layout/head.jsp" %>
<h1>Sono la pagina che edita il profilo di ${user.username}</h1>

<div class="box">
    <form method="post" action="EditProfileServlet?whatEdit=editName">
        Inserisci il nuovo nome utente: <input type="text" name="new_username" value="${user.username}"/>
        <input type="submit" value="Modifica"/>
    </form>

    <!-- ERRORI CAMBIO NOME UTENTE -->
    <c:choose>
        <c:when test="${param.message_username == 'changed'}">
            <p>Username cambiato con successo! Il nuovo username è ${user.username}.</p>
        </c:when>
        <c:when test="${param.message_username == 'already_exists'}">
            <p>Username già esistene!</p>
        </c:when>
        <c:when test="${param.message_username == 'too_short'}">
            <p>Username troppo corto!</p>
        </c:when>
    </c:choose>

</div>    

<div class="box">
    <form method="post" action="EditProfileServlet?whatEdit=editPassword">
        Cambia password:<br />
        Inserisci vecchia password: <input type="password" name="old_password" value=""/><br />
        Inserisci nuova password: <input type="password" name="new_password" value=""/><br />
        Ripeti nuova password: <input type="password" name="new_password_confirm" value=""/><br />
        <input type="submit" value="Modifica"/>
    </form>

    <!-- ERRORI CAMBIO PASSWORD -->
    <c:choose>
        <c:when test="${param.message_password == 'changed'}">
            <p>Password cambiata con successo!</p>
        </c:when>
        <c:when test="${param.message_password == 'old_psw_error'}">
            <p>Password vecchia non corretta!</p>
        </c:when>
        <c:when test="${param.message_password == 'new_psw_error'}">
            <p>Password nuove non coincidono!</p>
        </c:when>
    </c:choose>

</div>           

<div class="box">
    <form method="post" action="EditProfileServlet?whatEdit=editAvatar" enctype="multipart/form-data">
        Carica nuovo avatar <input type="file" name="avatar"/>
        <input type="submit" value="Modifica"/>    
    </form>

    <!-- ERRORI AVATAR -->
    <c:choose>
        <c:when test="${param.message_avatar == 'changed'}">
            <p>Avatar cambiato!</p>
        </c:when>
        <c:when test="${param.message_avatar == 'too_big'}">
            <p>File troppo grande!</p>
        </c:when>
        <c:when test="${param.message_avatar == 'not_image'}">
            <p>Il file non è un'immagine!</p>
        </c:when>
        <c:when test="${param.message_avatar == 'not_uploaded'}">
            <p>Nessun file selezionato!</p>
        </c:when>
    </c:choose>


    <!-- IMMAGINE AVATAR -->        
    <c:choose>
        <c:when test="${empty user.avatar_name}">
            <img src="${pageContext.request.contextPath}/res/images/default_avatar.png" style="margin:0 auto; width:100px; heigth:100px;" />
        </c:when>
        <c:otherwise>
            <img src="${pageContext.request.contextPath}/resource/avatar/${user.id}_${user.avatar_name}" style="margin:0 auto; width:100px; heigth:100px;" />
        </c:otherwise>
    </c:choose>

</div>


<%@include file="/layout/foot.jsp" %>
