package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.CartItem;
import serviceImp.MainDefinition;

@WebServlet("/viewCart")
public class ViewCartServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session == null || session.getAttribute("user_id") == null) {
			resp.sendRedirect("login.jsp");
			return;
		}
		int userId = (Integer)session.getAttribute("user_id");
		MainDefinition obj = new MainDefinition();
		int cardId = obj.getOrCreateCartId(userId);
		List<CartItem> cartItems = obj.getCartItems(cardId);
		req.setAttribute("cartItems", cartItems);
		req.getRequestDispatcher("/cart.jsp").forward(req, resp);
	}

}
