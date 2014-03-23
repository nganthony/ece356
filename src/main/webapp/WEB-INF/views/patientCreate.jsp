<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Patient Create</title>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

</head>
<body>
	<div class="container-fluid">
		<c:choose>
			<c:when test="${patient.edit}">
				<h3>Edit Patient</h3>
			</c:when>
			<c:otherwise>
				<h3>Create Patient</h3>
			</c:otherwise>
		</c:choose>
		<div class="row-fluid">
			<div class="span12">
				<fieldset>
					<form:form class="form-horizontal" action="/patientCreate"
						modelAttribute="patient">
						<form:hidden path="edit" />
						<div class="control-group">
							<form:label path="sin">SIN</form:label>
							<div class="controls">
								<form:input path="sin"></form:input>
								<form:errors path="sin" cssclass="alert alert-danger"></form:errors>
							</div>
						</div>
						<div class="control-group">
							<form:label path="firstName">First Name</form:label>
							<div class="controls">
								<form:input path="firstName"></form:input>
								<form:errors path="firstName" cssclass="error"></form:errors>
							</div>
						</div>
						<div class="control-group">
							<form:label path="lastName">Last Name</form:label>
							<div class="controls">
								<form:input path="lastName"></form:input>
								<form:errors path="lastName" cssclass="error"></form:errors>
							</div>
						</div>
						<div class="control-group">
							<form:label path="password">Password</form:label>
							<div class="controls">
								<form:input path="password"></form:input>
								<form:errors path="password" cssclass="error"></form:errors>
							</div>
						</div>

						<c:choose>
							<c:when test="${patient.edit}">
								<div class="control-group">
									<form:label path="healthCard">Health Card Number (No
								dashes or spaces)</form:label>
									<div class="controls">
										<form:input path="healthCard" class="disabled" disabled="true"></form:input>
										<form:errors path="healthCard" cssclass="error"></form:errors>
									</div>
									<form:hidden path="healthCard" />

								</div>
							</c:when>
							<c:otherwise>
								<div class="control-group">
									<form:label path="healthCard">Health Card Number (No
								dashes or spaces)</form:label>
									<div class="controls">
										<form:input path="healthCard"></form:input>
										<form:errors path="healthCard" cssclass="error"></form:errors>
									</div>
								</div>
							</c:otherwise>
						</c:choose>


						<div class="control-group">
							<form:label path="defaultDoctorId">Default Doctor</form:label>
							<div class="controls">
								<form:select class="dropdown-toggle" path="defaultDoctorId"
									items="${doctors}" />
							</div>
						</div>


						<div class="control-group">
							<form:label path="currentHealthID">Current Health</form:label>
							<div class="controls">
								<form:select class="dropdown-toggle" path="currentHealthID"
									items="${currentHealthMap}" />
							</div>
						</div>

						<br />
						<div class="form-actions">
							<button type="submit" class="btn btn-success">Submit</button>
						</div>
					</form:form>
				</fieldset>
			</div>
		</div>
	</div>
</body>
</html>