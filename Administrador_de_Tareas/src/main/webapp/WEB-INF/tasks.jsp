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
<title>Dashboard</title>

<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="/css/main.css">
<script type="text/javascript" src="/js/app.js"></script>

</head>
<body>
	<div class="container">
		<h2>Welcome, <c:out value="${user.name}"></c:out></h2>
		<a href="../logout" class="float-end">Logout</a>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Tasks</th>
					<th>Creator</th>
					<th>Assignee</th>
					<th>Priority</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="u">
					<c:forEach items="${u.getTasks()}" var="t">
						<tr>
							<td><a href="/tasks/${t.getId()}">${t.getName()}</a></td>
							<td>${t.getCreator()}</td>
							<td>${t.getAssignee()}</td>
							<td>${t.getPriority()}</td>
						</tr>
					</c:forEach>
				</c:forEach>
			</tbody>
		</table>
		<a href="/tasks/new" class="float-end"><button class="btn btn-success mt-2 float-end">Create task</button></a>
	</div>
</body>
</html>