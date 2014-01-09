<%@include file="/layout/head.jsp" %>
<%@include file="/layout/navigation.jsp" %>

<div class="main container">
    <h1>Sono la pagina che edita il profilo di ${user.username}</h1>

    <div class="box">
        <form method="post" action="EditProfileServlet?whatEdit=editName">
            Inserisci il nuovo nome utente: <input type="text" name="new_username" value="${user.username}"/>
            <input type="submit" value="Modifica"/>
        </form>

        <!-- ERRORI CAMBIO NOME UTENTE -->
        <p>
            <c:choose>
                <c:when test="${param.message_username == 'changed'}">
                    Username cambiato con successo! Il nuovo username è ${user.username}.
                </c:when>
                <c:when test="${param.message_username == 'already_exists'}">
                    Username già esistene!
                </c:when>
                <c:when test="${param.message_username == 'too_short'}">
                    Username troppo corto!
                </c:when>
            </c:choose>
        </p>

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
        <p>
            <c:choose>
                <c:when test="${param.message_password == 'changed'}">
                    Password cambiata con successo!
                </c:when>
                <c:when test="${param.message_password == 'old_psw_error'}">
                    Password vecchia non corretta!
                </c:when>
                <c:when test="${param.message_password == 'new_psw_error'}">
                    Password nuove non coincidono!
                </c:when>
            </c:choose>
        </p>

    </div>           

    <div class="box">
        <form method="post" action="EditProfileServlet?whatEdit=editAvatar" enctype="multipart/form-data">
            Carica nuovo avatar <input type="file" name="avatar"/>
            <input type="submit" value="Modifica"/>    
        </form>

        <!-- ERRORI AVATAR -->
        <p>
            <c:choose>
                <c:when test="${param.message_avatar == 'changed'}">
                    Avatar cambiato!
                </c:when>
                <c:when test="${param.message_avatar == 'too_big'}">
                    File troppo grande!
                </c:when>
                <c:when test="${param.message_avatar == 'not_image'}">
                    Il file non è un'immagine!
                </c:when>
                <c:when test="${param.message_avatar == 'not_uploaded'}">
                    Nessun file selezionato!
                </c:when>
            </c:choose>
        </p>

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
</div>

<%@include file="/layout/foot.jsp" %>
