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

	<h2 align="center">Visits booking form</h2>
	<hr>

	<h3 align="center">Fill the visit form</h3>

	<br>

	<div>

		<form:form action="processVisitForm" modelAttribute="visit">

			<p>Personal data</p>
		
		<form:errors path="customer.firstName" cssClass="error" /> <br>
		<form:input path="customer.firstName" placeholder="First name"/>
			

			<br>
			<form:errors path="customer.lastName" cssClass="error" /><br>
		<form:input path="customer.lastName" placeholder="Last name"/>
			

			<br>
			<form:errors path="customer.phoneNumber" cssClass="error" /> <br>
		<form:input path="customer.phoneNumber" placeholder="Phone number"/>
			

			<br>
			<form:errors path="customer.email" cssClass="error" /><br>
		<form:input path="customer.email" placeholder="E-mail"/>


			<br>
			<br>

			<p>Date of the visit</p>


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
			<c:if test="${(visit.bookedDate == true) and (not empty free)}">
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


		<a href="${pageContext.request.contextPath}"> Back to home page</a> <br>
		<br>

	</div>



</body>

</html>


<!--  <input type="button" value="Add hours"
				onclick="location.href='showTermHours'" />  -->

<!-- <form action="/showTermHours" method="post">
			<input type="button" value="Add hours" />
		</form> -->