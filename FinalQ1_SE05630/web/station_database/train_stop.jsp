<%-- 
    Document   : train_stop
    Created on : Sep 20, 2018, 3:07:55 PM
    Author     : Admin
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body> 
        <h1>Information of Statation ${param.name}, id ${param.id}</h1>
        
        <table border="1" cellspacing="0">
            <tr>
                <th>Train code</th>
                <th>Station id</th>
                <th>Departure time</th>
                <th>Arrival time</th>
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
        
        <a href="/FinalQ1_SE05630/StationController">Station</a>
    </body>
</html>
