<%-- 
    Document   : trainstop_traincode
    Created on : Sep 24, 2018, 11:42:21 PM
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
        <h2>Info of ${param.trainCode}</h2>

        <table border="1" cellSpacing = "0">
            <tr>
                <th>Train Code</th>
                <th>Station Id</th>
                <th>Departure Time</th>
                <th>Arrival Time</th>
                <th>Stop Order</th>
            </tr>

            <c:forEach var="trainStop" items="${trainStops}">
                <tr>
                    <td>${trainStop.trainCode}</td>
                    <td>${trainStop.stationId}</td>
                    <td>${trainStop.departureTime}</td>
                    <td>${trainStop.arrivalTime == null ? "N/A" : trainStop.arrivalTime}</td>
                    <td>${trainStop.stopOrder}</td>
                </tr>
            </c:forEach>

        </table>
        
         <a href="/FinalQ1_SE05630/station">Station</a>
    </body>
</html>
