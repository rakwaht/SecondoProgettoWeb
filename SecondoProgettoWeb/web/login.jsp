

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="layout/head.jsp" %>


<c:choose>
    <c:when test="${empty user}">

        <h1>Login</h1>
        <form method="post" action="LoginServlet">
            Please enter your username 		
            <input type="text" name="username"/><br/>		
            Please enter your password
            <input type="password" name="password"/><br />
            <a href="password_recovery.jsp">Password dimenticata?</a><br />

            <button type="submit">Entra</button>
            <button type="reset">Reset</button>
        </form>

        <!--  error message -->
        <p>
            <c:if test="${param.message_login == 'error'}">
                Username/password non esistente.
            </c:if>
        </p>


        <h1>Registration</h1>
        <form method="post" action="LoginServlet?new_user=true">
            Please enter your username 		
            <input type="text" name="username"/><br/>		
            Please enter your password
            <input type="password" name="password"/><br/>
            Please confirm your password
            <input type="password" name="password-confirm"/><br/>
            Please enter your email
            <input type="email" name="email"/><br/>

            <button type="submit">Registrati</button>
            <button type="reset">Reset</button>
        </form>

        <!-- error messages -->
        <p>
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
        </p>


        <br /><br /><br />
        <a href="/SecondoProgettoWeb/HomeServlet?next=groups">Vedi gruppi pubblici</a>

    </c:when>
    <c:otherwise>
        <c:redirect url="/secure/home.jsp"/>
    </c:otherwise>
</c:choose>


<%@include file="layout/foot.jsp" %>
