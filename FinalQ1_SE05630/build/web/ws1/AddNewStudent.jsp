<%-- 
    Document   : AddNewStudent
    Created on : Oct 2, 2018, 1:24:14 PM
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
        <form action="" method="POST">
            <p>Student Name</p>
            <input type="text" name="studentName">
            <p>class ID</p>
            <input type="text" name="classId">
            <p>Gender</p>
            <input type="checkbox" name="gender">
            <p>Date of Birth</p>
            <input type="date" name="DOB">
            
            <input type="submit" value="SAVE">
        </form>
    </body>
</html>
