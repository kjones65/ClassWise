package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("In Index doGet");
		
		String email = (String)req.getSession().getAttribute("email"); //pulled from class example on session info
		
		System.out.println("   User: <" + email + "> logged in");
		
		req.setAttribute("email", email);
		
		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}
}
