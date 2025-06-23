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

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productIdStr = req.getParameter("product_id");
        String quantityStr = req.getParameter("quantity");
        String unitStr = req.getParameter("quantity_per_unit");

        if (productIdStr == null || quantityStr == null || unitStr == null) {
            resp.sendRedirect("products.jsp?error=1");
            return;
        }

        int productId = Integer.parseInt(productIdStr);
        int quantity = Integer.parseInt(quantityStr);
        int unit = Integer.parseInt(unitStr);

        HttpSession session = req.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");
        if (userId == null) {
            System.out.println("User not logged in");
            resp.sendRedirect("login.jsp");
            return;
        }

        MainDefinition mainService = new MainDefinition();
        int cartId = mainService.getOrCreateCartId(userId);
        System.out.println("cartId: " + cartId);

        boolean added = mainService.addToCart(cartId, productId, quantity, unit);
        if (!added) {
            resp.sendRedirect("products.jsp?error=1");
            return;
        }

        // After adding, get the updated cart items
        List<CartItemWithProduct> cartItems = mainService.getCartItemsWithDetails(cartId); // You must implement this method

        // Calculate total quantity
        int totalCount = 0;
        if (cartItems != null) {
            for (CartItemWithProduct item : cartItems) {
                totalCount += item.getQuantity();
            }
        }

        // Store total count in session
        session.setAttribute("cartItemCount", totalCount);
        resp.sendRedirect("products.jsp");
    }
}
