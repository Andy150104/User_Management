<%-- 
    Document   : viewCart
    Created on : 02/03/2024, 3:13:38 PM
    Author     : admin
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="sample.sp24.t4s4.shopping.Computer"%>
<%@page import="sample.sp24.t4s4.shopping.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
    </head>
    <body>
        <h1>Your shopping cart</h1>
        <c:set var="total" value="0"></c:set>
        <c:if test="${sessionScope.CART ne null}">
            <c:if test="${not empty sessionScope.CART}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                            <th>Edit</th>
                            <th>Remove</th>
                            <th>Message</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="computer" varStatus="counter" items="${sessionScope.CART.cart}">
                            <c:set var="total" value="${total +(computer.value.quantity*computer.value.price)}"></c:set>
                            <form action="MainController">
                                <tr>
                                    <td>${counter.count}</td>
                                <td>
                                    <input type="text" name="id" value="${computer.value.id}" readonly="" />
                                </td>
                                <td>${computer.value.name}</td>
                                <td><fmt:formatNumber type="number" value="${computer.value.price}"></fmt:formatNumber></td>
                                <td>
                                    <input type="number" name="Quantity" value="${computer.value.quantity}" required=""min="1"/>
                                </td>
                                <td>
                                    <input type="hidden" name="total" value="${computer.value.quantity*computer.value.price}"/>
                                    <fmt:formatNumber type="number" value="${computer.value.quantity*computer.value.price}"></fmt:formatNumber>
                                </td>
                                <td>
                                    <input type="submit" name="action" value="Edit"/>
                                </td>
                                <td>
                                    <input type="submit" name="action" value="Remove"/>
                                </td>
                                <td>
                                    <c:if test="${requestScope.MESSAGE_ERROR_CHECK_OUT ne null && not empty requestScope.MESSAGE_ERROR_CHECK_OUT}">
                                        <c:forEach var="productError" items="${requestScope.MESSAGE_ERROR_CHECK_OUT}">
                                            <c:if test="${productError.id eq computer.value.id}">
                                                ${productError.errorMessage}
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
<!--            <h2>Total: ${total}</h2>-->
            <h2>Total: <fmt:formatNumber type="number" value="${total}"></fmt:formatNumber> VNĐ</h2>
        </c:if>
    </c:if>
        <c:if test="${requestScope.MESSAGE_CHECKOUT ne null}">
            <h2>${requestScope.MESSAGE_CHECKOUT}</h2>
    </c:if>
    <form action="MainController">
        <input type="hidden" name="total" value="${total}"/>
        <input type="submit" name="action" value="Checkout"/>
    </form>
    <a href="shopping2.jsp">Add more...</a>
</body>
</html>
