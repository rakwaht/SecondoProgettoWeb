

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="layout/head.jsp" %>
<h1>Login</h1>
<form method="post" action="LoginServlet">
    Please enter your username 		
    <input type="text" name="username"/><br/>		
    Please enter your password
    <input type="password" name="password"/>
    <div>
        <div><p>${param.message_login}</p></div>
        <input type="submit" value="Login" />
        <input type="reset" value="Reset" />
    </div>			
</form>
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
    
    <div>
        <div><p>${param.message_registration}</p></div>
        <input type="submit" value="Register" />
        <input type="reset" value="Reset" />
    </div>			
</form>
<br /><br /><br />
 <a href="/SecondoProgettoWeb/HomeServlet?next=groups">Vedi gruppi pubblici</a>
<%@include file="layout/foot.jsp" %>
