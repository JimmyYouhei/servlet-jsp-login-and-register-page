<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<%@ include file="parts/header.jsp" %>
<title>Login Page</title>
</head>


<c:url var="signUp" value="navigationServlet">
	<c:param name="command" value="signUp"></c:param>
</c:url>

<body class="bg-secondary">


<div class="container">
	<h1 class="text-info text-center font-weight-bold">Login Page</h1>
	<hr>
	<form action="loginProcess" method="post">
	
	
	<c:if test="${loginError}">
		<p>Username or password or both are incorrect please check</p>
	</c:if>
	
	<c:if test="${logout}">
		<p>You Are logged out</p>
	</c:if>
	
	<c:if test="${noUser}">
		<p>there is no user please <a href="${signUp}">sign up</a>  </p>
	</c:if>
	
	<c:if test="${firstSignUp}">
		<p> Sign Up Success please login
	</c:if>

		<table class="table table-bordered table-dark">
			<tbody>
				<tr>
					<td scope="row"><label>Username:</label></td>
					<td><input type="text" name="username"/></td>
				</tr>
				
				<tr>
					<td scope="row"><label>Password:</label></td>
					<td><input type="text" name="password"/></td>
				</tr>
				
				<tr>
					<td scope="row"></td>
					<td><input type="submit" value="check"></td>
				</tr>
			</tbody>	
		</table>
	</form>
	
</div>
</body>
</html>