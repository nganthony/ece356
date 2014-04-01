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
<link
	href="<%=request.getContextPath()%>/resources/css/bootstrap-combined.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/resources/css/bootstrap-datetimepicker.min.css">
<script src="resources/javascript/bootstrap.js"></script>

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
				<li><a href="permissions">Permissions</a></li>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li><a href="<%=request.getContextPath()%>/logout">Log Out</a></li>
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

			<h3>OR</h3>
			
			<div class="control-group">
				<label class="control-label">Start Time</label>
				<div id="datetimepicker" class="input-append date"
					style="position: relative; left: 20px;">
					<input type="text" name="startTime" id="startTime"
						title="STARTTIME"> <span class="add-on"> <i
						data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
					</span>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">End Time</label>
				<div id="datetimepicker1" class="input-append date"
					style="position: relative; left: 20px;">
					<input type="text" name="endTime" id="endTime" title="ENDTIME">
					<span class="add-on"> <i data-time-icon="icon-time"
						data-date-icon="icon-calendar"></i>
					</span>
				</div>
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form>
	</div>

	<br />
	
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

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/javascript/jquery.min.js">
		
	</script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/javascript/bootstrap.js">
		
	</script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/javascript/bootstrap-datetimepicker.min.js">
		
	</script>
	<script type="text/javascript">
		$('#datetimepicker').datetimepicker({
			format : 'yyyy-MM-dd',
			language : 'pt-EN'
		});
		$('#datetimepicker1').datetimepicker({
			format : 'yyyy-MM-dd',
			language : 'pt-EN'
		});
	</script>

</body>
</html>