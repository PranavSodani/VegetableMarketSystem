package service;

import java.util.List;

import domain.ProductVegie;
import domain.User;

public interface MainInterface {
	// register user implementation 
	boolean registerUser(User user);
	// login user implementation 
	boolean loginUser(String email,String password);
	// for all the vegie portions
	List<ProductVegie> getAllProducts();
	
}
	