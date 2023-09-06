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
<title>Show Task</title>

<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="/css/main.css">
<script type="text/javascript" src="/js/app.js"></script>

</head>
<body>
	<div class="container mt-2">
		<div class="row">
		<p class="text-danger"><c:out value="${error}"></c:out></p>
		<h3><c:out value="${task.getName()}"></c:out></h3>
			<div class="col-sm-2">
				<p>Creator:</p>
				<p>Assignee:</p>
				<p>Priority:</p>
				<a href="/tasks/${task.getId()}/edit"><button class="btn btn-success mt-2">Edit</button></a>
			</div>
			<div class="col-sm-4">
				<p><c:out value="${task.getCreator()}"></c:out></p>
				<p><c:out value="${task.getAssignee()}"></c:out></p>
				<p><c:out value="${task.getPriority()}"></c:out></p>
				<a href="/tasks/${task.getId()}/delete"><button class="btn btn-success mt-2">Delete</button></a>
			</div>
		</div>
	</div>
</body>
</html>