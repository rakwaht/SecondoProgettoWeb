<%@include file="/layout/head.jsp" %>
<h1>Crea qui il tuo fantastico gruppo!</h1>
<form method="post" action="NewGroupServlet">
    Please enter crew name	
    <input type="text" name="name"/><br/>		
    Please enter crew description
    <textarea type="password" name="description"></textarea><br/>
    Tipo:<br/>
    <input type="radio" name="type" value="public"  checked="checked"/>Public<br/>
    <input type="radio" name="type" value="private"/>Private<br/>
    <c:if test="${not empty users}">
        Utenti:<br/>
    </c:if>

    <c:forEach var="u" items="${users}">
        <input type="checkbox" name="users" value="${u.id}" /> <c:out value="${u.username}" /><br />
    </c:forEach>
    <br /> 

    <c:if test="${param.message_newgroup == 'empty_params'}">
        <p>Nome e/o descrizione obbligatorie!</p>
    </c:if>

    <button type="submit">Crea</button>
    <button type="reset">Reset</button>
    
</form>
<%@include file="/layout/foot.jsp" %>
