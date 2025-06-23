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
		// TODO Auto-generated method stub
		
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

}
