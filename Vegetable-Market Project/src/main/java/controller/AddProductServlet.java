package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import daoImpl.VendorProductDao;
import domain.ProductVegie;

@WebServlet("/vendor/products/add")
public class AddProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer vendorId = (Integer) session.getAttribute("user_id");
        String userType = (String) session.getAttribute("userType");

        if (vendorId == null || !"vendor".equalsIgnoreCase(userType)) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity_kg = Integer.parseInt(request.getParameter("quantity_kg"));
        String image_address = request.getParameter("image_address");

        ProductVegie product = new ProductVegie();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity_kg(quantity_kg);
        product.setImage_address(image_address);

        VendorProductDao vendorProductDao = new VendorProductDao();
        // You need to implement insertProduct in your DAO
        vendorProductDao.insertProduct(product, vendorId);

        response.sendRedirect(request.getContextPath() + "/vendor/products/list");
    }
}
