<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link type="text/css"
	href="<%=request.getContextPath() %>/resources/css/bootstrap.css"
	rel="stylesheet" />
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<fieldset>
					<legend>Login</legend>
					<form class="form-horizontal" method="post" action='<%=request.getContextPath() %>/'
						name="userForm" id="userForm">

						<div class="control-group">
							<label class="control-label">ID or Health Card #</label>
							<div class="controls">
								<input type="text" name="id" id="id" title="ID" value="${id}">
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Password</label>
							<div class="controls">
								<input type="password" name="password" id="password"
									title="Password">
							</div>
						</div>

						<br />

						<p style="color:red">${errorMessage}</p>

						<div class="form-actions">
							<button type="submit" class="btn btn-success">Login</button>
						</div>

					</form>

				</fieldset>
			</div>
		</div>
	</div>
</body>
</html>