<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="layout/head.jsp" %>
<c:choose>
    <c:when test="${empty user}">

        <div class="index-header">

            <div class="container" style="padding-bottom: 40px">

                <div class="row">
                    <div class="col-md-7 center">
                        <div class="app-name white"><i class="fa fa-coffee m-r"></i>Coffee<span class="green">ClassRoom</span><br /><i class="fa fa-bolt purple"></i><i class="fa fa-bolt purple"></i></div>
                        <br />
                        <a href="/SecondoProgettoWeb/HomeServlet?next=groups">
                            <button type="submit" class="btn btn-green btn-lg">Gruppi pubblici</button>
                        </a>
                    </div>
                    <div class="col-md-5">

                        <div class="box-i">
                            <form method="post" action="LoginServlet?new_user=true" role="form">

                                <div class="form-group">
                                    <label>Username</label> 		
                                    <input type="text" name="username" class="form-control" />
                                </div>
                                <div class="form-group">
                                    <label>Password</label>
                                    <input type="password" name="password" class="form-control"/>
                                </div>

                                <div class="form-group">
                                    <label>Conferma password</label>
                                    <input type="password" name="password-confirm" class="form-control"/>
                                </div>

                                <div class="form-group">
                                    <label>Email</label>
                                    <input type="email" name="email" class="form-control"/>
                                </div>

                                <button type="submit" class="btn btn-green btn-lg">Registrati</button>
                                <button type="reset" class="btn btn-default btn-lg">Reset</button>
                            </form>

                            <!-- error messages -->
                            <c:if test="${not empty param.message_registration}">
                                <div class="alert alert-danger" style="margin-top: 20px">
                                    <c:choose>
                                        <c:when test="${param.message_registration == 'empty_usr_psw'}">
                                            Username e password obbligatorie!
                                        </c:when>
                                        <c:when test="${param.message_registration == 'usr_already_exists'}">
                                            Username già esistene!
                                        </c:when>
                                        <c:when test="${param.message_registration == 'psw_error'}">
                                            Le password devono coincidere!
                                        </c:when>
                                        <c:when test="${param.message_registration == 'email_not_valid'}">
                                            L'email deve essere un email valida!
                                        </c:when>
                                        <c:when test="${param.message_registration == 'email_elready_exists'}">
                                            Email già registrata!
                                        </c:when>
                                    </c:choose>
                                </div>
                            </c:if>

                        </div>
                    </div>
                </div>

            </div>
        </div>
        <div class="section gray-bg center">
            <form method="post" action="LoginServlet" class="form-inline" role="form">
                <div class="form-group">
                    <input type="text" name="username" placeholder="username" class="form-control"/>
                </div>
                <div class="form-group">
                    <input type="password" name="password" placeholder="password" class="form-control"/>
                </div>
                <button type="submit" class="btn btn-green">Entra</button>
                <!--  error message -->
                <p>
                    <c:if test="${param.message_login == 'error'}">
                        Username/password non esistente!
                    </c:if>
                    <a href="password_recovery.jsp"><i class="fa fa-key m-r"></i>Password dimenticata?</a>
                </p>
            </form>
        </div>
        <div class="section purple-bg center" style="min-height: 200px">
            <h2 class="white">Just gotta have a Coffee.<i class="fa fa-coffee m-l"></i></h2>
        </div>
        
    </c:when>
    <c:otherwise>
        <c:redirect url="/secure/home.jsp"/>
    </c:otherwise>
</c:choose>        
<%@include file="layout/foot.jsp" %>
