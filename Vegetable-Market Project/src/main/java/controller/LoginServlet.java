package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;
import serviceImp.MainDefinition;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String email = req.getParameter("email");
	    String password = req.getParameter("password");
	    
	    MainDefinition loginUser = new MainDefinition();
	    User user = loginUser.loginUser(email, password); // This now returns a User object or null
	    
	    HttpSession session = req.getSession();
	    if(user != null) {
	        session.setAttribute("loggedIn", true);
	        session.setAttribute("user_id", user.getUser_id()); // Store the actual user ID
	        resp.sendRedirect("index.jsp");
	    }
	    else {
	        session.setAttribute("errorMessage","Invalid email or password");
	        resp.sendRedirect("login.jsp");
	    }
	}


}
