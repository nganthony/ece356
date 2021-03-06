<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login for users</title>
<link type="text/css"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.css"
	rel="stylesheet" />
<script src="resources/javascript/bootstrap.js"></script>
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
			<a class="navbar-brand" href="#">${patient.firstName}&nbsp;${patient.lastName}</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="home">Details</a></li>
				<li><a href="<%=request.getContextPath()%>/patient/edit/self/${patient.healthCard}">Update</a></li>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li><a href="<%=request.getContextPath()%>/logout">Log Out</a></li>
			</ul>

		</div>
	</div>
	</nav>



	<div class="container-fluid ">
		<div class="row-fluid">
			<div>
				<h3>${patient.firstName}&nbsp;${patient.lastName}</h3>
				<h6>Health Card: ${patient.healthCard}</h6>
				<c:if test="${not empty patient.sin}">
					<h6>SIN: ${patient.sin}</h6>
				</c:if>
				<h6>Default Doctor: ${defaultDoctor}</h6>
				<h6>Last Visit :${patient.lastVisitDate}</h6>
			</div>
			<br> <br>
			<display:table name="patientVisit" id="visit" class="table table-striped table-condensed">
				<display:column property="start"  title="Start" />
				<display:column property="end" title="END" />
				<display:column property="diagnosis" title="Diagnosis" />
				<display:column property="treatment" title="Treatment" />
				<display:column property="surgery" title="Surgery" />
				<display:column property="drugName" title="Drug" />
				<display:column property="user_id" title="Doctor ID" />
			</display:table>

		</div>
	</div>
</body>
</html>