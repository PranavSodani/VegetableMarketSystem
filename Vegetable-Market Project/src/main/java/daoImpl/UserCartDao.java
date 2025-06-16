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
	    PreparedStatement updateSt = null;
	    ResultSet rs = null;
	    try {
	        conn = DBConnect.getConn();
	        String checkSql = "SELECT item_id, quantity FROM cart_items WHERE cart_id = ? AND product_id = ? AND quantity_per_unit = ?";
	        st = conn.prepareStatement(checkSql);
	        st.setInt(1, cartId);
	        st.setInt(2, productId);
	        st.setInt(3, quantityPerUnit);
	        rs = st.executeQuery();
	        if (rs.next()) {
	            int existingQuantity = rs.getInt("quantity");
	            int newQuantity = existingQuantity + quantity;
	            int itemId = rs.getInt("item_id");
	            String updateSql = "UPDATE cart_items SET quantity = ? WHERE item_id = ?";
	            updateSt = conn.prepareStatement(updateSql);
	            updateSt.setInt(1, newQuantity);
	            updateSt.setInt(2, itemId);
	            int rowsAffected = updateSt.executeUpdate();
	            return rowsAffected > 0;
	        } else {
	            String insertSql = "INSERT INTO cart_items (cart_id, product_id, quantity, quantity_per_unit) VALUES (?, ?, ?, ?)";
	            updateSt = conn.prepareStatement(insertSql);
	            updateSt.setInt(1, cartId);
	            updateSt.setInt(2, productId);
	            updateSt.setInt(3, quantity);
	            updateSt.setInt(4, quantityPerUnit);
	            int rowsAffected = updateSt.executeUpdate();
	            return rowsAffected > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        try {
	            DBConnect.closeResources(conn, rs, st);
	            if (updateSt != null) {
	                updateSt.close();
	            }
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
		List<CartItemWithProduct> list = new ArrayList<>();
	    String sql = "SELECT ci.item_id, ci.cart_id, ci.product_id, ci.quantity, ci.quantity_per_unit, " +
	                 "p.name AS product_name, p.description, p.price AS unit_price, p.image_address, " +
	                 "ROUND((ci.quantity * ci.quantity_per_unit * p.price) / 100) AS total_price " +
	                 "FROM cart_items ci " +
	                 "JOIN products_vegies p ON ci.product_id = p.id " +
	                 "WHERE ci.cart_id = ?";
	    try (Connection conn = DBConnect.getConn();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, cartId);
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                CartItemWithProduct item = new CartItemWithProduct();
	                item.setItemId(rs.getInt("item_id"));
	                item.setCartId(rs.getInt("cart_id"));
	                item.setProductId(rs.getInt("product_id"));
	                item.setQuantity(rs.getInt("quantity"));
	                item.setQuantityPerUnit(rs.getInt("quantity_per_unit"));
	                item.setProductName(rs.getString("product_name"));
	                item.setProductDescription(rs.getString("description"));
	                item.setImageAddress(rs.getString("image_address"));
	                item.setPrice(rs.getInt("unit_price"));
	                item.setTotalPrice(rs.getInt("total_price"));  // Set calculated total price
	                list.add(item);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}

	@Override
	public boolean deleteCartByItemId(int itemId) {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DBConnect.getConn();
			String sql = "Delete from cart_items where item_id = ?";
			st = conn.prepareStatement(sql);
			st.setInt(1, itemId);
			int i = st.executeUpdate();
			if(i == 1)
				return true;
			else
				return false;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				DBConnect.closeResources(conn, null, st);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public boolean decreaseQuantity(int itemId) {
	    Connection conn = null;
	    PreparedStatement stSelect = null;
	    PreparedStatement stUpdate = null;
	    PreparedStatement stDelete = null;
	    ResultSet rs = null;
	    try {
	        conn = DBConnect.getConn();

	        // 1. Get current quantity
	        String selectSql = "SELECT quantity FROM cart_items WHERE item_id = ?";
	        stSelect = conn.prepareStatement(selectSql);
	        stSelect.setInt(1, itemId);
	        rs = stSelect.executeQuery();

	        if (rs.next()) {
	            int currentQuantity = rs.getInt("quantity");
	            if (currentQuantity > 1) {
	                // 2. Update quantity = quantity - 1
	                String updateSql = "UPDATE cart_items SET quantity = ? WHERE item_id = ?";
	                stUpdate = conn.prepareStatement(updateSql);
	                stUpdate.setInt(1, currentQuantity - 1);
	                stUpdate.setInt(2, itemId);
	                int rows = stUpdate.executeUpdate();
	                return rows > 0;
	            } else {
	                // 3. Quantity is 1, so remove the item
	                String deleteSql = "DELETE FROM cart_items WHERE item_id = ?";
	                stDelete = conn.prepareStatement(deleteSql);
	                stDelete.setInt(1, itemId);
	                int rows = stDelete.executeUpdate();
	                return rows > 0;
	            }
	        }
	        return false; // Item not found
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        try {
	            DBConnect.closeResources(conn, rs, stSelect);
	            if (stUpdate != null) stUpdate.close();
	            if (stDelete != null) stDelete.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


}
