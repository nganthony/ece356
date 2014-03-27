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