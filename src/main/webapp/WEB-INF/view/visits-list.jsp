<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>

<title>Visits list</title>

<link href="<c:url value="/resources/style.css" />" rel="stylesheet">

</head>

<body>

	<h2 align="center">Visits list</h2>
	<hr>

	<h3 align="center">Choose list option</h3>
	<br>

	<div class="visit-list-options" align="center">
	<form action="list" method="get">
		<button name="period" value="all">All visits</button>
		  |  
		<button name="period" value="today">Today visits</button>
		  |  
		<button name="period" value="future">Future visits</button>
	</form>
	</div>

	<br><br>

	<table class="table-list">

		<tr align="left">
			<th>ID</th>
			<th>Date</th>
			<th>First name</th>
			<th>Last name</th>
			<th>Phone</th>
			<th>E-mail</th>
			<th>Action</th>
		</tr>

		<c:forEach var="visit" items="${visits}">

			<tr>
				<td>${visit.id}</td>
				<td>${visit.date}</td>
				<td>${visit.customer.firstName}</td>
				<td>${visit.customer.lastName}</td>
				<td>${visit.customer.phoneNumber}</td>
				<td>${visit.customer.email}</td>
				<td align="center">
					<form action="delete" method="get">
						<button name="visitId" value="${visit.id}"
							onclick="if(!(confirm('Visit will be deleted. Are you sure you want to proceed?'))) return false">Delete</button>
					</form>
				</td>

			</tr>

		</c:forEach>

	</table>

	<br>
	<br>

	<p align="center">
		<a href="${pageContext.request.contextPath}/admin/adminPanel">Back
			to administrator panel</a>
	</p>

</body>

</html>

<!-- 
	<form action="list" method="get">
		<button name="period" value="today">Today</button>
	</form>

	<form action="list" method="get">
		<button name="period" value="future">Future</button>
	</form>
	
	-->