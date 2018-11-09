<%-- 
    Document   : bankservices.jsp
    Created on : Sep 25, 2018, 4:53:01 PM
    Author     : Admin
--%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String query = request.getParameter("query");
            if (query.equals("0")){%>
            
            <jsp:forward page="deposit.jsp">
                <jsp:param name="date" value="<%=new Date()%>"/>
                <jsp:param name="type" value="deposit"/>
            </jsp:forward>
            <%}
        %>
    </body>
</html>
