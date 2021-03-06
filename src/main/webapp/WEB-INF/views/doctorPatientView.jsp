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

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<fieldset>
					<div class="col-md-8">
						<h4>Name: ${patient.firstName} ${patient.lastName}</h4>
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
			<display:column property="drugName" title="Drug" />
			<display:column property="start" />
			<display:column property="end" />
		</display:table>
	</div>
</body>
</html>