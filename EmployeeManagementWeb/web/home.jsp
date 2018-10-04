<%-- 
    Document   : home.jsp
    Created on : Oct 4, 2018, 7:14:19 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:layout>
    <jsp:attribute name="body">

        <div class="list-employee">

            <c:if test="${empty employees}">
                <p>Nothing to display</p>
            </c:if>

            <c:if test="${not empty employees}">
                <table border="1" cellspacing="0">
                    <tr>
                        <th>Employee ID</th>
                        <th>Employee Name</th>
                        <th>Employee Date of Birth</th>
                        <th>Employee Gender</th>
                        <th>Employee Address</th>
                    </tr>

                    <c:forEach var="e" items="${employees}">
                        <tr>
                            <td>
                                <c:url value="/employee/account" var="acc">
                                    <c:param name="employeeId" value="${e.employeeId}" />
                                </c:url>
                                <a href="${acc}">${e.employeeId}</a>

                            </td>
                            <td>${e.employeeName}</td>
                            <td>${e.employeeDOB}</td>
                            <td>
                                <input type="checkbox" 
                                       ${e.employeeGender ? "checked" : ""}>
                            </td>

                            <td>
                            ${e.employeeAddress}
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>

    </jsp:attribute>
</t:layout>