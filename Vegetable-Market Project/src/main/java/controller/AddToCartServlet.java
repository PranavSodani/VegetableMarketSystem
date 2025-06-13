package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import serviceImp.MainDefinition;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String productIdStr = req.getParameter("product_id");
		String quantityStr = req.getParameter("quantity");
		String unitStr = req.getParameter("quantity_per_unit");

//		System.out.println("Your Js is working fine here " + productId+" "+quantity+" "+unit);
		
		if(productIdStr == null || quantityStr == null || unitStr == null)
			return;
		int productId = Integer.parseInt(productIdStr);
		int quantity = Integer.parseInt(quantityStr);
		int unit = Integer.parseInt(unitStr);
		
		HttpSession session = req.getSession();
		Integer userId = (Integer) session.getAttribute("user_id");
		if (userId == null) {
		    // Redirect to login
			System.out.println("Nope not founded the user id");
			resp.sendRedirect("login.jsp");
		    return;
		}
		else {
			System.out.println("Ok you are getting till here");
			MainDefinition mainService = new MainDefinition();
			int cartId = mainService.getOrCreateCartId(userId);
			System.out.println("cartId : "+cartId);
			boolean added = mainService.addToCart(cartId, productId, quantity, unit);
			if (added) {
			    resp.sendRedirect("products.jsp");
			} else {
			    resp.sendRedirect("products.jsp?error=1");
			}

			
		}

	}
	
}
