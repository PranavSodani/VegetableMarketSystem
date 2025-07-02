package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.ProductVegie;
import serviceImp.MainDefinition;
import util.DBConnect;

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

        // --- Fetch productNames and synonymToProduct for search ---
        List<String> productNames = new ArrayList<>();
        Map<String, String> synonymToProduct = new HashMap<>();

        try (Connection conn = DBConnect.getConn()) {
            // Fetch product names
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT name FROM products_vegies")) {
                while (rs.next()) {
                    productNames.add(rs.getString("name"));
                }
            }

            // Fetch synonyms
            String sql = "SELECT ps.synonym, pv.name FROM product_synonyms ps JOIN products_vegies pv ON ps.product_id = pv.id";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    synonymToProduct.put(rs.getString("synonym").toLowerCase(), rs.getString("name").toLowerCase());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // --- Set all attributes for JSP ---
        req.setAttribute("products", products); // Use request, not session
        req.setAttribute("productNames", productNames);
        req.setAttribute("synonymToProduct", synonymToProduct);

        req.getRequestDispatcher("/products.jsp").forward(req, resp);
    }
}
