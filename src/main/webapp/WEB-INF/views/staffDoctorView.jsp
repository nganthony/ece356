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
	<div class="panel panel-default">
		<div class="panel-heading">List of Doctors</div>
		<display:table name="users" id="user"
			class="table table-striped table-condensed">
			<display:column property="firstName" />
			<display:column property="lastName" />
			<display:column>
				<a
					href=<c:url value="/staff/${staffId}/doctor/schedule/${user.id}"/>>View
					Schedule</a>

			</display:column>
		</display:table>
	</div>
</body>
</html>