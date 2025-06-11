package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnect {
	private static Connection conn;
	public static Connection getConn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vegetables","root","root");
			System.out.println(conn);
		}
		catch(Exception e) {
		e.printStackTrace();
		}
		return conn;
	}
	public static void closeResources(Connection conn,ResultSet rs,PreparedStatement st) throws SQLException {
		if(conn != null) conn.close();
		if(rs != null) rs.close();
		if(st != null) st.close();
	}
}
