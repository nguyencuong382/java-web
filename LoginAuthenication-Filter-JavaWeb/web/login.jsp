<%-- 
    Document   : login
    Created on : Oct 16, 2018, 4:56:05 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <style>
            .err {
                color: red;
            }
        </style>
    </head>
    <body>
        
        <p class="err">${loginError}</p>
        
        <form action="LoginServlet" method="POST">
            <p>User name</p>
            <input type="text" name="userName"/>
            <p>Password</p>
            <input type="password" name="passWord"/>
            <input type="submit" value="Sign in">
        </form>
    </body>
</html>
