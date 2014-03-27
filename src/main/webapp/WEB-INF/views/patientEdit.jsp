<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit</title>
<link type="text/css"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.css"
	rel="stylesheet" />

</head>
<body>
	<spring:hasBindErrors name="patient">
		<div class="alert alert-danger">Change a few things up and try
			submitting again.</div>
	</spring:hasBindErrors>

	<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>

			<a class="navbar-brand" href="#">${patient.firstName}&nbsp;${patient.lastName}</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="<%=request.getContextPath()%>/patient/home">Details</a></li>
				<li class="active"><a href="edit/self/${patient.healthCard}">Update</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<%=request.getContextPath()%>/logout">Log Out</a></li>
			</ul>
		</div>
	</div>
	</nav>

	<div class="container-fluid">
		<h3>Your Information</h3>
		<div class="row-fluid">
			<div class="span12">
				<fieldset>
					<form:form class="form-horizontal" action="/patient/edit/self"
						modelAttribute="patient">
						<form:hidden path="edit" />
						<form:hidden path="sin" />
						<form:hidden path="firstName" />
						<form:hidden path="lastName" />
						<form:hidden path="healthCard" />
						<form:hidden path="defaultDoctorId" />
						<form:hidden path="currentHealthID" />

						<div class="control-group">
							<form:label path="password">Password</form:label>
							<div class="controls">
								<form:input path="password"></form:input>
								<form:errors path="password" cssclass="error"></form:errors>
							</div>
						</div>

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