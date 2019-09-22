<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>

	<title>Administrator panel</title>
	
	<link href="<c:url value="/resources/style.css" />" rel="stylesheet">

</head>

<body>
	
	<div class="top">
		<h1 class="heading">Administrator panel</h1>
	</div>
	
	<div class="bottom">
		<br>
		
		<h3 align="center">Choose action</h3>
		<br>
		
		<c:url var="visitsLink"	value="/visit/list">
			<c:param name="period" value="all" />
		</c:url>
		<a href="${visitsLink}">Visits list</a>
		
		<br><br>
		
		<a href="${pageContext.request.contextPath}/customer/list">CRM</a>
		
		<br><br><br>
		
		<a href="${pageContext.request.contextPath}">Back to home page</a>
		
		<br><br>
		
		<form:form action="${pageContext.request.contextPath}/logout" method="POST">
			<input type="submit" value="Logout" />
		</form:form>
		
		<br>

	</div>

</body>

</html>