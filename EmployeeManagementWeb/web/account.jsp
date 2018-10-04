<%-- 
    Document   : account.jsp
    Created on : Oct 4, 2018, 7:52:11 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:layout>
    <jsp:attribute name="body">

        <div class="list-account">

            <c:if test="${empty accounts}">
                <p>Nothing to display</p>
            </c:if>

            <c:if test="${not empty accounts}">
                <table border="1" cellspacing="0">
                    <tr>
                        <th>Employee ID</th>
                        <th>Employee Name</th>
                        <th>Employee Date of Birth</th>
                        <th>Employee Gender</th>
                        <th>Employee Address</th>
                    </tr>

                    <c:forEach var="acc" items="${accounts}">
                        <tr>
                            <td>${acc.userId}</td>
                            <td>${acc.displayName}</td>
                            <td>${acc.joinedDate}</td>
                            <td>
                                <input type="checkbox" 
                                       ${acc.active ? "checked" : ""}>
                            </td>
                            <td>
                                ${acc.employeeId}
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>

    </jsp:attribute>
</t:layout>