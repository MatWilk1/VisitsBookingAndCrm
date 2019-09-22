<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>

<title>Visits booking form</title>

<link href="<c:url value="/resources/style.css" />" rel="stylesheet">


</head>

<body>

	<div class="top">
		<h1 class="heading">Customer form</h1>
	</div>
	
	<div class="bottom">
	<br>
	
	<h3 align="center">Fill the customer form</h3>
	<br>

		<form:form action="saveCustomer" modelAttribute="customer">

			<p>Personal data</p>
			
			<form:hidden path="id" />
		
			<form:errors path="firstName" cssClass="error" /> <br>
			<form:input path="firstName" placeholder="First name"/>
			
			<br>
			<form:errors path="lastName" cssClass="error" /><br>
			<form:input path="lastName" placeholder="Last name"/>
			
			<br>
			<form:errors path="phoneNumber" cssClass="error" /> <br>
			<form:input path="phoneNumber" placeholder="Phone number"/>
			
			<br>
			<form:errors path="email" cssClass="error" /><br>
			<form:input path="email" placeholder="E-mail"/>

			<br>
			<br>

			<input type="submit" value="Submit" />

			<br>
			<br>

		</form:form>


		<a href="${pageContext.request.contextPath}/customer/list"> Back to customers list</a> <br>
		<br>

	</div>


</body>

</html>