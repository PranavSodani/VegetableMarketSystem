package serviceImp;

import java.util.List;

import daoImpl.MainDao;
import domain.ProductVegie;
import domain.User;
import service.MainInterface;

public class MainDefinition implements MainInterface {
	MainDao obj = new MainDao();
	public boolean loginUser(String email,String password) {
		return obj.loginUserDao(email,password);
	}

	@Override
	public boolean registerUser(User user) {
		String name = user.getName();
		String email = user.getEmail();
		String phone = user.getPhone();
		String password = user.getPassword();
		String address = user.getAddress();
		
//		 Here check all the entries 
		
		
		return obj.registerUserDao(user);
	}
	public List<ProductVegie> getAllProducts(){
		return obj.getAllProducts();
	}
}
