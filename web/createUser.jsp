<%-- 
    Document   : createUser
    Created on : 21/02/2024, 4:49:34 PM
    Author     : admin
--%>

<%@page import="sample.sp24.t4s4.user.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create new User</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--Link Bootstrap-->
        <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
        <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css'>
        <!--Link CSS-->
        <link type="text/css" rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div class="container-fluid create">
            <div class="col-md-12 create-input">
                <h1>Create new User</h1>
                <div class="create-form">
                    <form action="MainController" method="POST">

                        <h2>User ID</h2>
                        <input type="text" name="userID" required=""/><br/>
                        <h3>${requestScope.USER_ERROR.userIDError}</h3><br/>
                        <h2>Full Name</h2>
                        <input type="text" name="fullName" required=""/><br/>
                        <h3>${requestScope.USER_ERROR.fullNameError}</h3><br/>
                        <h2>Role ID</h2>
                        <select name="roleID">
                            <option value="AD">AD</option>
                            <option value="US">US</option>
                            <option value="OTHER">Other</option>
                        </select><br/>
                        <h2>Password</h2>
                        <div>
                            <input type="password" name="password" id="password-field" placeholder="Password" required=""/>
                            <span toggle="#password-field" class="fa fa-fw fa-eye field-icon toggle-password"></span><br/>
                        </div>
                        <h2>Confirm</h2>
                        <div>
                            <input type="password" name="confirm" id="confirm-field" placeholder="Confirm" required=""/>
                            <span toggle="#confirm-field" class="fa fa-fw fa-eye field-icon toggle-confirm"></span><br/>
                        </div>
                        <h3>${requestScope.USER_ERROR.confirmError}</h3><br/>
                        <div class="create-reset-place">
                            <input type="submit" name="action" value="Create"/>
                            <input type="reset" value="Reset_Create"/>

                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
    <script src="bootstrap-3.3.7-dist/js/bootstrap.js"></script>
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="bootstrap-3.3.7-dist/js/npm.js"></script>
    <script src='//cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js'></script>
    <script>
        $(document).ready(function () {
            $(".toggle-password").click(function () {
                $(this).toggleClass("fa-eye fa-eye-slash");
                var input = $($(this).attr("toggle"));
                if (input.attr("type") == "password") {
                    input.attr("type", "text");
                } else {
                    input.attr("type", "password");
                }
            });

            $(".toggle-confirm").click(function () {
                $(this).toggleClass("fa-eye fa-eye-slash");
                var input = $($(this).attr("toggle"));
                if (input.attr("type") == "password") {
                    input.attr("type", "text");
                } else {
                    input.attr("type", "password");
                }
            });
        });
    </script>
</html>
