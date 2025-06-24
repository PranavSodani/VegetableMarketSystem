package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import daoImpl.VendorProductDao;
import domain.ProductVegie;

@WebServlet("/vendor/products/list")
public class VendorProductListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer vendorId = (Integer) session.getAttribute("user_id");
        String userType = (String) session.getAttribute("userType");

        if (vendorId == null || !"vendor".equalsIgnoreCase(userType)) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        VendorProductDao vendorProductDao = new VendorProductDao();
        List<ProductVegie> products = vendorProductDao.getProductsByVendorId(vendorId);

        request.setAttribute("products", products);
        request.getRequestDispatcher("/vendor-product-list.jsp").forward(request, response);
    }
}
