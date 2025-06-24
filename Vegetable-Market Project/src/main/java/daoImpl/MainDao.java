package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.UserInterface;
import domain.ProductVegie;
import domain.User;
import util.DBConnect;

public class MainDao implements UserInterface {
	public boolean registerUserDao(User user) {
	    boolean f = false;
	    try {    
	        Connection conn = DBConnect.getConn();
	        String sql= "insert into users(name,email,phone,address,password,user_type,profile_image) values (?,?,?,?,?,?,?)";
	        PreparedStatement st = conn.prepareStatement(sql);
	        st.setString(1, user.getName());
	        st.setString(2, user.getEmail());
	        st.setString(3, user.getPhone());
	        st.setString(4, user.getAddress());
	        st.setString(5, user.getPassword());
	        st.setString(6, user.getUserType());
	        st.setString(7, user.getProfileImage() != null ? user.getProfileImage() : ""); // or NULL
	        int i = st.executeUpdate();
	        if(i == 1)
	            f = true;
	    }
	    catch(Exception e) {
	        e.printStackTrace();
	    }
	    return f;
	}


	public User loginUserDao(String email, String password) {
	    Connection conn = null;
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
	    try {
	        conn = DBConnect.getConn();
	        st = conn.prepareStatement(sql);
	        st.setString(1, email);
	        st.setString(2, password);
	        rs = st.executeQuery();
	        if (rs.next()) {
	            int id = rs.getInt("user_id");
	            String name = rs.getString("name");
	            String phone = rs.getString("phone");
	            String address = rs.getString("address");
	            String userEmail = rs.getString("email");
	            String userPassword = rs.getString("password");
	            String userType = rs.getString("user_type");
	            String profileImage = rs.getString("profile_image"); // New

	            User user = new User(id, name, userEmail, phone, address, userPassword, userType, profileImage);
	            System.out.println("User successfully logged in: " + userEmail);
	            return user;
	        } else {
	            System.out.println("User not found or wrong credentials");
	            return null;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error during login");
	        return null;
	    } finally {
	        try {
	            DBConnect.closeResources(conn, rs, st);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


	
	public List<ProductVegie> getAllProducts(){
	    List<ProductVegie> products = new ArrayList<>();
	    String sql = "select * from products_vegies";
	    try {
	        Connection conn = DBConnect.getConn();
	        PreparedStatement st = conn.prepareStatement(sql);
	        ResultSet rs = st.executeQuery();
	        while(rs.next()) {
	            ProductVegie product = new ProductVegie();
	            product.setId(rs.getInt("id"));
	            product.setName(rs.getString("name"));
	            product.setDescription(rs.getString("description"));
	            product.setImage_address(rs.getString("image_address"));
	            product.setPrice(rs.getInt("price")); 
	            product.setQuantity_kg(rs.getInt("quantity_kg"));
	            products.add(product);
	        }
	        System.out.println("Number of products fetched: " + products.size());
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    return products;
	}
	
	public boolean updateProfileImage(int userId, String profileImage) {
	    boolean updated = false;
	    Connection conn = null;
	    PreparedStatement st = null;
	    String sql = "UPDATE users SET profile_image = ? WHERE user_id = ?";
	    try {
	        conn = DBConnect.getConn();
	        st = conn.prepareStatement(sql);
	        st.setString(1, profileImage);
	        st.setInt(2, userId);
	        int i = st.executeUpdate();
	        if (i == 1) {
	            updated = true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (st != null) st.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return updated;
	}

	
}
