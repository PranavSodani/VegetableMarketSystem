package serviceImp;

import java.util.List;

import daoImpl.MainDao;
import daoImpl.UserCartDao;
import domain.CartItem;
import domain.CartItemWithProduct;
import domain.ProductVegie;
import domain.User;
import service.MainInterface;

public class MainDefinition implements MainInterface {
	MainDao obj1 = new MainDao();
	UserCartDao obj2 = new UserCartDao();
	
	public User loginUser(String email,String password) {
		return obj1.loginUserDao(email,password);
	}

	@Override
	public boolean registerUser(User user) {
		String name = user.getName();
		String email = user.getEmail();
		String phone = user.getPhone();
		String password = user.getPassword();
		String address = user.getAddress();
		
//		 Here check all the entries 
		
		
		return obj1.registerUserDao(user);
	}
	public List<ProductVegie> getAllProducts(){
		return obj1.getAllProducts();
	}
	public int getOrCreateCartId(int userId) {
		return obj2.getOrCreateCartId(userId);
	}
	@Override
	public boolean addToCart(int cartId, int productId, int quantity, int quantityPerUnit) {
	    return obj2.addToCart(cartId, productId, quantity, quantityPerUnit);
	}
	public List<CartItem> getCartItems(int cartId){
		return obj2.getCartItems(cartId);
	}
	public List<CartItemWithProduct> getCartItemsWithDetails(int cartId){
	    return obj2.getCartItemsWithProductDetails(cartId);
	}

}
