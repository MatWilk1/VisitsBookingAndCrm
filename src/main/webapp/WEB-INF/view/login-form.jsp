<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<title>Login Page</title>

<link href="<c:url value="/resources/style.css" />" rel="stylesheet">

</head>

<body>

	<div class="top">
		<h1 class="heading">Login Page</h1>
	</div>
	
	<div class="bottom">
		<br>
		
		<h3 align="center">Login details</h3>
		<br>
		
		<form:form action="${pageContext.request.contextPath}/authenticateUser"
			method="POST">

			<c:if test="${param.error != null}">
				<p class="error">Invalid username or password.</p>
			</c:if>
			
			<c:if test="${param.logout != null}">
				<p>Logged out successfully</p>
			</c:if>

			<p>
				<input type="text" name="username" placeholder="Username"/>
			</p>

			<p>
				<input type="password" name="password" placeholder="Password"/>
			</p>

			<input type="submit" value="Login" />

		</form:form>
		
		<br>

	</div>

</body>

</html>












