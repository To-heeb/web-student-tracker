<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Student</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>FooBar University</h2>
		</div>
	</div>
	<div id="container">
		<h3>Update Student</h3>
		
		<form action="StudentControllerServlet" method="GET">
			
			<input type="hidden" name='command' value='UPDATE'/>
			<input type="hidden" name='studentId' value='${the_student.id}' />
		
			<table>
				<tbody>
					<tr> 
						<td><label>First name:</label> </td>
						<td><input type="text" name="firstname" value="${the_student.firstname}" ></td>
					</tr>
					<tr> 
						<td><label>Last name:</label> </td>
						<td><input type="text" name="lastname" value="${the_student.lastname}" ></td>
					</tr>
					<tr> 
						<td><label>Email:</label> </td>
						<td><input type="text" name="email" value="${the_student.email}" ></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Update" class="save"/></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<p>
		<a href="StudentControllerServlet">Back to list</a>
	</p>
</body>
</html>