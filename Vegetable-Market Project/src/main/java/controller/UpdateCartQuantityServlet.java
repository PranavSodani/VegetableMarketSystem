package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import serviceImp.MainDefinition;

@WebServlet("/updateCartQuantity")
public class UpdateCartQuantityServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemIdStr = req.getParameter("itemId");
        String quantityStr = req.getParameter("quantity");
        if (itemIdStr != null && quantityStr != null) {
            try {
                int itemId = Integer.parseInt(itemIdStr);
                int quantity = Integer.parseInt(quantityStr);
                if (quantity < 1) {
                    quantity = 1; // Ensure minimum quantity
                }
                MainDefinition mainService = new MainDefinition();
                boolean success = mainService.decreaseQuantity(itemId, quantity);
                if (success) {
                    resp.sendRedirect(req.getContextPath() + "/viewCart");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/viewCart?error=1");
                }
            } catch (Exception e) {
                e.printStackTrace();
                resp.sendRedirect(req.getContextPath() + "/viewCart?error=1");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/viewCart?error=1");
        }
    }
}

