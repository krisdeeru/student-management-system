<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>List Students</title>
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>School Management System</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
			<input type="button" value="Add Student"
				   onclick="window.location.href='showFormForAdd'; return false;"
				   class="add-button"/>
			
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>

				<c:forEach var="student" items="${students}">
					<c:url var="updateLink" value="/student/showFormForUpdate">
						<c:param name="studentId" value="${student.id}" />
					</c:url>
					<c:url var="deleteLink" value="/student/delete">
						<c:param name="studentId" value="${student.id}" />
					</c:url>

					<tr>
						<td> ${student.firstName} </td>
						<td> ${student.lastName} </td>
						<td> ${student.email} </td>
						<td>
							<a href="${updateLink}">Update</a>
							|
							<a href="${deleteLink}"
							   onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>