package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import serviceImp.MainDefinition;

@WebServlet("/removeFromCart")
public class RemoveFromCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemIdStr = req.getParameter("itemId");
        if (itemIdStr != null) {
            try {
                int itemId = Integer.parseInt(itemIdStr);
                MainDefinition obj = new MainDefinition();
                boolean isDeleted = obj.deleteCartByItemId(itemId);
                if (!isDeleted) {
                    System.out.println("Unable to delete from cart");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect(req.getContextPath() + "/viewCart");

    }
}
