<%--
    Document   : index
    Created on : Oct 17, 2012, 1:09:40 AM
    Author     : wintor12
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>umlClub</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/cufon-yui.js"></script>
<script type="text/javascript" src="js/arial.js"></script>
<script type="text/javascript" src="js/cuf_run.js"></script>
<script type="text/javascript" src="js/register.js"></script>

</head>
<body>
	<form method="post" action="RegisterServlet" name="registerForm" onsubmit="return validateForm()">
		<div class="main">
			<div class="header">
				<div class="header_resize">
					<div class="logo">

						<p>
							<a href="#">Welcome to ClubUML</a>
						</p>

					</div>
					<div class="clr"></div>
				</div>
			</div>
			<div class="hbg">
				&nbsp;
				<p>&nbsp;</p>
				<p align="center">
					<font color="#61210B">Register User</font>
				</p>
				<p>&nbsp;</p>
			</div>
			<div class="fbg_resize">
				<table border="0" align="center" cellpadding="5" cellspacing="5">


					<table align="center">
						<tr>
							<td><font color="#61210B">Username:</font></td>
							<td><input type="text" name="username"
								onblur="checkUsername(this.value);"></td>
							<td><span id="username"></span></td>
						</tr>
						<tr>
							<td><font color="#61210B">Password:</font></td>
							<td><input type="password" name="password"
								onblur="checkPassword();"></td>
						</tr>
						<tr>
							<td><font color="#61210B">Confirm Password:</font></td>
							<td><input type="password" name="password2"
								onblur="checkPassword(); "></td>
							<td><span id="password"></span></td>
						</tr>
						<tr>
							<td><font color="#61210B">Email:</font></td>
							<td><input type="text" name="email"
								onblur="checkEmail(this.value);"></td>
							<td><span id="email"></span></td>
						</tr>
						<tr>
							<td><font color="#61210B">Security Question:</font></td>
							<td><select name="securityQuestion">
									<option value="nickname">What is your nick name?</option>
									<option value="firstschool">what is your first school?</option>
									<option value="bestfriend">Name of your best friend ?</option>
							</select></td>
							<div align="center">

								</select>
							</div>
						</tr>
						<tr>
							<td><font color="#61210B">Security Answer:</font></td>
							<td><input type="text" name="securityAnswer"></td>
						</tr>
						<tr>
							<td colspan="2" style="text-align:center"><input id='SubmitButton' type="submit" value="Create Account"></td>
						</tr>
					</table>
				</table>
			</div>
		</div>
	</form>
</body>

</html>