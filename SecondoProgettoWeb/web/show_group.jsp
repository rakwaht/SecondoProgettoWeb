
<%@include file="/layout/head.jsp" %>

<h1>Pagina show gruppo del gruppo: ${crew.name}</h1>
<h2>
    <c:choose>
        <c:when test="${crew.crew_private}">
            Gruppo privato
        </c:when>
        <c:otherwise>
            Gruppo pubblico
        </c:otherwise>
    </c:choose>
</h2>

Dettagli gruppo:<br />
nome: ${crew.name}<br />
descrizione: ${crew.description}<br />
proprietario: ${crew.admin.username}<br />
data creazione: ${crew.creation_date}<br />

<c:if test="${admin}">  
    <a href='secure/ModifyGroupServlet?id_crew=${crew.id}'>Modify Group</a>
</c:if>
<br /><br /><br />

<c:if test="${user_can_edit}">  
    Crea un post in questo gruppo : <br />

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
        Testo: <textarea id="testo" name="testo" type='text' ></textarea><br />
        <input type='file' name='file' onchange='add_upload_file();' /> <br />
        <button type='submit' >Submit</button>
    </form>
</c:if>





<br />Ecco i post:<br />
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

<%@include file="/layout/foot.jsp" %>

