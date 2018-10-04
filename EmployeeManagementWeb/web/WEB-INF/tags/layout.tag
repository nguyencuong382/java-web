<%-- 
    Document   : layout
    Created on : Oct 4, 2018, 4:54:39 PM
    Author     : Admin
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="body" fragment="true"%>

<%-- any content can be specified here e.g.: --%>
<html>
    <head>
        <title>TAG</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <jsp:include page="/nav.jsp"/>

        <!-- Can't use include directive in tag file-->
        <%--<%@include file="/nav.jsp" %>--%>

        <!--body-->
        <div class="container">
            <jsp:invoke fragment="body"></jsp:invoke>
        </div>


    </body>
</html>