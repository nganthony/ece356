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
	<nav class="navbar navbar-default" role="navigation"> <!-- Brand and toggle get grouped for better mobile display -->
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target="#bs-example-navbar-collapse-1">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="#">Legal</a>
	</div>

	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
			<li class="active"><a href="">Legal</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
					<li><a href="<%=request.getContextPath()%>/logout">Log Out</a></li>
				</ul>
	</div>
	</nav>
	<div class="panel panel-default">
		<div class="panel-heading">Audit Table</div>
		<display:table name="visitAudits" id="visitAudits"
			class="table table-striped table-condensed">
			<display:column property="id" title="ID" />
			<display:column property="health_card" title="Health Card" />
			<display:column property="visitId" title="Visit ID" />
			<display:column property="modifiedOn"  title="Modified On" />
			<display:column property="modifiedById" title="Modified By" />
			<display:column property="modifiedType" title="Modified Type" />
			<display:column property="start" title="Start" />
			<display:column property="end" title="END" />
			<display:column property="diagnosis" title="Diagnosis" />
			<display:column property="treatment" title="Treatment" />
			<display:column property="surgery" title="Surgery" />
			<display:column property="comment" title="Comment" />
			<display:column property="user_id" title="Doctor ID" />
		</display:table>
	</div>
</body>
</html>