<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Tracker App</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>FooBar University</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
		<!-- put new button: Add Student -->
			<!-- <input type="button" value="Add Student" 
			onclick="window.location.href='add-student-form.jsp'; return false;" 
			class="add-student-button"
			/>  -->
			
			<a style="
			text-decoration: none; 
			color: black;"
			class="add-student-button" href='add-student-form.jsp'>
			Add Student
			</a>
			<table style="margin-top: 20px">
				<thead>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="tempStudent" items="${student_list}">
						
						<!-- set up a link for each student -->
						<c:url var="tempLink" value="StudentControllerServlet">
							<c:param name="command" value="LOAD" />
							<c:param name="studentId" value="${tempStudent.id}" />
						</c:url>
						
						<!-- set up a link to delete a student -->
						<c:url var="deleteLink" value="StudentControllerServlet">
							<c:param name="command" value="DELETE" />
							<c:param name="studentId" value="${tempStudent.id}" />
						</c:url>
						<tr>
							<td>${tempStudent.firstname}</td>
							<td>${tempStudent.lastname}</td>
							<td>${tempStudent.email}</td>
							<td> 
								<a href="${tempLink}">Update</a> 
								| 
								<a href="${deleteLink}" onclick="if(!(confirm('Are you sure you want to delete ${tempStudent.lastname} ${tempStudent.firstname}?'))) return false;">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>