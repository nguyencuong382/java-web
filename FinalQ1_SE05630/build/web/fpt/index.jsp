<%-- 
    Document   : index.jsp
    Created on : Sep 25, 2018, 4:24:42 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="fpt/style.css"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <h1>Body go here</h1>


        <form action="fpt/bankservices.jsp">
            <select name="query" id="">
                <option value="0"> Deposit </option>
                <option value="1"> Withdraw </option>
                <option value="2"> IN query </option>
            </select>
            <input type="submit" value="Go!">
        </form>

        <jsp:include page="footer.jsp"/>
    </body>
</html>
