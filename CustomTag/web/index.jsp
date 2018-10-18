<%-- 
    Document   : index
    Created on : Oct 11, 2018, 3:07:38 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" uri="/WEB-INF/tlds/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%! String s = "the fox"; %>
        
        <mt:Show upper="true" repeat="3">
            <%=s%>
        </mt:Show>
        <form>
            <input type="text" name="path" value="${param.path}">
            <input type="submit" value="Browse">

            <table>
                <tr>
                    <th>Name</th>
                    <th>Size</th>
                </tr>
                <mt:Explorer path="${param.path}">
                    <tr>
                        <td>${path}</th>
                        <td>${size}</th>
                    </tr>
                </mt:Explorer>
            </table>

        </form>


        <h1>Today is <mt:Today pattern="yyyy-MM-dd"></mt:Today></h1>
            <table>
                <tr>
                    <th>INdex</th>
                    <th>Unicode</th>
                </tr>
            <mt:Unicode start="1041" end="1162">
                <tr>
                    <td>${index}</td>
                    <td>${unicode}</td>
                </tr>
            </mt:Unicode>
        </table>

    </body>
</html>
