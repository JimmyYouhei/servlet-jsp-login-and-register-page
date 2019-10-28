<%@ include file="parts/ifLoginFail.jsp" %>

<!DOCTYPE html>
<html>
<head>
<%@ include file="parts/header.jsp" %>

<title>HomePage</title>
</head>
<body class="bg-secondary">
	<%@ include file = "parts/navigation.jsp"%>
	<div class="container">
	
		<c:choose >
			<c:when test="${firstLogin}">
				<h1 class="text-info text-center font-weight-bold">Hi ${sessionScope.userSession.username} Welcome to the board</h1>
			</c:when>
			<c:otherwise>
				<h1 class="text-info text-center font-weight-bold"> Hi ${sessionScope.userSession.username} Welcome Back</h1>
			</c:otherwise>
		</c:choose>
		
	</div>
<%@ include file ="parts/footer.jsp" %>
</body>
</html>