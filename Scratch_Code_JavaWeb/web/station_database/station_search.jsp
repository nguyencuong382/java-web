<%-- 
    Document   : station_search
    Created on : Sep 20, 2018, 4:22:11 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <form action="station">
            Select a station name
            <select name="stationId" onchange="document.forms[0].submit()">
                <option value="-1">selected</option>
                <c:forEach var="station" items="${stations}">
                    <option value="${station.id}"
                            ${station.id == param.stationId ? "selected" : ""} > ${station.name}
                    </option>
                </c:forEach>
            </select>
        </form>

        <c:if test="${empty trainStops}">
            <h1>Nothing to display</h1>
        </c:if>
        <c:if test="${not empty trainStops}">
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
        </c:if>


    </body>
</html>
