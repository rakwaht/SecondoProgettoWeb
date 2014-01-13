<%@include file="/layout/head.jsp" %>
<c:choose>
    <c:when test="${not empty user}">
        <%@include file="/layout/navigation.jsp" %>
    </c:when>
    <c:otherwise>
        <%@include file="/layout/navigation_no_login.jsp" %>
    </c:otherwise>
</c:choose>

<div class="main">
    <div class="section gray-bg">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <h1>Gruppo ${crew.name}</h1>
                    <h3>
                        <c:choose>
                            <c:when test="${crew.crew_private}">
                                <i class="fa fa-eye-slash"></i>&nbsp;Gruppo privato
                            </c:when>
                            <c:otherwise>
                                <i class="fa fa-eye"></i>&nbsp;Gruppo pubblico
                            </c:otherwise>
                        </c:choose>
                    </h3>
                    <c:if test="${not crew.crew_enabled}"> 
                        <h4><i class="fa fa-lock"></i>&nbsp;&nbsp;Gruppo chiuso</h4>
                    </c:if>
                    <c:if test="${user.moderator &&  crew.crew_enabled}">  
                        <a href="secure/CloseGroupServlet?id_crew=${crew.id}"><button class="btn btn-wine">Chiudi gruppo</button></a>
                    </c:if>
                    <c:if test="${admin}">
                        <a href='secure/ModifyGroupServlet?id_crew=${crew.id}'>
                            <button type="button" class="btn btn-wine">Modifica gruppo</button>
                        </a>
                    </c:if> 
                </div>

                <div class="col-md-6 green-bg white">
                    <h3>Info</h3>
                    <p>${crew.description}</p>
                    <p>Admin: ${crew.admin.username}</p>
                    <p>Data creazione: ${crew.creation_date}</p>

                    <div class="row">
                        <div class="col-md-6 green-bg white">

                            <button href="#" id="members" class="btn btn-wine" style="margin-bottom:20px; width:100%">Partecipanti</button>
                            <p id="members-toggle">
                                <c:forEach var="m" items="${members}">
                                    ${m.username}<br />
                                </c:forEach>
                            </p>
                        </div>
                        <div class="col-md-6 green-bg white">
                            <button href="#" id="files" class="btn btn-wine" style="margin-bottom:20px; width:100%">Files</button>
                            <p id="files-toggle">
                                <c:forEach var="p" items="${posts}">
                                    <c:forEach var="f" items="${p.files}">
                                        <a class="white" href="${pageContext.request.contextPath}/resource/files/${crew.id}/${f.id}-${f.name}">${f.id}-${f.name}</a><br />
                                    </c:forEach>        
                                </c:forEach>
                            </p>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <div class="container" style="margin-top: 20px">
        <div class="row">
            <div class="col-md-12">

                <c:if test="${user_can_edit && crew.crew_enabled}"> 

                    <c:if test="${not empty param.error}">
                        <div class="alert alert-danger">
                            <c:choose>
                                <c:when test="${param.error == '1'}">
                                    <p>I file caricati superano il peso totale massimo (10MB)!</p>
                                </c:when>
                                <c:when test="${param.error == '2'}">
                                    <p>Campo di testo vuoto!</p>
                                </c:when>
                                <c:when test="${param.error == '3'}">
                                    <p>Hai superato il limite massimo di parole!</p>
                                </c:when>
                            </c:choose>
                        </div>
                    </c:if>

                    <form method='POST' action='secure/NewPostServlet?id_crew=${crew.id}' enctype='multipart/form-data' accept-charset="UTF-8">
                        <textarea class="form-control" id="testo" rows="3" name="testo" type='text' placeholder="Scrivi un post"></textarea><br />
                        <input type='file' name='file' onchange='add_upload_file();' /> <br />
                        <button type='submit' class="btn btn-green btn-lg">Invia</button>
                    </form>
                    
                   <div style="background-color: red;">
                        <div id='scelte'>
                            <c:forEach var="p" items="${posts}">
                                    <c:forEach var="f" items="${p.files}">
                                        <div>
                                            <input type='checkbox' value="${f.id}-${f.name}" name='file' value='file'>
                                            <label>${f.id}-${f.name}</label>
                                        </div><br/>
                                    </c:forEach>        
                            </c:forEach>
                            <button type='button' value='Linka Selezionati' onclick='linka_selezionati()' >Linka Selezionati</button>
                            <button type='button' value='QR Selezionati' onclick='qr_selezionati()' >QR Selezionati</button>
                        </div>
                    </div>
                </c:if>

                <c:forEach var="p" items="${posts}">

                    <div class="row box">

                        <div class="col-md-2">
                            <div class="center">
                                <!-- AVATAR -->        
                                <c:choose>
                                    <c:when test="${empty p.writer.avatar_name}">
                                        <img src="${pageContext.request.contextPath}/res/images/default_avatar.png" class="avatar" />
                                    </c:when>
                                    <c:otherwise>
                                        <img src="${pageContext.request.contextPath}/resource/avatar/${p.writer.id}_${p.writer.avatar_name}" class="avatar center" />
                                    </c:otherwise>
                                </c:choose>
                                <p>${p.writer.username}</p>
                            </div>
                        </div>
                        <div class="col-md-10">

                            <div class="row">
                                <div class="col-md-12">
                                    <p><c:out value="${p.text}" escapeXml="false" /></p>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-5">
                                    <p>
                                        Scritto alle ${p.creation_date}
                                    </p>
                                </div>
                                <div class="col-md-5">
                                    <c:if test="${not empty p.files}">
                                        File allegati <ul class="files_list">
                                            <c:forEach var="f" items="${p.files}">
                                                <li><a href="${pageContext.request.contextPath}/resource/files/${crew.id}/${f.id}-${f.name}" target="_blank" >${f.id}-${f.name}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:forEach>

            </div>
        </div>
    </div>

</div>
<%@include file="/layout/foot.jsp" %>

