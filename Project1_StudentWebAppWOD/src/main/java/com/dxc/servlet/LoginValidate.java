package com.dxc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dxc.dao.UserJdbcDAO;

/**
 * Servlet implementation class LoginValidate
 */
public class LoginValidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginValidate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		showLogin(response, false);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		try {
			if(UserJdbcDAO.validate(username, password))
			{
				HttpSession session=request.getSession(true);
				session.getAttribute(username);
				RequestDispatcher r=request.getRequestDispatcher("Display.jsp");
				r.forward(request, response);
			}
			else
			{
				showLogin(response, false);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void showLogin(HttpServletResponse response, boolean error) throws IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>login</title>");
		out.println("</head>");
		out.println("<body>");
		if(error) {
			out.println("<h2>Invalid username or password or both</h2>");
		}
		
		out.println("<form method=\"post\">");
		out.println("User name : <input type=\"text\" name=\"username\"><br>");
		out.println("Password <input type=\"password\" name=\"password\"><br>");
		out.println("<input type=\"submit\" value=\"Login\">");
		out.println("<input type=\"reset\" value=\"Cancel\">");
		out.println("</form>");

		out.println("</body>");
		out.println("</html>");
		
		

		out.close();
	}
	

}
