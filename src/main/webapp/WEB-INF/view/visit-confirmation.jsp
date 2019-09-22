<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>

<title>Visit confirmation</title>

<link href="<c:url value="/resources/style.css" />" rel="stylesheet">

</head>

<body>

	<div class="top">
		<h1 class="heading">Visit confirmation page</h1>
	</div>
	
	<div class="bottom">
		<br>

		<h3 align="center">Visit confirmed</h3>
		<br>
		
		<p>Visit details:</p>
		<br>

		First name: <b>${visit.customer.firstName}</b> Last name: <b>${visit.customer.lastName}</b>
		<br><br> Phone number: <b>${visit.customer.phoneNumber}</b> E-mail: <b>${visit.customer.email}</b>
		<br><br>
		Date of the visit: <b>${visit.chosenDate}  ${visit.chosenTime}</b>
		<br><br> 
		
		<a href="${pageContext.request.contextPath}"> Back to home page</a> <br>
		<br>

	</div>

</body>

</html>