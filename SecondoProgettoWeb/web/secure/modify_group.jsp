<%@include file="/layout/head.jsp" %>
<%@include file="/layout/navigation.jsp" %>

<div class="main container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="box">
                <h1>Modifica gruppo <a href="/SecondoProgettoWeb/ShowGroupServlet?id_crew=${crew.id}"><c:out value="${crew.name}"></c:out></a></h1>

                <!-- error messages -->
                <c:if test="${param.message_editgroup == 'empty_params'}">
                    <div class="alert alert-danger">
                        Nome e/o descrizione obbligatorie!               
                    </div>
                </c:if>
                <c:if test="${param.message_editgroup == 'html'}">
                    <div class="alert alert-danger">
                       E' presente HTML nei campi!              
                    </div>
                </c:if>

                <form method="post" action="ModifyGroupServlet?id_crew=${crew.id}" role="form">  
                    <div class="form-group">
                        <label>Nome</label>
                        <input type='text' name='title'value='${crew.name}' class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>Descrizione</label>
                        <textarea type='text' name='description' class="form-control"><c:out value="${crew.description}"></c:out></textarea>
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
                                    <input type='checkbox' name='followers'value='${followers.id}'/> <c:out value="${followers.username}"></c:out>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                    <c:if test="${not empty not_followers}">
                        <p>Invita</p>
                        <ul class="list-group">
                            <c:forEach var="not_followers" items="${not_followers}">
                                <li class="list-group-item">
                                    <input type='checkbox' name='not_followers'value='${not_followers.id}'/> <c:out value="${not_followers.username}"></c:out><br/>
                                </li>
                            </c:forEach>
                        </c:if>        
                        <c:if test="${not empty invited}">
                            <br /><p>Già invitati</p>
                            <ul class="list-group">
                                <c:forEach var="invited" items="${invited}">
                                    <li class="list-group-item">
                                        <c:out value="${invited.username}"></c:out>
                                    </li>
                                </c:forEach>
                            </ul>
                        </c:if>
                        <p style="margin-top: 20px">
                            <button type='submit' class="btn btn-green">Modifica</button>
                            <button type="reset" class="btn btn-default">Reset</button>
                        </p>
                </form>

            </div>
        </div>
    </div>
</div>
<%@include file="/layout/foot.jsp" %>

