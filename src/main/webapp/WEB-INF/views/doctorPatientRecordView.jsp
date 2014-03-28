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
				<li class="active"><a href="records">Records</a></li>
				<li><a href="permissions">Permissions</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="back">Back</a></li>
			</ul>
		</div>
	</div>
	</nav>
	
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<fieldset>
					<div class="col-md-8">
						<h4>Health Card: ${patient.healthCard}</h4>
						<c:if test="${not empty patient.sin}">
							<h4>SIN: ${patient.sin}</h4>
						</c:if>
						<h4>Default Doctor: ${defaultDoctor}</h4>
						<h4>Last Visit: ${patient.lastVisitDate}</h4>
					</div>
				</fieldset>
			</div>
		</div>
	</div>

	<br />

	<div class="panel panel-default">
		<div class="panel-heading">Visits</div>
		<display:table name="visits" id="visit"
			class="table table-striped table-condensed">
			<display:column property="health_card" title="Health Card" />
			<display:column property="diagnosis" />
			<display:column property="treatment" />
			<display:column property="surgery" />
			<display:column property="comment" />
			<display:column property="start" />
			<display:column property="end" />
		</display:table>
	</div>
</body>
</html>