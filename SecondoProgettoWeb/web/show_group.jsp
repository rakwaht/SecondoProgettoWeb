
<%@include file="/layout/head.jsp" %>
<%@include file="/layout/navigation.jsp" %>


<div class="main container">

    <div class="row">
        <div class="col-md-12">
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
    </div>

    <div class="row">
        <div class="col-md-5">

            <c:if test="${user.moderator}">  
                <button class="btn">Chiudi gruppo</button>
            </c:if>

            <c:if test="${admin}">  
                <a href='secure/ModifyGroupServlet?id_crew=${crew.id}'>Modifica gruppo</a>
            </c:if>  
            <br /><br />

            Membri del gruppo:<br />
            <c:forEach var="m" items="${members}">
                ${m.username}<br />
            </c:forEach>

            <br />
            Dettagli gruppo:<br />
            nome: ${crew.name}<br />
            descrizione: ${crew.description}<br />
            proprietario: ${crew.admin.username}<br />
            data creazione: ${crew.creation_date}<br />

        </div>
        <div class="col-md-7">

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
                    <button type='submit' class="btn btn-green">Invia</button>
                </form>
            </c:if>


            <br />Ci sono ${fn:length(posts)} post:<br />
            <c:forEach var="p" items="${posts}">

                <div class="box">
                    <c:out value="${p.text}" escapeXml="false" /><br />
                    Scritto da ${p.writer.username} alle ${p.creation_date}<br />

                    <c:if test="${not empty p.files}">
                        Files del post:<br />
                        <ul>
                            <c:forEach var="f" items="${p.files}">
                                <li><a href="${pageContext.request.contextPath}/resource/files/${crew.id}/${f.id}-${f.name}">${f.id}-${f.name}</a></li>
                                </c:forEach>
                        </ul>
                    </c:if>
                </div>
            </c:forEach>

        </div>
    </div>

</div>
<%@include file="/layout/foot.jsp" %>

