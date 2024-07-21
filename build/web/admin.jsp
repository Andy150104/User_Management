<%-- 
    Document   : admin
    Created on : 24/01/2024, 5:06:03 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="sample.sp24.t4s4.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <!--Link Bootstrap-->
        <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
        <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css'>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <!--Link CSS-->
        <link type="text/css" rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
        <script src='https://unpkg.com/feather-icons'></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
    </head>
    <body>
        <div class="admin-body-page">

            <!--Navigate bar-->
            <div class="navbar">
                <div class="nav-container">
                    <div class="hamburger-lines">
                        <span class="line line1"><p style="opacity: 0;">.</p></span>
                        <span class="line line2"><p style="opacity: 0;">.</p></span>
                        <span class="line line3"><p style="opacity: 0;">.</p></span>
                    </div>
                </div>
                <ul class="nav-items">
                    <li class="navbar__item"><a href="#" class="navbar__link"><i data-feather="home"></i><span>Home</span></a>
                    </li>
                    <li class="navbar__item"><a href="#" class="navbar__link"><i
                                data-feather="message-square"></i><span>Messages</span></a></li>
                    <li class="navbar__item"><a href="#" class="navbar__link"><i
                                data-feather="users"></i><span>Customers</span></a></li>
                    <li class="navbar__item"><a href="#" class="navbar__link"><i
                                data-feather="folder"></i><span>Projects</span></a></li>
                </ul>
            </div>

            <!--TABLE BODY-->

            <div class="container">
                <c:if test="${sessionScope.LOGIN_USER==null || sessionScope.LOGIN_USER.roleID ne 'AD'}">
                    <c:redirect url="login.html"></c:redirect>
                </c:if>
                <!--HEADER-->
                <div class="header-admin-page col-md-12">
                    <div class="search-place">
                        <form action="SearchController">
                            <div class="search-place-input">
                                <input type="text" name="search" value="${param.search}"/>
                                <div>
                                    <label for="searchInput" >
                                        <input type="submit" name="action" value="Search"/>
                                        <span class="search-icon">
                                            <i class="fa fa-search"></i>
                                        </span>
                                    </label>
                                </div>
                            </div>
                        </form>
                    </div>
                    <!--Logout and WelCome-->
                    <div class="Logout-Welcome-place">
                        <div class="Welcome-h1"><h1>Welcome: ${sessionScope.LOGIN_USER.fullName}</h1></div>
                        <div class="Logout">
                            <div>
                                <hr style="border: none; height: 0.8px; background-color: gray; margin: 15px 0 10px 0;"> 
                                <p>
                                    <span class="col-md-3">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" viewBox="0 0 24 24">
                                        <path fill="currentColor" d="M12 19.2c-2.5 0-4.71-1.28-6-3.2c.03-2 4-3.1 6-3.1s5.97 1.1 6 3.1a7.232 7.232 0 0 1-6 3.2M12 5a3 3 0 0 1 3 3a3 3 0 0 1-3 3a3 3 0 0 1-3-3a3 3 0 0 1 3-3m0-3A10 10 0 0 0 2 12a10 10 0 0 0 10 10a10 10 0 0 0 10-10c0-5.53-4.5-10-10-10"/>
                                        </svg>
                                    </span>
                                    Profile
                                </p>
                            </div>
                            <div><p>
                                    <span class="col-md-3">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" viewBox="0 0 24 24">
                                        <path fill="currentColor" d="M4 4h16v12H5.17L4 17.17zm0-2c-1.1 0-1.99.9-1.99 2L2 22l4-4h14c1.1 0 2-.9 2-2V4c0-1.1-.9-2-2-2zm2 10h12v2H6zm0-3h12v2H6zm0-3h12v2H6z"/>
                                        </svg>
                                    </span>
                                    Message
                                </p></div>
                            <div>
                                <p>
                                    <span class="col-md-3">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" viewBox="0 0 24 24">
                                        <path fill="currentColor" d="M19.9 12.66a1 1 0 0 1 0-1.32l1.28-1.44a1 1 0 0 0 .12-1.17l-2-3.46a1 1 0 0 0-1.07-.48l-1.88.38a1 1 0 0 1-1.15-.66l-.61-1.83a1 1 0 0 0-.95-.68h-4a1 1 0 0 0-1 .68l-.56 1.83a1 1 0 0 1-1.15.66L5 4.79a1 1 0 0 0-1 .48L2 8.73a1 1 0 0 0 .1 1.17l1.27 1.44a1 1 0 0 1 0 1.32L2.1 14.1a1 1 0 0 0-.1 1.17l2 3.46a1 1 0 0 0 1.07.48l1.88-.38a1 1 0 0 1 1.15.66l.61 1.83a1 1 0 0 0 1 .68h4a1 1 0 0 0 .95-.68l.61-1.83a1 1 0 0 1 1.15-.66l1.88.38a1 1 0 0 0 1.07-.48l2-3.46a1 1 0 0 0-.12-1.17ZM18.41 14l.8.9l-1.28 2.22l-1.18-.24a3 3 0 0 0-3.45 2L12.92 20h-2.56L10 18.86a3 3 0 0 0-3.45-2l-1.18.24l-1.3-2.21l.8-.9a3 3 0 0 0 0-4l-.8-.9l1.28-2.2l1.18.24a3 3 0 0 0 3.45-2L10.36 4h2.56l.38 1.14a3 3 0 0 0 3.45 2l1.18-.24l1.28 2.22l-.8.9a3 3 0 0 0 0 3.98m-6.77-6a4 4 0 1 0 4 4a4 4 0 0 0-4-4m0 6a2 2 0 1 1 2-2a2 2 0 0 1-2 2"/>
                                        </svg>
                                    </span>
                                    Setting
                                </p></div>
                            <hr style="border: none; height: 0.8px; background-color: gray; margin: 20px 0 10px 0;"> 
                            <div>
                                <form action="MainController" method="POST">
                                    <p style="padding: 15px 15px 15px 15px;">
                                        <span class="Logout-icon col-md-3" style="cursor: pointer">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" viewBox="0 0 24 24">
                                            <path fill="currentColor" d="M5 21q-.825 0-1.412-.587T3 19V5q0-.825.588-1.412T5 3h7v2H5v14h7v2zm11-4l-1.375-1.45l2.55-2.55H9v-2h8.175l-2.55-2.55L16 7l5 5z"/>
                                            </svg>
                                        </span>
                                        <input type="submit" name="action" value="Logout"/>
                                    </p>
                                </form>
                                <c:url var="logoutLink" value="MainController">
                                    <c:param name="action" value="Logout"></c:param>
                                </c:url>
                                <a href="${logoutLink}">LogOut JSTL</a>
                                <a href="MainController?action=Logout">LogOut URL Rewriting </a>
                                <hr style="border: none; height: 0.8px; background-color: gray; margin: 10px 0 10px 0;"> 
                            </div>
                        </div>
                    </div>
                </div>

                <!--CREATE BUTTON-->
                <div class="create-place col-md-12">
                    <form action="MainController">
                        <input type="submit" name="action" value="Create_User_Page"/>
                    </form>
                    <c:if test="${requestScope.ERROR ne null}">
                        <div class="error-in-search-place" style="margin: 25px 0 0 0;">
                            <h3 class="error-in-search" >Error: ${requestScope.ERROR}</h3>
                        </div>
                    </c:if>
                </div>
                <c:if test="${requestScope.LIST_USER !=null}">
                    <c:if test="${not empty requestScope.LIST_USER}">
                        <c:set var="radius_1" value="0 0 0 15px"></c:set>
                        <c:set var="radius_2" value="0 0 15px 0"></c:set>
                            <div class="col-md-12" style="padding-right:  100px;">

                                <table class="full-table" border="1">
                                    <thead>
                                        <tr class="table-row" >
                                            <th style="border-radius: 15px 0 0 0">No</th>
                                            <th>User ID</th>
                                            <th>Full Name</th>
                                            <th>Role ID</th>
                                            <th>Password</th>
                                            <th>Update</th>
                                            <th style="border-radius: 0 15px 0 0">Delete</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="user" varStatus="counter" items="${requestScope.LIST_USER}">
                                    <form action="MainController">
                                        <tr class="table-row-data">
                                            <td style="text-align: center;">${counter.count}</td>
                                            <td>
                                                <div>
                                                    <input type="text" name="userID" value="${user.userID}" readonly=""/>
                                                </div>
                                            </td>
                                            <td>
                                                <div>
                                                    <input type="text" name="fullName" value="${user.fullName}"/>
                                                </div>

                                            </td>
                                            <td>
                                                <div>
                                                    <input type="text" name="roleID" value="${user.roleID}"/>
                                                </div>

                                            </td>
                                            <td>${user.password}</td>
                                            <!--Update-->
                                            <td>
                                                <input type="submit" name="action" value="Update"/>
                                                <input type="hidden" name="search" value="${param.search}"/>
                                            </td>
                                            <td>
                                                <input type="submit" name="action" value="Delete"/><br/>
                                                <a class="a-button-delete" href="MainController?action=Delete&userID=${user.userID}&roleID=${user.roleID}&search=${param.search}" style="text-decoration: none; ">Delete</a>
                                                <c:url var="deleteLink" value="MainController">
                                                    <c:param name="userID" value="${user.userID}"></c:param>
                                                    <c:param name="search" value="${param.search}"></c:param>
                                                    <c:param name="roleID" value="${user.roleID}"></c:param>
                                                    <c:param name="action" value="Delete"></c:param>
                                                </c:url>
                                                <a href="${deleteLink}">Delete JSTL</a>
                                            </td>
                                        </tr>
                                    </form>

                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:if>
                </c:if>
            </div>
        </div>
    </body>
    <script src="bootstrap-3.3.7-dist/js/bootstrap.js"></script>
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="bootstrap-3.3.7-dist/js/npm.js"></script>
    <script src='//cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js'></script><script  src="./script.js"></script>
    <script src="js/script.js"></script>
</html>
