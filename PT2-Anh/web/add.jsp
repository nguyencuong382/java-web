<%-- 
    Document   : add
    Created on : Oct 22, 2018, 8:45:08 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    </head>
    <style>
        div[class^="col"] > div {
            background-color: #c9e0b4;
            padding: 5px;
            height: 100%;
        }

        .marg {
            padding-left: 10px;
            padding-right: 10px;
        }

        .container {
            max-width: 600px;
        }

        .checkbox > label {
            display: inline-flex;
            align-items: center;
        }

        .checkbox input {
            margin-right: 5px;
        }

        h6, p {
            margin-bottom: 0px;
        }

    </style>
    <body>
        <div class="container">
            <form action="/PT2-Anh/add" method="POST">
                <div class="row">
                    <div class="col-3">
                        <div>
                            <h6>PAYMENT</h6>
                        </div>
                    </div>
                    <div class="col-6 marg">
                        <div>
                            <div class="row">
                                <div class="col-4">
                                    <div>
                                        <div class="checkbox">
                                            <label><input type="checkbox" value="" name="cash"><h6>CASH</h6></label>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-8">
                                    <div>
                                        <h6>CURRENCY</h6>
                                        <div style="display: inline-flex">
                                            <div style="margin-right: 10px">
                                                <div class="checkbox">
                                                    <label><input type="checkbox" value="" name="euro" checked><p>Euro</p></label>
                                                </div>

                                                <div class="checkbox">
                                                    <label><input type="checkbox" value="" name="usd"><p>USD</p></label>
                                                </div>
                                            </div>

                                            <div>
                                                <div class="checkbox">
                                                    <label><input type="checkbox" value="" name="yen"><p>Yen</p></label>
                                                </div>

                                                <div class="checkbox">
                                                    <label><input type="checkbox" value="" name="other"><p>OTHER</p></label>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-3">
                        <div>
                            <div class="checkbox">
                                <label><input type="checkbox" value="" name="card"><h6>CARD</h6></label>
                            </div>
                        </div>
                    </div>
                </div>


                <table class="table">
                    <thead>
                        <tr>
                            <th>ITEM CODE</th>
                            <th>PAGE</th>
                            <th>QUANTITY</th>
                            <th>SEAT NO</th>
                            <th>PRICE</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <select name="itemCode" onchange="changeItem()" id="opt">
                                    <c:forEach var="item" items="${items}">
                                        <option value="${item.itemCode}">${item.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td><input type="text" class="form-control" name="page"></td>
                            <td><input type="text" class="form-control" name="quantity" ></td>
                            <td><input type="text" class="form-control" name="seatNo" ></td>
                            <td><span id="price">${items.get(0).price}</span></td>
                        </tr>      

                    </tbody>
                </table>
                
                <input type="submit" value="Buy">
            </form>
        </div>


        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script>
                                function changeItem() {
                                    $("#price").load("/PT2-Anh/item?itemCode=" + $("#opt").val());
                                }
        </script>
    </body>
</html>
