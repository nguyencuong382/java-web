<%-- 
    Document   : user
    Created on : Oct 23, 2018, 4:49:07 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome <s:property value="userName"/></h1>
        <h1>Stack value = <s:property value="%{user.userName}"/></h1>
        
        <table border="1" cellspacing="0">
            <tr>
                <th>UserName</th>
                <th>Password</th>
            </tr>
            
            <s:iterator value="users">
                <tr>
                    <td><s:property value="userName"/></td>
                    <td><s:property value="passWord"/></td>
                </tr>
            </s:iterator>
        </table>
    </body>
</html>
