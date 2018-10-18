<%-- 
    Document   : menu
    Created on : Oct 16, 2018, 4:47:28 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    </head>
    <body>
        <a href="/LoginAuthenication-Filter-JavaWeb/index.jsp">Home</a>
        <a href="/LoginAuthenication-Filter-JavaWeb/admin/index.jsp">Admin</a>
        <a href="/LoginAuthenication-Filter-JavaWeb/user/index.jsp">User</a>

        <c:if test="${empty sessionScope.user}">
            <a href="/LoginAuthenication-Filter-JavaWeb/login.jsp">Login</a>
        </c:if>

        <c:if test="${not empty sessionScope.user}">
            Welcome ${sessionScope.user.userName}
            <a href="/LoginAuthenication-Filter-JavaWeb/logout">Log out</a>
        </c:if>
    </body>
</html>
