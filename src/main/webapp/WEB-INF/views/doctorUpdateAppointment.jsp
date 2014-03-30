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
					
					<form:form role="form" method="post" action='${visit.id}'
						modelAttribute="visit">

						<div class="form-group">
							<label for="diagnosis">Diagnosis</label>
							<form:input type="text" path="diagnosis" class="form-control"
								id="diagnosis" value="${visit.diagnosis}"></form:input>
						</div>

						<div class="form-group">
							<label for="surgery">Surgery</label>
							<form:input type="text" path="surgery" class="form-control"
								id="surgery" value="${visit.surgery}"></form:input>
						</div>

						<div class="form-group">
							<label for="treatment">Treatment</label>
							<form:input type="text" path="treatment" class="form-control"
								id="treatment" value="${visit.treatment}"></form:input>
						</div>

						<div class="form-group">
							<form:label path="drugId">Drug</form:label>
							<div class="controls">
								<form:select class="dropdown-toggle" path="drugId"
									items="${drugs}" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label">Comment</label>
							<textarea name="comment" class="form-control" rows="3">${visit.comment}</textarea>
						</div>

						<button type="submit" class="btn btn-success">Update</button>

					</form:form>

				</fieldset>
			</div>
		</div>
	</div>
</body>
</html>