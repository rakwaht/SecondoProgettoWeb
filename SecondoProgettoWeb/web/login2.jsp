<%-- 
    Document   : login2
    Created on : 20-dic-2013, 13.20.48
    Author     : francesco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form method="post" action="check_login2.jsp">
            username 		
            <input type="text" name="username"/><br>		
            password
            <input type="password" name="password"/>
             <div>
                <input type="submit" value="Login" />
            </div>			
        </form>
    </body>
</html>
