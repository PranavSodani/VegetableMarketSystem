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
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String userType = req.getParameter("userType"); // Get user type from form

        MainDefinition loginUser = new MainDefinition();
        User user = loginUser.loginUser(email, password);

        HttpSession session = req.getSession();

        if (user != null) {
            // Check if user type matches
            if (userType != null && user.getUserType() != null && user.getUserType().equalsIgnoreCase(userType)) {
                session.setAttribute("loggedIn", true);
                session.setAttribute("user_id", user.getUser_id());
                session.setAttribute("userType", user.getUserType()); // Store user type in session

                // Redirect based on userType
                if ("vendor".equalsIgnoreCase(user.getUserType())) {
                    resp.sendRedirect("vendor/products/list");  // Vendor dashboard servlet URL
                } else {
                    resp.sendRedirect("products.jsp");  // User dashboard JSP page
                }
            } else {
                session.setAttribute("errorMessage", "User type does not match. Please select the correct user type.");
                resp.sendRedirect("login.jsp");
            }
        } else {
            session.setAttribute("errorMessage", "Invalid email or password");
            resp.sendRedirect("login.jsp");
        }

    }
}
