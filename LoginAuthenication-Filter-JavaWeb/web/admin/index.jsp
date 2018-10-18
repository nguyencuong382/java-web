<%-- 
    Document   : index
    Created on : Oct 16, 2018, 4:57:55 PM
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
        
        <jsp:include page="../menu.jsp"/>
        <h1>Hello admin ${sessionScope.user.userName}</h1>
    </body>
</html>
