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
import daoImpl.VendorProductDao;

@WebServlet("/vendor/products/*")
public class VendorProductServlet extends HttpServlet {
    private VendorProductDao vendorProductDao;

    @Override
    public void init() {
        vendorProductDao = new VendorProductDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer vendorId = (Integer) session.getAttribute("user_id");
        String userType = (String) session.getAttribute("userType");

        // Only allow vendors to access this page
        if (vendorId == null || !"vendor".equalsIgnoreCase(userType)) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String action = request.getPathInfo();
        if (action == null) action = "/list";

        try {
            switch (action) {
                case "/list":
                    listProducts(request, response, vendorId);
                    break;
                // Add cases for "/new", "/edit", "/delete" as you implement them
                default:
                    listProducts(request, response, vendorId);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response, int vendorId) throws ServletException, IOException {
        List<ProductVegie> products = vendorProductDao.getProductsByVendor(vendorId);
        request.setAttribute("products", products);
        request.getRequestDispatcher("/vendor-product-list.jsp").forward(request, response);
    }

    // Add methods for insert, update, delete as you implement the DAO
}
