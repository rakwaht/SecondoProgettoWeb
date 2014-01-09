
<%@include file="/layout/head.jsp" %>

<c:if test="${not empty user}">
    <%@include file="/layout/navigation.jsp" %>
</c:if>

<div class="main container">

    <c:choose>
        <c:when test="${empty user}">
            <h1>Lista gruppi</h1>
        </c:when>
        <c:otherwise>
            <h1>Gruppi di ${user.username}! </h1><br />
            <a href="secure/NewGroupServlet">
                <button type="button" class="btn btn-default">Nuovo gruppo</button>
            </a>
        </c:otherwise>
    </c:choose>

    <div class="row"> 
        <div class="col-md-6">
            <c:if test="${not empty user}">
                <h2>Gruppi ai quali sei iscritto</h2>
                <c:forEach var="myg" items="${my_groups}">
                    <a href="ShowGroupServlet?id_crew=${myg.id}" ><c:out value="${myg.name}" /></a>
                    Admin: <c:out value="${myg.admin.username}" />
                    <br />
                </c:forEach>
            </c:if>
        </div>
        <div class="col-md-6">
            <h2>Gruppi pubblici</h2>
            <c:forEach var="g" items="${public_groups}">
                <a href="ShowGroupServlet?id_crew=${g.id}" ><c:out value="${g.name}" /></a>
                Admin: <c:out value="${g.admin.username}" />
                <br />
            </c:forEach>
        </div>
    </div>

</div>

<%@include file="/layout/foot.jsp" %>

