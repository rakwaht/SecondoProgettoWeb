<%@include file="/layout/head.jsp" %>
<%@include file="/layout/navigation.jsp" %>

<div class="main container">
    <div class='row'>    
        <div class="col-md-6 col-md-offset-3">
            <div class="box">
                <form method="post" action="EditProfileServlet?whatEdit=editAvatar" enctype="multipart/form-data" role="form">
                    <h3>Cambia avatar</h3>
                    <div class="form-group">
                        <input type="file" name="avatar" />
                    </div>
                    <button type="submit" class="btn btn-green">Modifica</button>
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
            <div class="box">
                <form method="post" action="EditProfileServlet?whatEdit=editName" role="form">
                    <h3>Cambia username</h3>
                    <div class="form-group">
                        <label>Nuovo username</label>
                        <input type="text" name="new_username" value="${user.username}" class="form-control" />
                    </div>
                    <button type="submit" class="btn btn-green">Modifica</button>
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
                <form method="post" action="EditProfileServlet?whatEdit=editPassword" role="form">
                    <h3>Cambia password</h3>
                    <div class="form-group">
                        <label>Inserisci vecchia password</label>
                        <input type="password" name="old_password" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>Inserisci nuova password</label>
                        <input type="password" name="new_password" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>Ripeti nuova password</label>
                        <input type="password" name="new_password_confirm" class="form-control" />
                    </div>
                    <button type="submit" class="btn btn-green">Modifica</button>
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

            
        </div>
    </div>
</div>

<%@include file="/layout/foot.jsp" %>
