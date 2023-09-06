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
<title>Register</title>

<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="/css/main.css">
<script type="text/javascript" src="/js/app.js"></script>

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Register!</h1>
			    <p class="text-danger"><c:out value="${errorCreate}"></c:out></p>
			    <p><form:errors path="user.*" class="text-danger"/></p>
			    
			    <form:form method="POST" action="/registration" modelAttribute="user">
			     <div class="form-group">
			            <form:label path="name" class="form-label">Name:</form:label>
			            <form:input path="name" class="form-control"/>
			        </div>
			        <div class="form-group">
			            <form:label path="email" class="form-label">Email:</form:label>
			            <form:input type="email" path="email" class="form-control"/>
			        </div>
			        <div class="form-group">
			            <form:label path="password" class="form-label">Password:</form:label>
			            <form:password path="password"  class="form-control"/>
			        </div>
			        <div class="form-group">
			            <form:label path="passwordConfirmation">Password Confirmation:</form:label>
			            <form:password path="passwordConfirmation"  class="form-control"/>
			        </div>
			        <input class="btn btn-success mt-2 float-end" type="submit" value="Register!"/>
			    </form:form>
			</div>
			<div class="col-6">
				<h1>Login</h1>
			    <p class="text-danger"><c:out value="${errorLogin}" /></p>
			    <form method="post" action="/login">
			        <div class="form-group">
			            <label for="email" class="form-label">Email</label>
			            <input type="email" id="email" name="email"  class="form-control"/>
			        </div>
			        <div class="form-group">
			            <label for="password" class="form-label">Password</label>
			            <input type="password" id="password" name="password" class="form-control"/>
			        </div>
			        <input class="btn btn-success mt-2 float-end" type="submit" value="Login!"/>
			    </form>  
			</div>
		</div>
	</div>
</body>
</html>