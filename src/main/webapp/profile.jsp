<%@ include file="parts/ifLoginFail.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="parts/header.jsp" %>
<title>User Profile</title>
</head>
<body class="bg-secondary">
	<%@ include file = "parts/navigation.jsp"%>
	
	<div class = "container">
	<h1 class="text-info text-center font-weight-bold">Here is the user Profile</h1>
	<hr>
	
			<table class="table table-bordered table-dark">
				<tbody>
				<tr>
					<td scope="row"><label>Username:</label></td>
					<td>${sessionScope.userSession.getUsername()}</td>
				</tr>
				
				<tr>
					<td scope="row"><label>Password:</label></td>
					<td>${sessionScope.userSession.getPassword()}</td>
				</tr>
				
				<tr>
					<td scope="row"><label>Logged In Times:</label></td>
					<td>${sessionScope.userSession.getLoggedInTimes()}</td>
				</tr>
			</tbody>	
		</table>
	</div>
	<%@ include file ="parts/footer.jsp" %>
</body>
</html>