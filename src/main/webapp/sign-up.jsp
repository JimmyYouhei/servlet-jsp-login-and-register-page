<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<%@ include file="parts/header.jsp" %>
<title>Login Page</title>
</head>



<body class="bg-secondary">


<div class="container">
	<h1 class="text-info text-center font-weight-bold">Sign-Up Page</h1>
	<hr>
	<form action="signUpProcess" method="post">
		<table class="table table-bordered table-dark">
			<tbody>
				<tr>
					<td scope="row"><label>Username:</label></td>
					<td><input type="text" name="username"/></td>
					<td><c:if test="${usernameError}">
					Username must be longer than 6 and cannot contain any character: "$#@%^&*"
					</c:if>
					
					<c:if test="${usernameExist}"> Username already exist please choose different username</c:if>
					
					</td>
				</tr>
				
				<tr>
					<td scope="row"><label>Password:</label></td>
					<td><input type="text" name="password"/>
					</td>
					<td><c:if test="${passwordError}">
					Password mustbe longer than 8 and must have a Capitalized word , a digit and a character
					</c:if></td>
				</tr>
				
				<tr>
					<td scope="row"></td>
					<td><input type="submit" value="Sign Up"></td>
				</tr>
			</tbody>	
		</table>
	</form>
	
</div>
</body>
</html>