<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${patient.firstName}${patient.lastName}</title>
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
			<a class="navbar-brand" href="#">${patient.firstName}
				${patient.lastName}</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="records">Records</a></li>
				<li class="active"><a href="permissions">Permissions</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="back">Back</a></li>
			</ul>
		</div>
	</div>
	</nav>

	<div class="panel panel-default">
		<div class="panel-heading">Doctors with permission</div>
		<display:table name="doctorsWithPermission" id="doctorWithPermission"
			class="table table-striped table-condensed">
			<display:column property="id" title="ID" />
			<display:column property="firstName" title="First Name" />
			<display:column property="lastName" title="Last Name" />
			<display:column>
				<form action="revoke_permission/${doctorWithPermission.id}"
					method="post">
					<button type="submit" class="btn btn-danger">Revoke
						Permissions</button>
				</form>
			</display:column>
		</display:table>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">Doctors without permission</div>
		<display:table name="doctorsWithoutPermission"
			id="doctorWithoutPermission"
			class="table table-striped table-condensed">
			<display:column property="id" title="ID" />
			<display:column property="firstName" title="First Name" />
			<display:column property="lastName" title="Last Name" />
			<display:column>
				<form action="grant_permission/${doctorWithoutPermission.id}"
					method="post">
					<button type="submit" class="btn btn-success">Grant
						Permissions</button>
				</form>
			</display:column>
		</display:table>
	</div>

</body>
</html>