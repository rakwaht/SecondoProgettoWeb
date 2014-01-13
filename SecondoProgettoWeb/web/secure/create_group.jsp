<%@include file="/layout/head.jsp" %>
<%@include file="/layout/navigation.jsp" %>

<div class="main container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="box">
                <h1>Crea qui il tuo fantastico gruppo!</h1>
                <c:if test="${param.message_newgroup == 'empty_params'}">
                    <p>Nome e/o descrizione obbligatorie!</p>
                </c:if>

                <form method="post" action="NewGroupServlet" role="form">
                    <div class="form-group">
                        <label>Nome</label>	
                        <input type="text" name="name" class="form-control" />	
                    </div>
                    <div class="form-group">
                        <label>Descrizione</label>	
                        <textarea type="password" name="description" class="form-control" ></textarea>
                    </div>
                    <input type='radio' name='type' value='public' checked='checked' /> pubblico<br /> 
                    <input type='radio' name='type' value='private' /> privato
                    <br /><br /> 
                    <c:if test="${not empty users}">
                        <p>Invita utenti</p>
                        <ul class="list-group">
                            <c:forEach var="u" items="${users}">
                                <li class="list-group-item">
                                    <input type="checkbox" name="users" value="${u.id}" /> ${u.username}
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                   
                    <button type="submit" class="btn btn-green">Crea</button>
                    <button type="reset" class="btn btn-default">Reset</button>
                </form>
            </div>
        </div>
    </div>
</div>
<%@include file="/layout/foot.jsp" %>
