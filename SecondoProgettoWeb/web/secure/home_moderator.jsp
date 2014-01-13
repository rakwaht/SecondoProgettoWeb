<%@include file="/layout/head.jsp" %>
<%@include file="/layout/navigation.jsp" %>

<div class="main container">
    <h1>Pagina moderatore</h1>

    <table id="moderator_table_id">
        <thead>
            <tr>
                <th>Nome gruppo</th>
                <th>Amministratore</th>
                <th>Numero partecipanti</th>
                <th>Tipo</th>
                <th>Numero post</th>
                <th>Discussione</th>
                <th>Link</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="m" items="${moderatordtos}">
                <tr>
                    <td><a href="/SecondoProgettoWeb/ShowGroupServlet?id_crew=${m.crew.id}"><c:out value="${m.crew.name}" /></a></td>
                    <td>
                        <!-- AVATAR -->
                        <p>
                            <c:choose>
                                <c:when test="${empty m.crew.admin.avatar_name}">
                                    <img src="${pageContext.request.contextPath}/res/images/default_avatar.png" class="avatar" />
                                </c:when>
                                <c:otherwise>
                                    <img src="${pageContext.request.contextPath}/resource/avatar/${m.crew.admin.id}_${m.crew.admin.avatar_name}" class="avatar" />
                                </c:otherwise>
                            </c:choose>
                        </p>
                        <c:out value="${m.crew.admin.username}" />
                    </td>
                    <td><c:out value="${fn:length(m.users)}" /></td>
                    <td>
                        <c:choose>
                            <c:when test="${m.crew.crew_private}">
                                Privato
                            </c:when>
                            <c:otherwise>
                                Pubblico
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td><c:out value="${fn:length(m.posts)}" /></td>
                    <td>
                        <c:choose>
                            <c:when test="${m.crew.crew_enabled}">
                                Aperta
                            </c:when>
                            <c:otherwise>
                                Chiusa
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <c:if test="${m.crew.crew_enabled}">
                    <td><a href="/SecondoProgettoWeb/secure/CloseGroupServlet?id_crew=${m.crew.id}">Chiudi</a></td>
                    </c:if>
                    </tr>
            </c:forEach>
        </tbody>
    </table>   
</div>

<%@include file="/layout/foot.jsp" %>