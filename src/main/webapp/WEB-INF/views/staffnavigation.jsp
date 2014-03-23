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
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <a class="navbar-brand" href="#">ECE
					356</a>
			</div>
			<div class="navbar-collapse collapse top-collapse">
				<!-- NOTE!  the class top-collapse was added here -->
				<ul class="nav navbar-nav">
					<li><a
						href="/1.0.0-BUILD-SNAPSHOT/patient/create"
						onclick="return go(this);">Create Patient</a></li>
						
					<li><a href="/1.0.0-BUILD-SNAPSHOT/patient/view"
						onclick="return go(this);">Update Patient</a></li>
						
					<li><a href="/1.0.0-BUILD-SNAPSHOT/staffnavigation/doctor/view"
						onclick="return go(this);">New Appointment</a></li>
						
					<li><a href="/1.0.0-BUILD-SNAPSHOT/doctor/view"
						onclick="return go(this);">Scheduling Information</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
	<div class="container-fluid" style="padding: 75px 12%">
		<div class="row-fluid">
			<div class="span12">
				<div id="content"></div>
				<script type="text/javascript">
					onload = function() {
						var el = document.getElementById("content");
						el.innerHTML = "<iframe height = '800px' width='90%' src=\"/1.0.0-BUILD-SNAPSHOT/patient/create\"></iframe>";
					};
				</script>
				<script>
					function go(obj) {
						var page = obj.href;
						document.getElementById('content').innerHTML = '<object height = "800px" width = "100%" data="'+page+'" type="text/html"><embed height = "800px" width = "90%" src="'+page+'" type="text/html" /></object>';
						return false;
					}
				</script>
				
			</div>
		</div>
	</div>
</body>
</html>