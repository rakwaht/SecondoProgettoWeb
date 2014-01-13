
<%@include file="/layout/head.jsp" %>

<c:choose>
    <c:when test="${not empty user}">
        <%@include file="/layout/navigation.jsp" %>
    </c:when>
    <c:otherwise>
        <%@include file="/layout/navigation_no_login.jsp" %>
    </c:otherwise>
</c:choose>

<div class="main container">

    <div class="box">
        <c:choose>
            <c:when test="${empty user}">
                <h1>Lista gruppi</h1>
            </c:when>
            <c:otherwise>
                <h1>Gruppi di ${user.username}! </h1><br />
                <a href="secure/NewGroupServlet">
                    <button type="button" class="btn btn-green">Nuovo gruppo</button>
                </a>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="row"> 
        <div class="col-md-6">
            <c:if test="${not empty user}">
                <div class="box">
                    <h2>Gruppi ai quali sei iscritto</h2>
                    <table class="table">
                        <thead>
                            <tr>
                                <th></th>
                                <th>Gruppo</th>
                                <th>Descrizione</th>
                                <th>Admin</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="myg" items="${my_groups}">
                                <tr>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not myg.crew_enabled}"><i class="fa fa-lock"></i></c:when>
                                            <c:otherwise></c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td><a href="ShowGroupServlet?id_crew=${myg.id}" > ${myg.name}</a></td>
                                    <td>${myg.description}</td>
                                    <td>${myg.admin.username}</td>
                                </tr>

                            </c:forEach>

                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>
        <div class="col-md-6">
            <div class="box">
                <h2>Gruppi pubblici</h2>
                <table class="table">
                    <thead>
                        <tr>
                            <th></th>
                            <th>Gruppo</th>
                            <th>Descrizione</th>
                            <th>Admin</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="g" items="${public_groups}">
                            <tr>
                                <td>
                                    <c:choose>
                                        <c:when test="${not g.crew_enabled}"><i class="fa fa-lock"></i></c:when>
                                        <c:otherwise></c:otherwise>
                                    </c:choose>
                                </td>
                                <td><a href="ShowGroupServlet?id_crew=${g.id}" > ${g.name}</a></td>
                                <td>${g.description}</td>
                                <td>${g.admin.username}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>

<%@include file="/layout/foot.jsp" %>

