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
			String sql= "insert into users(name,email,phone,address,password) values (?,?,?,?,?)";
			PreparedStatement st = conn.prepareStatement(sql);
			System.out.println(user.getName());
			st.setString(1,user.getName());
			st.setString(2,user.getEmail()	);
			st.setString(3, user.getPhone());
			st.setString(4, user.getAddress());
			st.setString(5, user.getPassword());
			int i = st.executeUpdate();
			if(i == 1)
				f = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	public boolean loginUserDao(String email, String password) {
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
	            System.out.println("User successfully logged in");
	            return true;
	        } else {
	            System.out.println("User not found or wrong credentials");
	            return false;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error during login");
	        return false;
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
	            products.add(product);
	        }
	        System.out.println("Number of products fetched: " + products.size());
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    return products;
	}
}
