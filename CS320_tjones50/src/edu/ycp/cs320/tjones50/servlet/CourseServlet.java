package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.tjones50.controller.CourseController;
import edu.ycp.cs320.tjones50.model.Advice;
import edu.ycp.cs320.tjones50.model.Course;
import edu.ycp.cs320.tjones50.model.Department;
import edu.ycp.cs320.tjones50.model.Rating;

public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Course model = new Course();
		CourseController controller = new CourseController();
		controller.setModel(model);
		
		String courseName = req.getParameter("courseName");
		String departmentName = req.getParameter("departmentName");
		
		model.setName(courseName);
		Department department = new Department(departmentName);
		model.setDepartment(department);
		
		model.setAveGrade(3.21);
		Rating aveRating = new Rating(7,4,0,8);
		model.setAveRatings(aveRating);
		
		Rating adviceRating = new Rating(7,4,0,8);
		Advice advice = new Advice(adviceRating, "Sophmore", "Computer Engineering", 3.91, 4.0, "Spring", 2017);
		advice.setText("This class is the best and I am going to talk a lot so that I can see how it goes to display so much text I am running out of things to say so I'm just going to talk about how it snowed this week and we got a snow day and it was great but I just did homework during the day because I am an engineer and that's all I do I wonder if this is enough text I hope it is I am running out of stuff to say so I guess I am going to stop");
		advice.setApproved(true);
		model.addAdvice(advice);
		
		Rating adviceRating2 = new Rating(1,5,4,2);
		Advice advice2 = new Advice(adviceRating2, "Senior", "Computer Science", 3.53, 3.5, "Fall", 2013);
		advice2.setText("This class was the worst");
		advice2.setApproved(true);
		model.addAdvice(advice2);
		
		Rating adviceRating3 = new Rating(1,1,1,1);
		Advice advice3 = new Advice(adviceRating3, "Junior", "Electrical Engineering", 3.22, 3.0, "Fall", 2018);
		advice3.setText("This class was okay");
		advice3.setApproved(true);
		model.addAdvice(advice3);
		
		
		// Pass model to jsp
		req.setAttribute("course", model);
		
		req.getRequestDispatcher("/_view/course.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Course model = new Course();
		CourseController controller = new CourseController();
		controller.setModel(model);
		
		// Pass model to jsp
		req.setAttribute("course", model);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/course.jsp").forward(req, resp);
	}

}
