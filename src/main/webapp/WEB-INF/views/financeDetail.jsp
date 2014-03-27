<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login for users</title>
<link type="text/css"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.css"
	rel="stylesheet" />
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
				<li><a href="<%=request.getContextPath()%>/finance/home">Summary</a></li>
				<li class="active"><a href="">Detail</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<%=request.getContextPath()%>/logout">Log Out</a></li>
			</ul>
		</div>
	</div>
	</nav>

	<div class="container-fluid">
		<display:table name="visits" id="visit"
			class="table table-striped table-condensed"
			requestURI="${requestScope['javax.servlet.forward.request_uri']}">
			<display:column property="health_card" title="Health Card"
				sortable="true" />
			<display:column property="diagnosis" />
			<display:column property="treatment" />
			<display:column property="surgery" />
			<display:column property="comment" />
			<display:column property="start" sortable="true" />
			<display:column property="end" sortable="true" />
		</display:table>
	</div>

	<br>

</body>
</html>