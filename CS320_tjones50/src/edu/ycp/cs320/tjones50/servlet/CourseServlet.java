package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.tjones50.controller.AdviceController;
import edu.ycp.cs320.tjones50.controller.CourseController;
import edu.ycp.cs320.tjones50.model.Advice;
import edu.ycp.cs320.tjones50.model.Course;

public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// session info
		System.out.println("in the course doGet");
		
		String email = (String)req.getSession().getAttribute("email"); //pulled from class example on session info
		
		System.out.println("   User: <" + email + "> logged in");
		
		String departmentName = (String)req.getSession().getAttribute("departmentName"); //pulled from class example on session info

		System.out.println("   Departement: <" + departmentName + ">");
		
		String courseName = (String)req.getSession().getAttribute("courseName"); //pulled from class example on session info

		System.out.println("   Course: <" + courseName + ">");

		// initialize variables
		CourseController controller = new CourseController();
		controller.setCourseByName(courseName);
		Course course = controller.getCourse();
		
		// call controller methods
		controller.computeAveGrade();
		controller.computeAveRating();
		
		// Pass model to jsp
		req.setAttribute("course", course);
		req.setAttribute("department", course.getDepartment());
		req.setAttribute("email", email);
		req.getRequestDispatcher("/_view/course.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// session info
		System.out.println("In Course Servlet doPost");
				
		// get info from parameters
		String departmentName = (String)req.getSession().getAttribute("departmentName"); //pulled from class example on session info

		System.out.println("   Departement: <" + departmentName + ">");
		
		String courseName = (String)req.getSession().getAttribute("courseName"); //pulled from class example on session info

		System.out.println("   Course: <" + courseName + ">");
		
		Integer adviceId = Integer.parseInt(req.getParameter("adviceId"));
		Integer flags = Integer.parseInt(req.getParameter("flags"));
				
		// initialize variables
		CourseController controller = new CourseController();
		controller.setCourseByName(courseName);
		Course course = controller.getCourse();
		AdviceController adviceController = new AdviceController();

		
		// call controller methods
		controller.computeAveGrade();
		controller.computeAveRating();
		
		// check flags
		for(Advice adv: course.getArrAdvice()){
			if(adviceId == adv.getAdviceId()){
				adv.setFlags(flags);
				adviceController.setModel(adv);
				adviceController.flagAdvice();
				if(adv.getFlags()==3){
					adv.setApproved(false);
				}
			}
		}
		
		// Pass model to jsp
		req.setAttribute("course", course);
		req.setAttribute("department", course.getDepartment());
				
		req.getRequestDispatcher("/_view/course.jsp").forward(req, resp);
	}
	
}
