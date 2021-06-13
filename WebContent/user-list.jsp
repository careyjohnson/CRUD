<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"/>
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="#" class="navbar-brand"> Employee Management App </a>
			</div>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">Danh sach nhan vien</h3>
			<hr>
			<div class="container text-right">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Them moi</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr style="background:tomato;">
						<th style="text-align:center;">Ma nhan vien</th>
						<th style="text-align:center;">Ten nhan vien</th>
						<th style="text-align:center;">Ngay sinh</th>
						<th style="text-align:center;">Chuc vu</th>
						<th style="text-align:center;">Thao tac</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="emp" items="${listEmp}">

						<tr>
							<td style="text-align:center;"><c:out value="${emp.code}" /></td>
							<td style="text-align:center;"><c:out value="${emp.name}" /></td>
							<td style="text-align:center;"><c:out value="${emp.birthday}" /></td>
							<td style="text-align:center;"><c:out value="${emp.position}" /></td>
							<td style="text-align:center;">
								<a href="edit?id=<c:out value='${emp.id}' />"><i class="fas fa-user-edit" style="color:black"></i></a>
									&nbsp;&nbsp;&nbsp;&nbsp; 
								<a href="#" onclick="showMess(${emp.id })"><i class="fas fa-trash-alt" style="color:black"></i></a>
							</td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
	
	<script>
		function showMess(id){
			var option = confirm('Are you sure to delete?');
			if(option===true){
				window.location.href='delete?id='+id;
			}
		}
		
	</script>
</body>
</html>
