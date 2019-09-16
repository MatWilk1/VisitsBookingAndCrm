<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>

<title>Visits booking and CRM home page</title>

<link href="<c:url value="/resources/style.css" />" rel="stylesheet">

</head>

<body>

	<h2 align="center">Visits booking and CRM home page</h2>
	<hr>

	<h3 align="center">Welcome on our home page</h3>
	<br>

	<div>

		<h4>For Customers</h4>
		<a href="visit/visitForm">Visits booking</a> <br>

		<br><br>

		<h4>For Administrators</h4>
		<p style="margin-top:-20px; font-size:12px;">(login data from the attached SQL file: <br>
		username/password: admin1/admin1 or admin2/admin2)</p>
		<a href="admin/adminPanel">Administrator panel</a> <br> <br>
		
		
		<br>

	</div>

</body>

</html>