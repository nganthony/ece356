<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Appointment</title>
<link type="text/css"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.css"
	rel="stylesheet" />
<link
	href="<%=request.getContextPath()%>/resources/css/bootstrap-combined.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/resources/css/bootstrap-datetimepicker.min.css">

</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">

				<fieldset>
					<legend>Create an Appointment</legend>
					<form:form class="form-horizontal" method="post"
						action='/staff/${staffId}/create/appointment/${visit.user_id}/${visit.id}'
						modelAttribute="visit">
						<div class="control-group">
							<label class="control-label">Start Time</label>
							<div id="datetimepicker" class="input-append date"
								style="position: relative; left: 20px;" >
								<input type="text" name="startTime" id="startTime"
									title="STARTTIME" value=${visit.start}> <span class="add-on" > <i
									data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
								</span>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">End Time</label>
							<div id="datetimepicker1" class="input-append date"
								style="position: relative; left: 20px;">
								<input type="text" name="endTime" value=${visit.end} id="endTime" title="ENDTIME">
								<span class="add-on"> <i data-time-icon="icon-time"
									data-date-icon="icon-calendar"></i>
								</span>
							</div>
						</div>
						
						<c:choose>
							<c:when test="${visit.edit}">
								<div class="control-group">
									<form:label path="health_card"></form:label>
									<label class="control-label">Health Card</label>
									<div class="controls">
										<form:input path="health_card" class="disabled" disabled="true"></form:input>
										<form:errors path="health_card" cssclass="error"></form:errors>
									</div>
									<form:hidden path="health_card" />

								</div>
							</c:when>
							<c:otherwise>
								<div class="control-group">
									<!--  <form:label path="health_card" style="position:relative; left:17%;">Health Card Number (No dashes or spaces)</form:label>-->
									<label class="control-label">Health Card</label>
									<div class="controls">
										<form:input path="health_card"></form:input>
										<form:errors path="health_card" cssclass="error"></form:errors>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
						
						<p style="position:relative; left:16%; color: red">${errorMessage}</p>
						
						<div class="form-actions">
							<button type="submit" class="btn btn-success">Create</button>
						</div>
						
						<br />	
						
					</form:form>
				</fieldset>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/javascript/jquery.min.js">
		
	</script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/javascript/bootstrap.js">
		
	</script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/javascript/bootstrap-datetimepicker.min.js">
		
	</script>
	<script type="text/javascript">
		$('#datetimepicker').datetimepicker({
			format : 'yyyy-MM-dd hh:mm:ss',
			language : 'pt-EN'
		});
		$('#datetimepicker1').datetimepicker({
			format : 'yyyy-MM-dd hh:mm:ss',
			language : 'pt-EN'
		});
	</script>
</body>
</html>