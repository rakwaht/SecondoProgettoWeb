<%@include file="/layout/head.jsp" %>
<%@include file="/layout/navigation.jsp" %>

<div class="main container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h1>Modifica gruppo ${crew.name}</h1>
            <a href="/SecondoProgettoWeb/ShowGroupServlet?id_crew=${crew.id}">Torna al gruppo</a>

            <form method="post" action="ModifyGroupServlet?id_crew=${crew.id}" role="form">  
                <div class="form-group">
                    <label>Nome</label>
                    <input type='text' name='title'value='${crew.name}' class="form-control" />
                </div>
                <div class="form-group">
                    <label>Descrizione</label>
                    <textarea type='text' name='description' class="form-control">${crew.description}</textarea><br/>
                </div>

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
                    Elimina<br/>
                    <c:forEach var="followers" items="${followers}">
                        <input type='checkbox' name='followers'value='${followers.id}'/> ${followers.username}<br/>
                    </c:forEach>
                </c:if>
                <c:if test="${not empty not_followers}">
                    Invita<br/>
                    <c:forEach var="not_followers" items="${not_followers}">
                        <input type='checkbox' name='not_followers'value='${not_followers.id}'/> ${not_followers.username}<br/>
                    </c:forEach>
                </c:if>        
                <c:if test="${not empty invited}">
                    <c:forEach var="invited" items="${invited}">
                        ${invited.username} già invitato!<br />
                    </c:forEach>
                </c:if>
                <button type='submit' class="btn btn-green">Modifica</button>
            </form>

            <!-- error messages -->
            <p>
                <c:choose>
                    <c:when test="${param.message_editgroup == 'empty_params'}">
                        Nome e/o descrizione obbligatorie!
                    </c:when>
                </c:choose>
            </p>

        </div>
    </div>
</div>
<%@include file="/layout/foot.jsp" %>

