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
		<h1 class="heading">Visits booking form</h1>
	</div>
	
	<div class="bottom">
	<br>
	
	<h3 align="center">Date of the visit</h3>
	<br>

		<form:form action="addVisitForCustomer" modelAttribute="visit">

			<p>Date of the visit</p>

			<form:hidden path="customer.firstName" />
			<form:hidden path="customer.lastName" />
			<form:hidden path="customer.phoneNumber" />
			<form:hidden path="customer.email" />

			<table class="table-visit-form">

				<tr align="center">
					<td><label>Select Date</label></td>
					<td><label>Select Time</label></td>
				</tr>


				<tr align="center">

					<td><form:select path="chosenDate">
							<form:options items="${visit.dateOptions }" />
						</form:select></td>

					<td><form:select path="chosenTime">
							<form:options items="${visit.timeOptions }" />
						</form:select></td>
				</tr>

			</table>

			<!-- Show info if chosen term is already booked and show all free visit terms in chosen day -->
			<c:if test="${(visit.bookedDate == true)}">
				<p style="color: red; font-size:13px">
					Term is already booked. Available this day are: <br>
					${free}
				</p>
			</c:if>

			<br>

			<input type="submit" value="Submit" />

			<br>
			<br>

		</form:form>


		<a href="${pageContext.request.contextPath}/customer/list"> Back to CRM</a> <br>
		<br>

	</div>


</body>

</html>