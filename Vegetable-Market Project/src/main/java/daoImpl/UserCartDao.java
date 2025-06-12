package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.UserCartInterface;
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

}
