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
        <c:forEach var="c" items="${crews}">
            <tr>
                <td><c:out value="${c.name}" /></td>
                <td>todo</td>
                <td>
                    <c:choose>
                        <c:when test="${c.crew_private}">
                            Privato
                        </c:when>
                        <c:otherwise>
                            Pubblico
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>todo</td>
                <td><a href="/SecondoProgettoWeb/ShowGroupServlet?id_group=${c.id}">Vai a Gruppo</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>   
    

<%@include file="/layout/foot.jsp" %>