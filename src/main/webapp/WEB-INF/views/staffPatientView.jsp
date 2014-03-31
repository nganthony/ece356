<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Patient</title>
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
	<div class="container-fluid">
	<legend>List of Patients</legend>
		<display:table name="patients" id="patient"
			class="table table-striped table-condensed">
			<display:column property="firstName" title="First Name" />
			<display:column property="lastName" title="Last Name" />
			<display:column>
				<a href=<c:url value="/patient/edit/${patient.healthCard}"/>>Edit</a>

			</display:column>
		</display:table>
	</div>
</body>
</html>