package daoImpl;

import dao.VendorProductInterface;
import domain.ProductVegie;
import util.DBConnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendorProductDao implements VendorProductInterface {
    @Override
    public List<ProductVegie> getProductsByVendor(int vendorId) {
        List<ProductVegie> products = new ArrayList<>();
        String sql = "SELECT * FROM products_vegies WHERE vendor_id = ?";
        try (Connection conn = DBConnect.getConn();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, vendorId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ProductVegie product = new ProductVegie();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getInt("price"));
                product.setQuantity_kg(rs.getInt("quantity_kg"));
                product.setImage_address(rs.getString("image_address"));
                product.setVendorId(rs.getInt("vendor_id"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

	@Override
	public void insertProduct(ProductVegie product, int vendorId) {
	    String sql = "INSERT INTO products_vegies (name, description, price, quantity_kg, image_address, vendor_id) VALUES (?, ?, ?, ?, ?, ?)";
	    try (Connection conn = DBConnect.getConn();
	         PreparedStatement st = conn.prepareStatement(sql)) {
	        st.setString(1, product.getName());
	        st.setString(2, product.getDescription());
	        st.setInt(3, product.getPrice());
	        st.setInt(4, product.getQuantity_kg());
	        st.setString(5, product.getImage_address());
	        st.setInt(6, vendorId);
	        System.out.println(product.getImage_address());
	        st.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	@Override
	public void updateProduct(ProductVegie product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProduct(int productId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ProductVegie getProductById(int productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductVegie> getProductsByVendorId(int vendorId) {
	    List<ProductVegie> products = new ArrayList<>();
	    try (Connection conn = DBConnect.getConn()) { // Get your DB connection
	        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM products_vegies WHERE vendor_id = ?");
	        stmt.setInt(1, vendorId);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            ProductVegie product = new ProductVegie();
	            product.setId(rs.getInt("id"));
	            product.setName(rs.getString("name"));
	            product.setDescription(rs.getString("description"));
	            product.setPrice(rs.getInt("price"));
	            product.setQuantity_kg(rs.getInt("quantity_kg"));
	            product.setImage_address(rs.getString("image_address"));
	            products.add(product);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return products;
	}


}
