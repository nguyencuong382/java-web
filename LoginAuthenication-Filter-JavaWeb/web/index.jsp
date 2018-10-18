<%-- 
    Document   : index
    Created on : Oct 16, 2018, 4:54:52 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <h1>Home Page (no authentication required)</h1>


        <c:if test="${not empty sessionScope.user}">
            <table border="1" cellspacing="0">
                <tr>
                    <th>Role Id</th>
                    <th>Role Name</th>
                </tr>

                <c:forEach var="role" items="${user.roles}">
                    <tr>
                        <td>${role.roleId}</td>
                        <td>${role.roleName}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>
