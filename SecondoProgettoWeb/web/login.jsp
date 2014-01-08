<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="layout/head.jsp" %>
<c:choose>
    <c:when test="${empty user}">

        <div class="container">
            <div class="row">
                <div class="col-md-6">

                    <h1>Login</h1>
                    <form method="post" action="LoginServlet" role="form">
                        <div class="form-group">
                            <label>Please enter your username</label> 		
                            <input type="text" name="username" class="form-control"/>
                        </div>


                        <div class="form-group">
                            <label>Please enter your password</label>
                            <input type="password" name="password" class="form-control"/>
                        </div>
                        <p>
                            <a href="password_recovery.jsp">Password dimenticata?</a>
                        </p>
                        <button type="submit" class="btn btn-green btn-lg">Entra</button>
                        <button type="reset" class="btn btn-default btn-lg">Reset</button>
                    </form>

                    <!--  error message -->
                    <c:if test="${param.message_login == 'error'}">
                        <div class="alert alert-danger">
                            Username/password non esistente.
                        </div>
                    </c:if>

                </div>
                <div class="col-md-6">


                    <h1>Registration</h1>
                    <form method="post" action="LoginServlet?new_user=true" role="form">

                        <div class="form-group">
                            <label>Username</label> 		
                            <input type="text" name="username" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label>Please enter your password</label>
                            <input type="password" name="password" class="form-control"/>
                        </div>

                        <div class="form-group">
                            <label>Please confirm your password</label>
                            <input type="password" name="password-confirm" class="form-control"/>
                        </div>

                        <div class="form-group">
                            <label>Please enter your email</label>
                            <input type="email" name="email" class="form-control"/>
                        </div>

                        <button type="submit" class="btn btn-green btn-lg">Registrati</button>
                        <button type="reset" class="btn btn-default btn-lg">Reset</button>
                    </form>



                    <!-- error messages -->
                    <c:if test="${not empty param.message_registration}">
                        <div class="alert alert-danger">
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

            <br /><br /><br />
            <a href="/SecondoProgettoWeb/HomeServlet?next=groups">Vedi gruppi pubblici</a>

        </div>
    </c:when>
    <c:otherwise>
        <c:redirect url="/secure/home.jsp"/>
    </c:otherwise>
</c:choose>        
<%@include file="layout/foot.jsp" %>
