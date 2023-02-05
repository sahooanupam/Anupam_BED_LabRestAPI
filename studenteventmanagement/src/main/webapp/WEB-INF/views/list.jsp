<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.List,com.gl.studenteventmanagement.entity.Student"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>

<head>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<style>
.myDivhead {
	border: 1px black;
	background-color: grey;
	padding: 20px;
	text-align: center;
	font-size: 12px;
}

table {
	border-collapse: collapse;
	border: 0.25px solid;
	width: 100%;
}

th {
	border-collapse: collapse;
	background-color: grey;
	padding: 7px;
	text-align: center;
	font-size: 17px;
	color: White;
}

tr {
	border-collapse: collapse;
	border: 0.25px black;
}

tr:nth-child(even) {
	border: 0.25px black;
	background-color: lightgray;
}

td {
	text-align: center;
}

input {
	border-radius: 5px;
	background-color: lightgray;
	border: 1px;
	font-weight: bold;
	width: 12%;
	height: 5%;
	font-size: 12px;
}
</style>

</head>

<body>
	<div class="myDivhead" id="">
		<h1>
			<b> EVENT REGISTRATION </b>
		</h1>
	</div>
	
	
	<div>
		<form action="/StudentManagement/login" method="post">
			<input type="submit" name="s" style="float: right" value="Logout" />
		</form>
		<br> <br> <br>
		<form action="/StudentManagement/students/showStudentFormForAdd" method="post">
			<input type="submit" name="s" value="Add Student" />
		</form>
	</div>

	<div>
	<table class="table table-bordered table-striped">
			<thead class="thead-dark">
				<tr>
                    <th>Student Id</th>				
					<th>FirstName</th>
					<th>LastName</th>
					<th>Course</th>
					<th>Country</th>
					<th>Action</th>
					
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${students}" var="tempStudent">
					<tr>
					    <td><c:out value="${tempStudent.studentId}" /></td>
						<td><c:out value="${tempStudent.firstName}" /></td>
						<td><c:out value="${tempStudent.lastName}" /></td>
						<td><c:out value="${tempStudent.course}" /></td>
						<td><c:out value="${tempStudent.country}" /></td>
						
						<td>
							<!-- Add "update" button/link --> <a
							href="/StudentManagement/students/showStudentFormForUpdate?studentId=${tempStudent.studentId}"
							class="btn btn-info btn-sm"> Update </a> <!-- Add "delete" button/link -->
							<a href="/StudentManagement/students/delete?studentId=${tempStudent.studentId}"
							class="btn btn-danger btn-sm"
							onclick="if (!(confirm('Are you sure you want to delete this employee?'))) return false">
								Delete </a>

						</td>
					

					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
</body>
</html>