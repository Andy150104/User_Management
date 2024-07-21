<%-- 
    Document   : shopping2
    Created on : 05/03/2024, 11:20:30 PM
    Author     : admin
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="sample.sp24.t4s4.shopping.ProductError"%>
<%@page import="sample.sp24.t4s4.shopping.Computer"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nhan store</title>
    </head>
    <body>
        <div><h1>Super Computer Paradise</h1></div>
        <form action="SearchProductController">
            <input type="text" name="SearchProduct"/>
            <input type="submit" name="action" value="Search"/>
        </form>

        <table border="1">
    <thead>
        <tr>
            <th>No</th>
            <th>Product ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Add</th>
        </tr>
    </thead>
    <tbody>
        <c:if test="${sessionScope.LIST_PRODUCT ne null && not empty sessionScope.LIST_PRODUCT}">
            <c:forEach var="computer" varStatus="counter" items="${sessionScope.LIST_PRODUCT}">
                <form action="MainController" method="post">
                <tr>
                        <td>${counter.count}</td>
                        <td><input type="text" name="id" readonly="" value="${computer.id}"/></td>
                        <td><input type="text" name="name" value="${computer.name}"/></td>
                        <td><input type="text" name="price" value="<fmt:formatNumber type="number" value="${computer.price}"></fmt:formatNumber>"/></td>
                        <td>
                            <select name="quantity">
                                <option value="1">1 Item</option>
                                <option value="2">2 Items</option>
                                <option value="3">3 Items</option>
                                <option value="4">4 Items</option>
                                <option value="5">5 Items</option>
                                <option value="10">10 Items</option>
                            </select>
                        </td>
                        <td><input type="submit" name="action" value="Add"/></td>
                </tr>
               </form>
            </c:forEach>
        </c:if>
    </tbody>
</table>
        <c:if test="${requestScope.MESSAGE ne null}">
            <h3>${requestScope.MESSAGE}</h3>
        </c:if>


    <form action="MainController">
        <input type="submit" name="action" value="View"/>
    </form>
</body>
</html>
