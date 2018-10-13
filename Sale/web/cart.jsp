<%-- 
    Document   : cart
    Created on : Oct 2, 2018, 5:20:26 PM
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
        
        <c:if test="${empty sessionScope.cart.cartItems}">
            <h1>Nothing in the cart</h1>
            <a href="/Sale/product">Shopping</a>
        </c:if>
            
        <c:if test="${not empty sessionScope.cart.cartItems}">
            <table border="1" cellspacing="0">
                <tr>
                    <th>Cart Name</th>
                    <th>Quantity</th>
                    <th>Total Price</th>
                </tr>

                <c:forEach var="cartItems" items = "${sessionScope.cart.cartItems}">
                    <tr>

                        <td>${cartItems.name}</td>
                        <td>${cartItems.quantity}</td>
                        <td>
                            ${cartItems.quantity * cartItems.price}
                        </td>

                    </tr>
                </c:forEach>
            </table>

            <h3>Total: </h3>
            <p>${sessionScope.cart.totalAmount}</p>
            
            
            <a href="/Sale/product">Continue shopping</a>
            <input type="button" value="Checkout" onclick="window.location = 'checkout.jsp'">
        </c:if>

    </body>
</html>
