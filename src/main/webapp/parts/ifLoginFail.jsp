<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<c:if test= "${empty sessionScope.userSession}">
	<c:redirect url="navigationServlet">
		<c:param name="command" value="landing"></c:param>
	</c:redirect>
</c:if>
	