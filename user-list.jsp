<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
	<head>
	<title>CRUD JSP Test</title>
	<link rel="stylesheet"
		href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
		integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
		crossorigin="anonymous">
	</head>
	<body style="background-color: #24292d">
		<header>
			<nav class="navbar navbar-dark bg-dark"
				style="background-color: #e3f2fd">
				<div>
					<a href="https://github.com/ThePernalonga" class="navbar-brand"> CRUD JSP Test </a>
				</div>
				<ul class="navbar-nav">
					<li><a href="<%=request.getContextPath()%>/list"
						class="nav-link">Users</a></li>
				</ul>
			</nav>
		</header>
		<br>
		<div class="row">
			<div class="container">
				<h3 class="text-center" style="color: white">List of Users</h3>
				<hr>
				<div class="container text-left">
					<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New User</a>
				</div>
				<br>
				<div class="backgroud" style="background-color: white">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Birthday</th>
							<th>Email</th>
							<th>Work</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${listUser}">
							<tr>
								<td><c:out value="${user.id}" /></td>
								<td><c:out value="${user.name}" /></td>
								<td><c:out value="${user.birth}" /></td>
								<td><c:out value="${user.email}" /></td>
								<td><c:out value="${user.work}" /></td>
								<td><a href="edit?id=<c:out value='${user.id}' />">Edit</a>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
									href="delete?id=<c:out value='${user.id}' />">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
			</div>
		</div>
	</body>
</html>