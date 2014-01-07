
<%@include file="/layout/head.jsp" %>

<h1>Pagina show gruppo del gruppo: ${crew.name}</h1><br />

Dettagli gruppo:<br />
nome: ${crew.name}<br />
desctizione: ${crew.description}<br />
propietario: ${crew.admin.username}<br />
data creazione: ${crew.creation_date}<br />

<c:if test="${admin}">  
    <a href='secure/ModifyGroupServlet?id_crew=${crew.id}'>Modify Group</a>
</c:if>
<br /><br /><br />

<c:if test="${user_can_edit}">  
    Crea un post in questo gruppo : <br />
    <c:if test="${not empty param.error}">
        Errore in input numero ${param.error}
    </c:if>
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

