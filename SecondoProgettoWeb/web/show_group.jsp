<%@include file="/layout/head.jsp" %>
<c:if test="${not empty user}">
    <%@include file="/layout/navigation.jsp" %>
</c:if>

<div class="main">
    <div class="section gray-bg">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <h1>Gruppo ${crew.name}</h1>
                    <h3>
                        <c:choose>
                            <c:when test="${crew.crew_private}">
                                Gruppo privato
                            </c:when>
                            <c:otherwise>
                                Gruppo pubblico
                            </c:otherwise>
                        </c:choose>
                    </h3>
                    <c:if test="${not crew.crew_enabled}"> 
                        <h4>Gruppo chiuso</h4>
                    </c:if>
                </div>
                <div class="col-md-6">

                </div>
            </div>
        </div>
    </div>

    <div class="section green-bg">
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <p style="margin-top: 20px">
                        <c:if test="${user.moderator}">  
                            <button class="btn btn-wine">Chiudi gruppo</button>
                        </c:if>
                        <c:if test="${admin}">
                            <a href='secure/ModifyGroupServlet?id_crew=${crew.id}'>
                                <button type="button" class="btn btn-wine">Modifica gruppo</button>
                            </a>
                        </c:if> 
                    </p>
                </div>
                <div class="col-md-4 white">
                    <h3>Info</h3>
                    <p>${crew.description}</p>
                    <p>Admin: ${crew.admin.username}</p>
                    <p>Data creazione: ${crew.creation_date}</p>
                </div>
                <div class="col-md-4 white">
                    <h3>Partecipanti</h3>
                    <c:forEach var="m" items="${members}">
                        ${m.username}<br />
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>

    <div class="container" style="margin-top: 40px">
        <div class="row">
            <div class="col-md-12">

                <c:if test="${user_can_edit && crew.crew_enabled}">  
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

                    <form method='POST' action='secure/NewPostServlet?id_crew=${crew.id}' enctype='multipart/form-data'>

                        <textarea class="form-control" id="testo" rows="3" name="testo" type='text' placeholder="Scrivi un post"></textarea><br />
                        <input type='file' name='file' onchange='add_upload_file();' /> <br />
                        <button type='submit' class="btn btn-green btn-lg">Invia</button>
                    </form>
                </c:if>

                <c:forEach var="p" items="${posts}">

                    <div class="row box">

                        <div class="col-md-2">
                            <!-- AVATAR -->        
                            <c:choose>
                                <c:when test="${empty user.avatar_name}">
                                    <img src="${pageContext.request.contextPath}/res/images/default_avatar.png" class="avatar-small" />
                                </c:when>
                                <c:otherwise>
                                    <img src="${pageContext.request.contextPath}/resource/avatar/${user.id}_${user.avatar_name}" class="avatar-small" />
                                </c:otherwise>
                            </c:choose>
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
                                        Scritto da ${p.writer.username} alle ${p.creation_date}
                                    </p>
                                </div>
                                <div class="col-md-5">
                                    <c:if test="${not empty p.files}">
                                        File allegati <ul class="files_list">
                                            <c:forEach var="f" items="${p.files}">
                                                <li><a href="${pageContext.request.contextPath}/resource/files/${crew.id}/${f.id}-${f.name}">${f.id}-${f.name}</a>
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

