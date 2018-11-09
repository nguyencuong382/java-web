<%-- 
    Document   : login
    Created on : Oct 25, 2018, 2:57:56 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
            <s:url action="LocaleActionSupport" var="en">
                <s:param name="request_locale">en_US</s:param>
            </s:url>
            <s:url action="LocaleActionSupport" var="vi">
                <s:param name="request_locale">vi_VN</s:param>
            </s:url>
            
            <p>
                <s:a href="%{en}">English</s:a>
                <s:a href="%{vi}">Tiếng Việt</s:a>
            </p>
            
            
            <s:form action="LoginActionSupport">
                <!-- resource.[ten tep properties]-->
                <s:i18n name="resource.global">
                    <s:textfield key="username" label="%{getText('login.username')}" name="username"/>
                    <s:textfield key="password" label="%{getText('login.password')}" name="password"/>
                    <s:submit value="%{getText('login.signin')}" name="submit"/>
                </s:i18n>
            </s:form>
    </body>
</html>
