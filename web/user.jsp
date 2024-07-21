<%-- 
    Document   : user
    Created on : 24/01/2024, 5:06:10 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="sample.sp24.t4s4.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
        <!--Link Bootstrap-->
        <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
        <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css'>
        <!--Link CSS-->
        <link type="text/css" rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER ==null || sessionScope.LOGIN_USER.roleID ne 'US'}">
            <c:redirect url="login.html"></c:redirect>
        </c:if>
        <div class="user">
            <h1 style="color: #FF004D; padding: 30px 0">User Information</h1>
            <div class="container user-block-show-info">
                <div class="col-md-5 user-Info">
                    <p>User ID: ${sessionScope.LOGIN_USER.userID}</p>
                    <p>Full Name:${sessionScope.LOGIN_USER.fullName}</p>
                    <p>ID: ${sessionScope.LOGIN_USER.roleID}</p>
                    <p>Password: ${sessionScope.LOGIN_USER.password}</p>
                    <a class="Go_to_Link_Store" href="MainController?action=Shopping" style="text-decoration: none; ">Go to Nhan's store</a>
                </div>
                <img class="col-md-7" src="images/pngtree-circular-avatar-vector-illustration-male-people-person-male-vector-png-image_36446834.png"alt="avatar user"/>

            </div>
        </div>
        <div class="img-background" >
            <img src="images/23.76b6ac398df737076bc8fa67dc4616b8.jpg"alt="avatar user">
            <div class="color"></div>
        </div>

    </body>
    <script src="bootstrap-3.3.7-dist/js/bootstrap.js"></script>
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="bootstrap-3.3.7-dist/js/npm.js"></script>
    <script src='//cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js'></script><script  src="./script.js"></script>
    <script src="js/script.js"></script>
</html>
