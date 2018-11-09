<%-- 
    Document   : detail
    Created on : Oct 22, 2018, 10:51:25 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    </head>
    <body>
        <div class="list-orders">
            <c:if test="${empty ordersDetail    }">
                <p>Nothing to display</p>
            </c:if>
            <c:if test="${not empty ordersDetail}">

                <table border="1" cellspacing="0">
                    <tr>
                        <th>Ma don hang</th>
                        <th>Ma san pham</th>
                        <th>Trang</th>
                        <th>So luong</th>
                        <th>Gia ban</th>
                        <th>Thanh Tien</th>
                    </tr>

                    <c:forEach var="order" items="${ordersDetail}">
                        <tr>
                            <td>
                                ${order.orderId}
                            </td>
                            <td>${order.itemCode}</td>
                            <td>
                                ${order.page}</td>
                            <td>${order.quantity}</td>
                            <td>${order.price}
                            </td>
                            <td>${order.quantity * order.price}</td>

                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
    </body>
</html>
