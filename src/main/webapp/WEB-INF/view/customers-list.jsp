<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>

<title>CRM</title>

<link href="<c:url value="/resources/style.css" />" rel="stylesheet">

</head>

<body>

	<div class="top">
		<h1 class="heading">CRM</h1>
	</div>
	
		<br>
		
		<h3 align="center">Customers list</h3>
		<br>
		
	    <form:form align="center" action="search" method="GET">
	        Search customer: <input type="text" name="name" />
	        
	        <input type="submit" value="Search" />
	    </form:form>
	    
	    <br>
	
		<table class="table-list">
	
			<tr>
				<th>ID</th>
				<th>First name</th>
				<th>Last name</th>
				<th>Phone</th>
				<th>E-mail</th>
				<th>Visits</th>
				<th>Action</th>
			</tr>
	
			<c:forEach var="customer" items="${customers}">
			
	 
				<tr>
					<td>${customer.id}</td>
					<td>${customer.firstName}</td>
					<td>${customer.lastName}</td>
					<td>${customer.phoneNumber}</td>
					<td>${customer.email}</td>
					<td align="center">
						<c:url var="visitsLink"	value="customerVisitsList">
							<c:param name="customerId" value="${customer.id}" />
						</c:url>
						<a href="${visitsLink}">${customer.visitsNumber}</a>
					</td>
					<td align="center">
						<form action="formForUpdate" method="get" style="display:inline;">
							<button name="customerId" value="${customer.id}">Update</button>
						</form>
						|
						<form action="${pageContext.request.contextPath}/visit/formForVisit" method="get" style="display:inline;">
							<button name="customerId" value="${customer.id}">Add visit</button>
						</form>
						|
						<form action="delete" method="get" style="display:inline;">
							<button name="customerId" value="${customer.id}"
								onclick="if(!(confirm('Customer and all his visits will be deleted. Are you sure you want to proceed?'))) return false">Delete</button>
						</form>
					</td>
	
				</tr>
				
			</c:forEach>
	
		</table>
	
		<br>
		<br>
	
		<p align="center">
			<a href="${pageContext.request.contextPath}/admin/adminPanel">
				Back to administrator panel</a>
		</p>
	

</body>

</html>
