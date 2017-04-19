package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.tjones50.controller.AccountController;
import edu.ycp.cs320.tjones50.model.Account;
import edu.ycp.cs320.tjones50.model.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("In the login doGet");
		
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("In the login doPost");
		
		User model = new User();
		AccountController controller = new AccountController();
		controller.setModel(model);
		
		String email = req.getParameter("email");
		String password = req.getParameter("pass");
		String reenter = req.getParameter("reenter");
		
		model.setEmail(email);
		model.setPassword(password);
		model.setReenter(password);
		
		boolean emailValid = controller.validate(email);
		boolean accountExists = controller.checkAccountInfo(email, password, reenter);
		
		// Pass model to jsp
		req.setAttribute("login", model);
		if(emailValid == false){
			req.setAttribute("errorMessage", "Please enter a valid email pattern.");
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		if(accountExists == true){ //if account exists
			// Pass model to jsp
			
			req.setAttribute("email", req.getParameter("email")); //session code modeled after in-class example by Professor Hake
			req.setAttribute("pass", req.getParameter("pass"));
			
			// store email obj in session
			req.getSession().setAttribute("email", email);
			
			// Forward to view to render the result HTML document
			req.getRequestDispatcher("/_view/home.jsp").forward(req, resp);
		}else{
			req.setAttribute("errorMessage", "Email and/or password invalid.");
			// Forward to view to render the result HTML document
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
	}
}