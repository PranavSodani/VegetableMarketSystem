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
        String action = req.getParameter("action");
        if (itemIdStr != null && "decrease".equals(action)) {
            try {
                int itemId = Integer.parseInt(itemIdStr);
                MainDefinition mainService = new MainDefinition();
                mainService.decreaseQuantity(itemId);
                resp.sendRedirect(req.getContextPath() + "/viewCart");
            } catch (Exception e) {
                e.printStackTrace();
                resp.sendRedirect(req.getContextPath() + "/viewCart?error=1");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/viewCart?error=1");
        }
    }
}
