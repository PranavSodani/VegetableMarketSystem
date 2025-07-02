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

import domain.ProductVegie; // Make sure this import is correct for your model
import util.DBConnect;

@WebServlet("/fetchSearchData")
public class FetchSearchDataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FetchSearchDataServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Product list for cards
        List<ProductVegie> products = new ArrayList<>();
        // 2. Product names for search
        List<String> productNames = new ArrayList<>();
        // 3. Synonym map for search
        Map<String, String> synonymToProduct = new HashMap<>();

        try (Connection conn = DBConnect.getConn()) {
            // 1. Fetch all products for display and search
            String productQuery = "SELECT id, name, description, price, image_address FROM products_vegies";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(productQuery)) {
                while (rs.next()) {
                    ProductVegie veg = new ProductVegie();
                    veg.setId(rs.getInt("id"));
                    veg.setName(rs.getString("name"));
                    veg.setDescription(rs.getString("description"));
                    veg.setPrice(rs.getInt("price"));
                    veg.setImage_address(rs.getString("image_address"));
                    products.add(veg);

                    // Also add to productNames for search
                    productNames.add(rs.getString("name"));
                }
            }

            // 2. Fetch synonyms mapped to product names
            String synonymQuery = "SELECT ps.synonym, pv.name FROM product_synonyms ps " +
                                  "JOIN products_vegies pv ON ps.product_id = pv.id";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(synonymQuery)) {
                while (rs.next()) {
                    String synonym = rs.getString("synonym");
                    String productName = rs.getString("name");
                    if (synonym != null && productName != null) {
                        synonymToProduct.put(synonym.toLowerCase(), productName.toLowerCase());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set all attributes for JSP (never null)
        request.setAttribute("products", products);
        request.setAttribute("productNames", productNames);
        request.setAttribute("synonymToProduct", synonymToProduct);

        // Forward to JSP page
        request.getRequestDispatcher("products.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
