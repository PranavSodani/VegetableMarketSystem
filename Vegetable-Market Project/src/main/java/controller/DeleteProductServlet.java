package controller;

import daoImpl.VendorProductDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/vendor/products/delete")
public class DeleteProductServlet extends HttpServlet {
    private VendorProductDao dao = new VendorProductDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer vendorId = (Integer) session.getAttribute("user_id");
        String userType = (String) session.getAttribute("userType");

        if (vendorId == null || !"vendor".equalsIgnoreCase(userType)) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String idStr = request.getParameter("id");
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            dao.deleteProduct(id, vendorId);
        }

        response.sendRedirect(request.getContextPath() + "/vendor/products/list");
    }
}
