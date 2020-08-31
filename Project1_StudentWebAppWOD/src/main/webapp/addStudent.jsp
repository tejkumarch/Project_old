<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AddStudent</title>
</head>
<body>
<h2>Enter the Student Details</h2>
<form action="save" method="post">
<table>
			<tr>
				<td>Id</td>
				<td><input type="text" name="id">
				<td>
			</tr>
			<tr>
				<td>Name</td>
				<td><input type="text" name="name">
				<td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="text" name="email">
				<td>
			</tr>
			<tr>
				<td>Mobile</td>
				<td><input type="text" name="mobile">
				<td>
			</tr>

		</table>
		<input type="submit" value="submit">
		 <input type="reset" value="Cancel">
	</form>

</body>
</html>