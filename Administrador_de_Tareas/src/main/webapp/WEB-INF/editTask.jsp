<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Task</title>

<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="/css/main.css">
<script type="text/javascript" src="/js/app.js"></script>

</head>
<body>
	<div class="container">
		<h3>Edit task</h3>
		<p class="text-danger"><c:out value="${error}"></c:out></p>
		<form:form action="/tasks/${task.id}/edit" method="post" modelAttribute="task">
			<input type="hidden" name="_method" value="put">
			<div class="container p-2 m-2">
				<div class="form-group">
					<form:label path="name" class="form-label">Task: </form:label>
					<form:input path="name" class="form-control" />
					<form:errors path="name" class="text-danger" />
				</div>
				<div class="form-group">
					<form:label path="assignee" class="form-label">Assignee: </form:label>
					<form:select path="assignee" class="form-control mb-2">
						<c:forEach items="${users}" var="u">
							<form:option value="${u.getName()}">${u.getName()}</form:option>
						</c:forEach>
					</form:select>
				</div>
				<div class="form-group">
					<form:label path="priority" class="form-label">Priority: </form:label>
					<form:select path="priority" class="form-control mb-2">
						<form:option value="Low">Low</form:option>
						<form:option value="Medium">Medium</form:option>
						<form:option value="High">High</form:option>
					</form:select>
				</div>
			</div>
			<button class="btn btn-success mt-2 float-end">Uptadet</button>
		</form:form>
	</div>
</body>
</html>