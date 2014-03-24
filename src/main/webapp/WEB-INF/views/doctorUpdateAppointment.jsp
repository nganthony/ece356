<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update appointment information</title>
<link type="text/css"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.css"
	rel="stylesheet" />
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<fieldset>
					<legend>Update appointment information</legend>
					<form:form class="form-horizontal" method="post" action='${visit.id}'
						modelAttribute="visit">
						
						<div class="control-group">
							<label class="control-label">Diagnosis</label>
							<div class="controls">
								<form:input type="text" path="diagnosis"
									value="${visit.diagnosis}"></form:input>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Surgery</label>
							<div class="controls">
								<form:input type="text" path="surgery" value="${visit.surgery}"></form:input>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Treatment</label>
							<div class="controls">
								<form:input type="text" path="treatment"
									value="${visit.treatment}"></form:input>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Comment</label>
							<div class="controls">
								<form:input type="text" path="comment" value="${visit.comment}"></form:input>
							</div>
						</div>

						<br />
						
						<div class="form-actions">
							<button type="submit" class="btn btn-success">Update</button>
						</div>

					</form:form>

				</fieldset>
			</div>
		</div>
	</div>
</body>
</html>