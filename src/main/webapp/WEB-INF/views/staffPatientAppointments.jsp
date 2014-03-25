<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Patient Schedule</title>
<link type="text/css"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.css"
	rel="stylesheet" />
</head>
<body>
	<div class="container-fluid">
		<legend>Appointments of Patient</legend>
		<display:table name="visits" id="visit"
			class="table table-striped table-condensed">
			<display:column property="start" />
			<display:column property="end" />
			<display:column property="health_card" />
			<display:column property="diagnosis" />
			<display:column property="treatment" />
			<display:column property="surgery" />
			<display:column property="comment" />
			<display:column property="user_id" />
			<display:column property="duration" />
			<display:column>
				<a
					href=<c:url value="/staff/${staffId}/doctor/schedule/${visit.user_id}/${visit.id}"/>>Reschedule</a>
			</display:column>
			<display:column>
				<a
					href=<c:url value="/staff/${staffId}/doctor/schedule/${visit.user_id}/delete/${visit.id}"/>>Delete</a>
			</display:column>
		</display:table>
	</div>
	<div>
		<a href=<c:url value="/staff/${staffId}/create/appointment/${id}/0"/>>Create
			Appointment</a>
	</div>
</body>
</html>