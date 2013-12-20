
<%@page contentType="text/html" pageEncoding="UTF-8"%>

  

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Benvenuto, ${user.username} ! </h1><br />
        psw: ${user.password}<br />
        email:${user.email}<br />
        login date:${user.login_date}<br />
        avatar:${user.avatar_name}<br />
        id:${user.id}<br />
    </body>
</html>
