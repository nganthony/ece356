<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Staff Navigation Page</title>
<link type="text/css"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.css"
	rel="stylesheet" />
</head>
<body>
	<!-- Static top navbar -->
	<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <a class="navbar-brand" href="#">Staff</a>
			</div>
			<div class="navbar-collapse collapse top-collapse">
				<!-- NOTE!  the class top-collapse was added here -->
				<ul class="nav navbar-nav">
					<li><a href="/1.0.0-BUILD-SNAPSHOT/patient/create"
						onclick="return go(this);">Create Patient</a></li>

					<li><a href="/1.0.0-BUILD-SNAPSHOT/patient/view"
						onclick="return go(this);">Update Patient</a></li>

					<li><a href="/1.0.0-BUILD-SNAPSHOT/user/create"
						onclick="return go(this);">Create User</a></li>

					<li><a
						href="/1.0.0-BUILD-SNAPSHOT/staff/${staffId}/doctor/view"
						onclick="return go(this);">Appointments</a></li>

					<li><a
						href="/1.0.0-BUILD-SNAPSHOT/staff/${staffId}/appointment/view"
						onclick="return go(this);">Patient Records</a></li>

				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="<%=request.getContextPath()%>/logout">Log Out</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container-fluid" >
		<div class="row-fluid">
			<div class="span12">
				<div id="content"></div>
				<script type="text/javascript">
					onload = function() {
						var el = document.getElementById("content");
						el.innerHTML = "<iframe style=\"border-style:none;\"height = '800px' width='100%' src=\"/1.0.0-BUILD-SNAPSHOT/patient/create\"></iframe>";
					}; 
				</script>
				<script>
					function go(obj) {
						var page = obj.href;
						document.getElementById('content').innerHTML = '<object height = "800px" width = "100%" data="'
								+ page
								+ '" type="text/html"><embed height = "800px" width = "90%" src="'
								+ page + '" type="text/html" /></object>';
						return false;
					}
				</script>

			</div>
		</div>
	</div>
</body>
</html>