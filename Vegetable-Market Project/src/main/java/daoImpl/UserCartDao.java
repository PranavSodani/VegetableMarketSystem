package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.UserCartInterface;
import domain.CartItem;
import domain.CartItemWithProduct;
import util.DBConnect;

public class UserCartDao implements UserCartInterface{
	public int getOrCreateCartId(int userId) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        int cartId = -1;

        try {
            conn = DBConnect.getConn();
            String sql = "SELECT cart_id FROM user_cart WHERE user_id = ? AND is_active = TRUE";
            st = conn.prepareStatement(sql);
            st.setInt(1, userId);
            rs = st.executeQuery();

            if (rs.next()) {
                cartId = rs.getInt("cart_id");
            } else {
                sql = "INSERT INTO user_cart (user_id, is_active) VALUES (?, TRUE)";
                st = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                st.setInt(1, userId);
                st.executeUpdate();
                rs = st.getGeneratedKeys();
                if (rs.next()) {
                    cartId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBConnect.closeResources(conn, rs, st);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cartId;
    }
	
	public boolean addToCart(int cartId, int productId, int quantity, int quantityPerUnit) {
	    Connection conn = null;
	    PreparedStatement st = null;
	    try {
	        conn = DBConnect.getConn();
	        String sql = "INSERT INTO cart_items (cart_id, product_id, quantity, quantity_per_unit) VALUES (?, ?, ?, ?)";
	        st = conn.prepareStatement(sql);
	        st.setInt(1, cartId);
	        st.setInt(2, productId);
	        st.setInt(3, quantity);
	        st.setInt(4, quantityPerUnit);
	        int rowsAffected = st.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        try {
	            DBConnect.closeResources(conn, null, st);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	public List<CartItem> getCartItems(int cartId){
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<CartItem> cartItems = new ArrayList<>();
		try {
			conn = DBConnect.getConn();
			String sql = "select * from cart_items where cart_id=?";
			st = conn.prepareStatement(sql);
			st.setInt(1,cartId);
			rs = st.executeQuery();
			while(rs.next()) {
				System.out.println("here");
				CartItem item = new CartItem(
				rs.getInt("item_id"),
				rs.getInt("cart_id"),
				rs.getInt("product_id"),
				rs.getInt("quantity"),
				rs.getInt("quantity_per_unit")		
				);
				cartItems.add(item);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				DBConnect.closeResources(conn, rs, st);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(cartItems != null)
		System.out.println("Size of my cart is "+cartItems.size());
		return cartItems;
		
	}
	
	public List<CartItemWithProduct> getCartItemsWithProductDetails(int cartId) {
	    List<CartItemWithProduct> items = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    try {
	        conn = DBConnect.getConn();
	        String sql = "SELECT ci.item_id, ci.cart_id, ci.product_id, ci.quantity, ci.quantity_per_unit, " +
	                     "p.name, p.description, p.image_address, p.price " +
	                     "FROM cart_items ci " +
	                     "JOIN products_vegies p ON ci.product_id = p.id " +
	                     "WHERE ci.cart_id = ?";
	        st = conn.prepareStatement(sql);
	        st.setInt(1, cartId);
	        rs = st.executeQuery();
	        while (rs.next()) {
	            CartItemWithProduct item = new CartItemWithProduct(
	                rs.getInt("item_id"),
	                rs.getInt("cart_id"),
	                rs.getInt("product_id"),
	                rs.getInt("quantity"),
	                rs.getInt("quantity_per_unit"),
	                rs.getString("name"),
	                rs.getString("description"),
	                rs.getString("image_address"),
	                rs.getInt("price")  // or double if you changed price type
	            );
	            items.add(item);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            DBConnect.closeResources(conn, rs, st);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return items;
	}


}
