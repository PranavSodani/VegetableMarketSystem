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

@WebServlet("/removeFromCart")
public class RemoveFromCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemIdStr = req.getParameter("itemId");
        HttpSession session = req.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");

        if (itemIdStr == null || userId == null) {
            resp.sendRedirect(req.getContextPath() + "/viewCart");
            return;
        }

        try {
            int itemId = Integer.parseInt(itemIdStr);
            MainDefinition mainService = new MainDefinition();
            int cartId = mainService.getOrCreateCartId(userId);
            boolean isDeleted = mainService.deleteCartByItemId(itemId);

            // Set notification in session
            session.setAttribute("notification", "Item removed from cart!");

            // After deletion, get the updated cart items
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

            if (!isDeleted) {
                System.out.println("Unable to delete from cart");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/viewCart");
    }
}
