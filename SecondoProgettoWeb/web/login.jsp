

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <form method="post" action="LoginServlet">
            Please enter your username 		
            <input type="text" name="username"/><br>		
            Please enter your password
            <input type="password" name="password"/>
             <div>
                <div style="color: #FF0000">${message}</div>
                <input type="submit" value="Login" />
                <input type="reset" value="Reset" />
            </div>			
        </form>
    </body>
</html>
