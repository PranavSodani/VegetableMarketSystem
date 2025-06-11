package dao;

import java.util.List;

import domain.ProductVegie;
import domain.User;

public interface UserInterface {
	boolean registerUserDao(User user);
	boolean loginUserDao(String email,String password);
	List<ProductVegie> getAllProducts();
}
