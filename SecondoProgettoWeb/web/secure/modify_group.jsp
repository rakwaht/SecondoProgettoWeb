
<%@include file="/layout/head.jsp" %>

<h1>MODIFICA GRUPPO ${crew.name}</h1><br />

<form method="post" action="ModifyGroupServlet?id_crew=${crew.id}">  
    <input type='text' name='title'value='${crew.name}'/><br/>
    <textarea type='text' name='description'>${crew.description}</textarea><br/>

    <c:choose>
        <c:when test="${crew.crew_private}">
            privato <input type='radio' name='type' value='private' checked='checked' />
            pubblico <input type='radio' name='type' value='public' />
        </c:when>
        <c:otherwise>
            privato <input type='radio' name='type' value='private' />
            pubblico <input type='radio' name='type' value='public' checked='checked' />
        </c:otherwise>
    </c:choose>

    <br />  

    <c:if test="${not empty followers}">
        ELIMINA:<br/>
        <c:forEach var="followers" items="${followers}">
            ${followers.username}<input type='checkbox' name='followers'value='${followers.id}'/><br/>
        </c:forEach>
    </c:if>
    <c:if test="${not empty not_followers}">
        INVITA:<br/>
        <c:forEach var="not_followers" items="${not_followers}">
            ${not_followers.username}<input type='checkbox' name='not_followers'value='${not_followers.id}'/><br/>
        </c:forEach>
    </c:if>        
    <c:if test="${not empty invited}">
        <c:forEach var="invited" items="${invited}">
            ${invited.username} già invitato!<br />
        </c:forEach>
    </c:if>
    <input type='submit' value='Modifica'/>
</form>

<!-- error messages -->
<p>
    <c:choose>
        <c:when test="${param.message_editgroup == 'empty_params'}">
            Nome e/o descrizione obbligatorie!
        </c:when>
    </c:choose>
</p>
<%@include file="/layout/foot.jsp" %>

