package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import serviceImp.MainDefinition;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		MainDefinition loginUser = new MainDefinition();
		boolean loggedIn = loginUser.loginUser(email, password);
		HttpSession session = req.getSession();
		if(loggedIn) {
			session.setAttribute("loggedIn", true);
			resp.sendRedirect("index.jsp");
		}
		else {
			session.setAttribute("errorMessage","Invalid email or password");
			resp.sendRedirect("login.jsp");
		}
	}

}
