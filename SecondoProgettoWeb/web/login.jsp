

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="layout/head.jsp" %>
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
<%@include file="layout/foot.jsp" %>
