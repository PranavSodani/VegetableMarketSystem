package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import util.DBConnect;
import serviceImp.MainDefinition;
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		String password = req.getParameter("password");
		String userType = req.getParameter("userType");
		
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPhone(phone);
		user.setAddress(address);
		user.setPassword(password);
		user.setUserType(userType);
		
		MainDefinition registerUser = new MainDefinition();
		boolean isRegistered = registerUser.registerUser(user);
		if(isRegistered) {
			System.out.println("User succesfully registerd!!!");
			resp.sendRedirect("index.jsp");
		}
		else
			System.out.println("Some Error ...");
		
	}
	
}
