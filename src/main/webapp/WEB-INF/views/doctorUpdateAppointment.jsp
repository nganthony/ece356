<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update appointment information</title>
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
				<li><a href="../patients">Patients</a></li>
				<li class="active"><a href="../appointments">Appointments</a></li>
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
					<legend>Update appointment information</legend>
					
					<form:form role="form" method="post" action='${visit.id}'
						modelAttribute="visit">

						<div class="form-group">
							<label for="diagnosis">Diagnosis</label>
							<form:input type="text" path="diagnosis" class="form-control"
								id="diagnosis" value="${visit.diagnosis}"></form:input>
						</div>

						<div class="form-group">
							<label for="surgery">Surgery</label>
							<form:input type="text" path="surgery" class="form-control"
								id="surgery" value="${visit.surgery}"></form:input>
						</div>

						<div class="form-group">
							<label for="treatment">Treatment</label>
							<form:input type="text" path="treatment" class="form-control"
								id="treatment" value="${visit.treatment}"></form:input>
						</div>

						<div class="form-group">
							<form:label path="drugId">Drug</form:label>
							<div class="controls">
								<form:select class="dropdown-toggle" path="drugId"
									items="${drugs}" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label">Comment</label>
							<textarea name="comment" class="form-control" rows="3">${visit.comment}</textarea>
						</div>

						<button type="submit" class="btn btn-success">Update</button>

					</form:form>

				</fieldset>
			</div>
		</div>
	</div>
</body>
</html>