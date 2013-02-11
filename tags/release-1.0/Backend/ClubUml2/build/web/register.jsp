<%-- 
    Document   : register
    Created on : Oct 17, 2012, 1:12:04 AM
    Author     : wintor12
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New User Page</title>
        <script type="text/javascript" src = "js/register.js"></script>
        <style type="text/css">
            h1{
                color:#990000;
                background-color: #FC9804;
                font-family:serif;
                font-style:italic;
                font-weight:bold;
                text-align: center;
            }
            
        </style>
    </head>
    <body>
        <h1>Welcome to ClubUML!</h1>
        <form method="get" name="register" action="RegisterServlet" onsubmit="return validate();">

        <table align="center">
            <tr>
                <td>Username:</td>
                <td><input type="text" name="username" onblur="checkUsername(this.value)"></td>
                <td><span id="username"></span></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td>Confirm Password:</td>
                <td><input type="password" name="password2" onblur="checkPassword()"></td>
                <td><span id="password"></span></td>
            </tr>
            <tr>   
                <td>Email:</td>
                <td><input type="text" name="email" onsubmit= "return checkEmail();"></td>
            </tr>
            <tr>
                <td><input type="submit" value="submit"></td>
            </tr>
        </table>
        </form>

    </body>
</html>
