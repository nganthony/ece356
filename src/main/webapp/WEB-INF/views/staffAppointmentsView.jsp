<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Doctors</title>
<link type="text/css"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.css"
	rel="stylesheet" />
</head>
<body>

	<div class="container-fluid">
		<form class="navbar-form navbar-left" role="search" method="post"
			action='/1.0.0-BUILD-SNAPSHOT/staff/${staffId}/appointment/view'>
			<div class="form-group">
				<input type="text" class="form-control" name="search"
					placeholder="Search" value="${search}">
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">Doctors with permission</div>
		<display:table name="patientVisits" id="patientVisit"
			class="table table-striped table-condensed"
			style="float: left; width:100%; table-layout:fixed; ">
			<display:column property="patient.firstName" title="First Name" />
			<display:column property="patient.lastName" title="Last Name" />
			<display:column property="visit.start"  title="Start" />
			<display:column property="visit.end" title="END" />
			<display:column property="visit.health_card" title="Health Card" />
			<display:column property="visit.diagnosis" title="Diagnosis" />
			<display:column property="visit.treatment" title="Treatment" />
			<display:column property="visit.surgery" title="Surgery" />
			<display:column property="visit.comment" title="Comment" />
			<display:column property="visit.user_id" title="Doctor ID" />
		</display:table>
	</div>

</body>
</html>