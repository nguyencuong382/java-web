<%-- 
    Document   : login.jsp
    Created on : Sep 25, 2018, 5:13:01 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="doLogin.jsp" method="POST">
            <p>UserName</p> <input type="text" name="userName">
            <p>Password</p> <input type="text" name="passWord">
            <input type="submit" value="Sign in">
        </form>
        
        <p>
            <a href="SessionServlet">SessionServlet</a>
        </p>
    </body>
</html>
