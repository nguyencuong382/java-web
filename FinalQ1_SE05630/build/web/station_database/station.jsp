<%-- 
    Document   : station
    Created on : Sep 18, 2018, 4:56:52 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <form action="station">
            <p>Station name</p>
            <input type="text" name="station_name" value="${param.station_name}">
            <input type="submit" value="submit">
        </form>
        
        <table border="1" cellspacing="0">
            <tr>
                <th>Station ID</th>
                <th>Station Name</th>
                <th>Train Code</th>
            </tr>
            
           
            
            <c:forEach var="station" items = "${stations}">
                <tr>
                    
                    <td>
                        <c:url value="/train/list" var="train">
                            <c:param name="stationId" value="${station.id}" />
                            <c:param name="stationName" value="${station.name}" />
                        </c:url>
                        <a href="${train}">${station.id}</a>
                    </td>
                    <td>${station.name}</td>
                    <td>
                        <ul>
                            <c:forEach var="trainStop" items="${station.trainStops}">
                                <c:url value="/train/list" var="train_stop">
                                    <c:param name="trainCode" value="${trainStop.trainCode}"/>
                                </c:url>
                                <li><a href="${train_stop}">${trainStop.trainCode}</a></li>
                            </c:forEach>
                        </ul>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
