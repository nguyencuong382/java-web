<%-- 
    Document   : product.jsp
    Created on : Oct 2, 2018, 4:50:34 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <a href="/Sale/cart.jsp">Cart</a>
        </div>
        
        <table border="1" cellspacing="0">
            <tr>
                <th>Product ID</th>
                <th>Product Name</th>
                <th>Unit Price</th>
                <th></th>
            </tr>

            <c:forEach var="product" items = "${products}">
                <tr>
                    <td>
                        ${product.id}
                    </td>
                    <td>${product.name}</td>
                    <td>
                        ${product.price}
                    </td>
                    <td>
                        <c:url var="add" value="/cart/add">
                            <c:param name="productId" value="${product.id}"/>
                            <c:param name="productName" value="${product.name}"/>
                            <c:param name="productPrice" value="${product.price}"/>
                        </c:url>
                        <a href="${add}">Add To Cart</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
