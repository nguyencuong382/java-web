<%-- 
    Document   : doLogin.jsp
    Created on : Sep 25, 2018, 5:15:00 PM
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
        <!--scope: session, request, page, application-->
        <jsp:useBean id="b" class="bean.LoginBean" scope="session"/>
        <jsp:setProperty name="b" param="userName" property="userName"/>
        <jsp:setProperty name="b" param="passWord" property="passWord"/>
        
        <p>User name: <jsp:getProperty name="b" property="userName"/></p>
        <p>PassWord: ${b.passWord}</p>
    </body>
</html>
