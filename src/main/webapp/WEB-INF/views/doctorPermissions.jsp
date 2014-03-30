<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Permissions</title>
<link type="text/css"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.css"
	rel="stylesheet" />
</head>
<body>
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
			<a class="navbar-brand" href="#">${user.firstName}
				${user.lastName}</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="patients">Patients</a></li>
				<li><a href="appointments">Appointments</a></li>
				<li class="active"><a href="permissions">Permissions</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<%=request.getContextPath()%>/logout">Log Out</a></li>
			</ul>
		</div>
	</div>
	</nav>

	<div class="panel panel-default">
		<div class="panel-heading">Permissions</div>
		<display:table name="userPatients" id="userPatient"
			class="table table-striped table-condensed">
			<display:column property="user.id" title="Doctor ID" />
			<display:column property="patient.firstName" title="Patient First Name" />
			<display:column property="patient.lastName" title="Patient Last Name" />
			<display:column property="patient.healthCard" title="Patient Health Card" />
			<display:column>
				<a href="permissions/${userPatient.user.id}/patient/${userPatient.patient.healthCard}">View records</a>
			</display:column>
		</display:table>
	</div>

</body>
</html>