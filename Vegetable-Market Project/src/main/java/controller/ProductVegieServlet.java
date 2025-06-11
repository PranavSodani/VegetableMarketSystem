package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.ProductVegie;
import serviceImp.MainDefinition;

@WebServlet("/products")
public class ProductVegieServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    MainDefinition productService = new MainDefinition();
	    List<ProductVegie> products = productService.getAllProducts();
	    if(products != null && products.size() > 0)
	    	System.out.println("Till here everything is working");
	    else
	    	System.out.println("Some things is wrong here");
	    HttpSession session = req.getSession();
	    session.setAttribute("products", products);
	    req.getRequestDispatcher("/products.jsp").forward(req, resp);
	}

	
}
