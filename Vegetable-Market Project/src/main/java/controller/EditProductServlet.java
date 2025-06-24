package controller;

import daoImpl.VendorProductDao;
import domain.ProductVegie;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/vendor/products/edit")
public class EditProductServlet extends HttpServlet {
    private VendorProductDao dao = new VendorProductDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get product id from request
        String idStr = request.getParameter("id");
        if (idStr == null) {
            response.sendRedirect(request.getContextPath() + "/vendor/products/list");
            return;
        }
        int id = Integer.parseInt(idStr);

        ProductVegie product = dao.getProductById(id);
        if (product == null) {
            response.sendRedirect(request.getContextPath() + "/vendor/products/list");
            return;
        }

        request.setAttribute("product", product);
        request.getRequestDispatcher("/edit-product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Process form submission
        HttpSession session = request.getSession();
        Integer vendorId = (Integer) session.getAttribute("user_id");
        String userType = (String) session.getAttribute("userType");

        if (vendorId == null || !"vendor".equalsIgnoreCase(userType)) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity_kg = Integer.parseInt(request.getParameter("quantity_kg"));
        String image_address = request.getParameter("image_address"); // For simplicity, you can extend to handle file upload

        ProductVegie product = new ProductVegie();
        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity_kg(quantity_kg);
        product.setImage_address(image_address);
        product.setVendorId(vendorId);

        dao.updateProduct(product);

        response.sendRedirect(request.getContextPath() + "/vendor/products/list");
    }
}
