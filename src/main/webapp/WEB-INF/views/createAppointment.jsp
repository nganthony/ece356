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

</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<fieldset>
					<form:form class="form-horizontal" method = "post" action='create/appointment'
						modelAttribute="visit">
						
						<div class="control-group">
							<label class="control-label">Health Card</label>
							<div class="controls">
								<form:input  path="health_card" value="${visit.health_card}"></form:input>
								<form:errors path="health_card" cssclass="error"></form:errors>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Surgery</label>
							<div class="controls">
								<form:input type="text" path="surgery" value="${visit.surgery}"></form:input>
								<form:errors path="surgery" cssclass="error"></form:errors>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Treatment</label>
							<div class="controls">
								<form:input type="text" path="treatment" value="${visit.treatment}"></form:input>
								<form:errors path="treatment" cssclass="error"></form:errors>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Comments</label>
							<div class="controls">
								<form:input type="text" path="comment" value="${visit.comment}"></form:input>
								<form:errors path="comment" cssclass="error"></form:errors>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Diagnosis</label>
							<div class="controls">
								<form:input type="text" path="diagnosis" value="${visit.diagnosis}"></form:input>
								<form:errors path="diagnosis" cssclass="error"></form:errors>
							</div>
						</div>

						<br />
						<div class="form-actions">
							<button type="submit" class="btn btn-success">Create</button>
						</div>
					</form:form>
				</fieldset>
			</div>
		</div>
	</div>
</body>
</html>