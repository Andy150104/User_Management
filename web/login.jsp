<%-- 
    Document   : login
    Created on : 24/01/2024, 5:05:56 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <!--Link Bootstrap-->
        <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
        <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css'>
        <!--Link CSS-->
        <link type="text/css" rel="stylesheet" href="css/style.css">
    </head>
    <body>


        <div class="container-fluid login">
            <div class="col-md-6 login-input">
                <h1>Input your information</h1>
                <div>
                    <form  action="MainController" method="POST">
                        <h2>User ID</h2>
                        <input type="text" name="userID" placeholder="User ID"/><br/>
                        <h2>Password</h2>
                        <div>
                            <input type="password" name="password" id="password-field" placeholder="Password"/>
                            <span toggle="#password-field" class="fa fa-fw fa-eye field-icon toggle-password"></span><br/>
                        </div>
                        <c:if test="${requestScope.ERROR ne null}">
                            <h3>${requestScope.ERROR}</h3> 
                        </c:if>
                        <div class="login-submit-reset-place">
                            <input type="submit" name="action" value="login"/>
                            <input type="reset" value="Reset"/>
                        </div>
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=profile email&redirect_uri=http://localhost:8084/UserManagementT4S4_ASM_JSTL/LoginWithGoogleController&response_type=code
                           &client_id=148451594700-697vg9r0iehunncv9uobsi4r6kdv2h9f.apps.googleusercontent.com&approval_prompt=force">Login With Google</a>
                    </form>
                </div>
            </div>

            <img class="col-md-6" src="images/illustration-astronaut.jpg" width="1920" height="1920" alt="illustration-fox-login"/>

        </div>
    </body>
    <script src="bootstrap-3.3.7-dist/js/bootstrap.js"></script>
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="bootstrap-3.3.7-dist/js/npm.js"></script>
    <script src='//cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js'></script><script  src="./script.js"></script>
    <script src="js/script.js"></script>
</html>
