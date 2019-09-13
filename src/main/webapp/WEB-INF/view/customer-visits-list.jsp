<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>

<title>Customer Visits list</title>

<link href="<c:url value="/resources/style.css" />" rel="stylesheet">

</head>

<body>

	<h2 align="center">Customer Visits list</h2>
	<hr>

	<h3 align="center">All visits of </h3>
	<br>

	<table class="table-list">

		<tr align="left">
			<th>ID</th>
			<th>Date</th>
			<th>Action</th>
		</tr>

		<c:forEach var="visit" items="${visits}">

			<tr>
				<td>${visit.id}</td>
				<td>${visit.date}</td>
				<td align="center"><form action="deleteVisit" method="get">
						<button name="visitId" value="${visit.id}"
							onclick="if(!(confirm('Visit will be deleted. Are you sure you want to proceed?'))) return false">Delete</button>
					</form></td>

			</tr>

		</c:forEach>


	</table>
	
	<br>
	<br>

	<p align="center"><a href="${pageContext.request.contextPath}/customer/list"> Back to CRM</a></p>
	<p align="center"><a href="${pageContext.request.contextPath}/admin/adminPanel"> Back to administrator panel</a></p>

</body>

</html>