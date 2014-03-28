<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Patients</title>
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
				<li class="active"><a href="patients">Patients</a></li>
				<li><a href="appointments">Appointments</a></li>
			</ul>
		</div>
	</div>
	</nav>

	<div class="container-fluid">
		<form class="navbar-form navbar-left" role="search" method='post'
			action='patients'>
			<div class="form-group">
				<input type="text" class="form-control" name="search"
					placeholder="Search" value="${search}">
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">Default Patients</div>
		<display:table name="defaultPatients" id="patient"
			class="table table-striped table-condensed">
			<display:column property="firstName" title="First Name" />
			<display:column property="lastName" title="Last Name" />
			<display:column property="healthCard" title="Health Card" />
			<display:column property="lastVisitDate" title="Last Visit Date" />
			<display:column>
				<a href="patient/${patient.healthCard}/records">View records</a>
			</display:column>
		</display:table>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">Visited Patients</div>
		<display:table name="visitedPatients" id="patient"
			class="table table-striped table-condensed">
			<display:column property="firstName" title="First Name" />
			<display:column property="lastName" title="Last Name" />
			<display:column property="healthCard" title="Health Card" />
			<display:column property="lastVisitDate" title="Last Visit Date" />
			<display:column>
				<a href="patient/${patient.healthCard}/records">View records</a>
			</display:column>
		</display:table>
	</div>

</body>
</html>