<%@include file="/layout/head.jsp" %>
<%@include file="/layout/navigation.jsp" %>

<div class="main container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="box">
                <h1>Modifica gruppo ${crew.name}</h1>
                <a href="/SecondoProgettoWeb/ShowGroupServlet?id_crew=${crew.id}">Torna al gruppo</a>

                <form method="post" action="ModifyGroupServlet?id_crew=${crew.id}" role="form">  
                    <div class="form-group">
                        <label>Nome</label>
                        <input type='text' name='title'value='${crew.name}' class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>Descrizione</label>
                        <textarea type='text' name='description' class="form-control">${crew.description}</textarea>
                    </div>

                    <c:choose>
                        <c:when test="${crew.crew_private}">
                            <input type='radio' name='type' value='private' checked='checked' /> privato<br />
                            <input type='radio' name='type' value='public' /> pubblico 
                        </c:when>
                        <c:otherwise>
                            <input type='radio' name='type' value='private' /> privato<br />
                            <input type='radio' name='type' value='public' checked='checked' /> pubblico 
                        </c:otherwise>
                    </c:choose>

                    <br /><br /> 

                    <c:if test="${not empty followers}">
                        <p>Elimina</p>
                        <ul class="list-group">
                            <c:forEach var="followers" items="${followers}">
                                <li class="list-group-item">
                                    <input type='checkbox' name='followers'value='${followers.id}'/> ${followers.username}
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                    <c:if test="${not empty not_followers}">
                        <p>Invita</p>
                        <ul class="list-group">
                            <c:forEach var="not_followers" items="${not_followers}">
                                <li class="list-group-item">
                                    <input type='checkbox' name='not_followers'value='${not_followers.id}'/> ${not_followers.username}<br/>
                                </li>
                            </c:forEach>
                        </c:if>        
                        <c:if test="${not empty invited}">
                            <br /><p>Già invitati</p>
                            <ul class="list-group">
                                <c:forEach var="invited" items="${invited}">
                                    <li class="list-group-item">
                                        ${invited.username}
                                    </li>
                                </c:forEach>
                            </ul>
                        </c:if>

                        <button type='submit' class="btn btn-green">Modifica</button>
                        <button type="reset" class="btn btn-default">Reset</button>
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
</div>
<%@include file="/layout/foot.jsp" %>

