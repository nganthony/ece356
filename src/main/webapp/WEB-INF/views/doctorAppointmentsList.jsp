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
		<display:table name="visits" id="visit"
			class="table table-striped table-condensed">
			<display:column property="health_card" title="Health Card"/>
			<display:column property="diagnosis" />
			<display:column property="treatment" />
			<display:column property="surgery" />
			<display:column property="comment" />
			<display:column property="start" />
			<display:column property="end" />
			<display:column>
				<a href="update_appointment/${visit.id}">Update Information</a>
			</display:column>
		</display:table>
	</div>
</body>
</html>