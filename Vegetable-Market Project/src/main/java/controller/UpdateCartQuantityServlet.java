package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.CartItemWithProduct;
import serviceImp.MainDefinition;

@WebServlet("/updateCartQuantity")
public class UpdateCartQuantityServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemIdStr = req.getParameter("itemId");
        String quantityStr = req.getParameter("quantity");
        HttpSession session = req.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");

        if (itemIdStr == null || quantityStr == null || userId == null) {
            resp.sendRedirect(req.getContextPath() + "/viewCart?error=1");
            return;
        }

        try {
            int itemId = Integer.parseInt(itemIdStr);
            int quantity = Integer.parseInt(quantityStr);
            if (quantity < 1) {
                quantity = 1; // Ensure minimum quantity
            }
            MainDefinition mainService = new MainDefinition();
            int cartId = mainService.getOrCreateCartId(userId);
            boolean success = mainService.decreaseQuantity(itemId, quantity);

            if (success) {
                // After update, get the updated cart items
                List<CartItemWithProduct> cartItems = mainService.getCartItemsWithDetails(cartId);

                // Calculate total quantity
                int totalCount = 0;
                if (cartItems != null) {
                    for (CartItemWithProduct item : cartItems) {
                        totalCount += item.getQuantity();
                    }
                }

                // Store total count in session
                session.setAttribute("cartItemCount", totalCount);
                resp.sendRedirect(req.getContextPath() + "/viewCart");
            } else {
                resp.sendRedirect(req.getContextPath() + "/viewCart?error=1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/viewCart?error=1");
        }
    }
}
