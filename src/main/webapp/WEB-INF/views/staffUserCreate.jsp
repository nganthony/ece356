<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a user</title>
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
					<li class="active"><a href="/1.0.0-BUILD-SNAPSHOT/staff/${staffId}/patient/create">Create Patient</a></li>

					<li><a href="/1.0.0-BUILD-SNAPSHOT/staff/${staffId}/patient/view">Update Patient</a></li>

					<li class="active"><a href="/1.0.0-BUILD-SNAPSHOT/staff/${staffId}/user/create">Create User</a></li>

					<li><a
						href="/1.0.0-BUILD-SNAPSHOT/staff/${staffId}/doctor/view">New Appointment</a></li>

					<li><a
						href="/1.0.0-BUILD-SNAPSHOT/staff/${staffId}/appointment/view">Patient Records</a></li>

				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="<%=request.getContextPath()%>/logout">Log Out</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<fieldset>
					<legend>Create a user</legend>
					<form:form class="form-horizontal" method="post" action='create'
						name="userForm" id="userForm" modelAttribute="user">

						<div class="control-group">
							<label class="control-label">First Name</label>
							<div class="controls">
								<form:input type="text" path="firstName" value="${user.firstName}"></form:input>
								<form:errors path="firstName" cssclass="error"></form:errors>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Last Name</label>
							<div class="controls">
								<form:input type="text" path="lastName" value="${user.lastName}"></form:input>
								<form:errors path="lastName" cssclass="error"></form:errors>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Password</label>
							<div class="controls">
								<form:input type="password" path="password" value="${user.password}"></form:input>
								<form:errors path="password" cssclass="error"></form:errors>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Role</label>
							<div class="controls">
								<form:select class="dropdown-toggle" items="${roles}" path="roleId"></form:select>
							</div>
						</div>
						
						<br />

						<p style="color: red">${errorMessage}</p>

						<div class="form-actions">
							<button type="submit" class="btn btn-success">Create</button>
						</div>

					</form:form>

				</fieldset>
			</div>
		</div>
	</div>
</body>
</html>