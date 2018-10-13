<%-- 
    Document   : productPaging
    Created on : Oct 9, 2018, 4:40:48 PM
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

        <form>
            Show entries
            <select name="pageSize" id="" onchange="this.form.submit();">
                <option value="5" ${param.pageSize == 5? "selected" : ""}>5</option>
                <option value="10" ${param.pageSize == 10? "selected" : ""}>10</option>
                <option value="15" ${param.pageSize == 15? "selected" : ""}>15</option>
            </select>
        </form>

        <div>
            <jsp:useBean id="p" class="bean.ProductBean" scope="session"/>
            <jsp:setProperty name="p" property="*"/>

            <table border="1" cellspacing="0">
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Unit Price</th>
                </tr>

                <c:forEach var="product" items="${p.products}">
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

            <div>
                <c:forEach var="i" begin="1" end="${p.pages}" step="1">
                    <c:url var="next" value="/productPaging.jsp">
                        <c:param name="page" value="${i}"/>
                        <c:param name="pageSize" value="${param.pageSize}"/>
                    </c:url>

                    <a href="${next}">${i}</a>
                </c:forEach>
            </div>
        </div>


    </body>
</html>
