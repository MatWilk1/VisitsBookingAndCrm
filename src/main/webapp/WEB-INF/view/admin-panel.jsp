<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>

	<title>Administrator panel</title>
	
	<link href="<c:url value="/resources/style.css" />" rel="stylesheet">

</head>

<body>

	<h2 align="center">Administrator panel</h2>
	<hr>
	
	<h3 align="center">Choose action</h3>
	<br>
	
	<div align="center" style="background-color:#ecf0f1">

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

	</div>

</body>

</html>

		<!-- <a href="${pageContext.request.contextPath}/visit/list">Visits list</a> -->