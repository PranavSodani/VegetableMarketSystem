package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import domain.ProductVegie;
import serviceImp.MainDefinition;

@WebServlet("/vendor/products/list")
public class VendorProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        Integer userId = (Integer) session.getAttribute("user_id");
        String userType = (String) session.getAttribute("userType");

        // Check if user is logged in and is a vendor
        if (loggedIn == null || !loggedIn || userId == null || !"vendor".equalsIgnoreCase(userType)) {
            resp.sendRedirect("login.jsp");
            return;
        }

        MainDefinition mainService = new MainDefinition();
        List<ProductVegie> products = mainService.getProductsByVendorId(userId);

        req.setAttribute("products", products);
        req.getRequestDispatcher("/vendor/products.jsp").forward(req, resp); // Your JSP path
    }
}
