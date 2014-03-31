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
<link type="text/css"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.css"
	rel="stylesheet" />

</head>
<body>

	<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<span class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <a class="navbar-brand" href="#">Staff</a>
		</div>
		<div class="navbar-collapse collapse top-collapse">
			<!-- NOTE!  the class top-collapse was added here -->
			<ul class="nav navbar-nav">

				<c:choose>
					<c:when test="${patient.edit}">
						<li class="active"><a
							href="/1.0.0-BUILD-SNAPSHOT/staff/${staffId}/patient/create">Create
								Patient</a></li>

						<li><a
							href="/1.0.0-BUILD-SNAPSHOT/staff/${staffId}/patient/view">Update
								Patient</a></li>
					</c:when>
					<c:otherwise>
						<li ><a
							href="/1.0.0-BUILD-SNAPSHOT/staff/${staffId}/patient/create">Create
								Patient</a></li>

						<li class="active"><a
							href="/1.0.0-BUILD-SNAPSHOT/staff/${staffId}/patient/view">Update
								Patient</a></li>
					</c:otherwise>
				</c:choose>

				<li><a
					href="/1.0.0-BUILD-SNAPSHOT/staff/${staffId}/user/create">Create
						User</a></li>

				<li><a
					href="/1.0.0-BUILD-SNAPSHOT/staff/${staffId}/doctor/view">New
						Appointment</a></li>

				<li><a
					href="/1.0.0-BUILD-SNAPSHOT/staff/${staffId}/appointment/view">Patient
						Records</a></li>

			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<%=request.getContextPath()%>/logout">Log Out</a></li>
			</ul>
		</div>
	</div>
	</nav>
	<spring:hasBindErrors name="patient">
		<div class="alert alert-danger">Change a few things up and try
			submitting again.</div>
	</spring:hasBindErrors>
	<div class="container-fluid">
		<c:choose>
			<c:when test="${patient.edit}">
				<legend>Edit a Patient</legend>
			</c:when>
			<c:otherwise>
				<legend>Create a Patient</legend>
			</c:otherwise>
		</c:choose>
		<div class="row-fluid">
			<div class="span12">
				<fieldset>
					<form:form class="form-horizontal" action="/patient/create"
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
									<form:label path="healthCard">Health Card Number (No dashes or spaces)</form:label>
									<div class="controls">
										<form:input path="healthCard" class="disabled" disabled="true"></form:input>
										<form:errors path="healthCard" cssclass="error"></form:errors>
									</div>
									<form:hidden path="healthCard" />

								</div>
							</c:when>
							<c:otherwise>
								<div class="control-group">
									<form:label path="healthCard">Health Card Number (No dashes or spaces)</form:label>
									<div class="controls">
										<form:input path="healthCard"></form:input>
										<form:errors path="healthCard" cssclass="error"></form:errors>
									</div>
								</div>
							</c:otherwise>
						</c:choose>

						<div class="control-group">
							<form:label path="phoneNumber">Phone Number (No dashes or spaces)</form:label>
							<div class="controls">
								<form:input path="phoneNumber"></form:input>
								<form:errors path="phoneNumber" cssclass="error"></form:errors>
							</div>
						</div>

						<div class="control-group">
							<form:label path="street">Street</form:label>
							<div class="controls">
								<form:input path="street"></form:input>
								<form:errors path="street" cssclass="error"></form:errors>
							</div>
						</div>

						<div class="control-group">
							<form:label path="city">City</form:label>
							<div class="controls">
								<form:input path="city"></form:input>
								<form:errors path="city" cssclass="error"></form:errors>
							</div>
						</div>

						<div class="control-group">
							<form:label path="province">Province</form:label>
							<div class="controls">
								<form:input path="province"></form:input>
								<form:errors path="province" cssclass="error"></form:errors>
							</div>
						</div>

						<div class="control-group">
							<form:label path="postalCode">Postal Code</form:label>
							<div class="controls">
								<form:input path="postalCode"></form:input>
								<form:errors path="postalCode" cssclass="error"></form:errors>
							</div>
						</div>

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