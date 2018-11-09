<%-- 
    Document   : Student.jsp
    Created on : Sep 27, 2018, 4:29:13 PM
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
        
        <div class="list-student">
            <c:if test="${empty students}">
                <p>Nothing to display</p>
            </c:if>
            <c:if test="${not empty students}">
                <h1>List of student in class ${param.groupId}</h1>
                <table border="1" cellspacing="0">
                    <tr>
                        <th>Student ID</th>
                        <th>Student Name</th>
                        <th>Class Id</th>
                        <th>Is Male</th>
                        <th>Date Of Birth</th>
                    </tr>
                    
                    <c:forEach var="student" items="${students}">
                        <tr>
                            <td>
                                <a href="">${student.id}</a>
                                
                            
                            </td>
                            <td>${student.name}</td>
                            <td>${student.classId}</td>
                            <td>
                                <input type="checkbox" 
                                ${student.gender ? "checked" : ""}>
                            </td>
                            <td>${student.DOB}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
    </body>
</html>
