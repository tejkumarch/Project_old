<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student</title>
</head>
<body>

	<%
		String id = request.getParameter("un");
		String dri = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/studentsinfo";
		String un = "root";
		String pw = "root";

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
	%>
	<table>
		<tr>

		</tr>
		<tr>
			<td><b>ID</b></td>
			<td><b>Name</b></td>
			<td><b>DOB</b></td>
			<td><b>Email</b></td>
			<td><b>Mobile</b></td>
		</tr>
		<%
			try {
				Class.forName(dri);
				con = DriverManager.getConnection(url, un, pw);
				stmt = con.createStatement();
				String sql = "select * from students";

				rs = stmt.executeQuery(sql);
				while (rs.next()) {
				
		%>
		<tr>

			<td><%=rs.getInt("studentid")%></td>
			<td><%=rs.getString("name")%></td>
			<td><%=rs.getDate("dob")%></td>
			<td><%=rs.getString("email")%></td>
			<td><%=rs.getString("mobile")%></td>
			<td>hi</td>
		

		</tr>

		<%
			}
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		%>
	</table>
	
	<button onclick="location.href ='/Login/logout'" >logout</button>
</body>
</html>

