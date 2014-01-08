<%@include file="/layout/head.jsp" %>
<h1>MOD PAGE</h1>

 <table id="moderator_table_id">
    <thead>
        <tr>
            <th>Nome Gruppo</th>
            <th>Nro Partecipanti</th>
            <th>Privato</th>
            <th>Nro Post</th>
            <th>Link</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="m" items="${moderatordtos}">
            <tr>
                <td><c:out value="${m.crew.name}" /></td>
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
                <td><a href="/SecondoProgettoWeb/ShowGroupServlet?id_crew=${c.id}">Vai a Gruppo</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>   
    

<%@include file="/layout/foot.jsp" %>