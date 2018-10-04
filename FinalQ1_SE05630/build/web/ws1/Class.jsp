<%-- 
    Document   : Class.jsp
    Created on : Sep 27, 2018, 3:54:21 PM
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
        <form action="group">
            <span>Number of class</span>
            
            <input type="text" name="numberOfClass"value="${param.numberOfClass}">
            
            <input type="submit" value="View">
            <br>
        </form>
        
        <div class="list-group">
            <c:if test="${empty groups}">
                <p>Nothing to display</p>
            </c:if>
            <c:if test="${not empty groups}">
                <table border="1" cellspacing="0">
                    <tr>
                        <th>Class ID</th>
                        <th>Class Name</th>
                        <th>Start Date</th>
                        <th>Activated</th>
                        <th></th>
                    </tr>
                    
                    <c:forEach var="group" items="${groups}">
                        <tr>
                            <td>
                                <c:url value="group/student" var="students">
                                    <c:param name="groupId" value="${group.id}" />
                                </c:url>
                                <a href="${students}">${group.id}</a>
                                
                            </td>
                            <td>${group.name}</td>
                            <td>${group.startedDate}</td>
                            <td>
                                <input type="checkbox" 
                                ${group.activated ? "checked" : ""}>
                            </td>
                            
                            <td>
                                <c:url value="group/student/add" var="addStudent">
                                    <c:param name="groupId" value="${group.id}" />
                                </c:url>
                                <a href="${addStudent}">Add more student</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>

    </body>
    
</html>
