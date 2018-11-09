<%-- 
    Document   : station.jsp
    Created on : Sep 27, 2018, 2:51:01 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="sb" class="bean.StationBean" scope="session"/>
        
        <jsp:setProperty name="sb" property="*"/>
        
        <form action="">
            <p>Enter station name</p>
            <input type="text" name="search" value="${param.search}">
            
        </form>
            
            <c:if test="${empty sb.stations}">
                <h1>NOthing to display</h1>
            </c:if>
            <c:if test="${not empty sb.stations}">
                <table border="1" cellspacing="0">
                    <tr>
                        <th>Station Id</th>
                        <th>Station Name</th>
                    </tr>
                    <c:forEach var="station" items="${sb.stations}">
                        <tr>
                            <td>${station.id}</td>
                            <td>${station.name}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
    </body>
</html>
