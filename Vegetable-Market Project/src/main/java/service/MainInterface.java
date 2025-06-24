package service;

import java.util.List;

import domain.CartItem;
import domain.CartItemWithProduct;
import domain.ProductVegie;
import domain.User;

public interface MainInterface {
	// register user implementation 
	boolean registerUser(User user);
	// login user implementation 
	User loginUser(String email,String password);
	// for all the vegie portions
	List<ProductVegie> getAllProducts();
	// for getting the cart id
	public int getOrCreateCartId(int userId);
	boolean addToCart(int cartId, int productId, int quantity, int quantityPerUnit);
	List<CartItem> getCartItems(int cartId);
	List<CartItemWithProduct> getCartItemsWithDetails(int cartId);
	boolean deleteCartByItemId(int itemId);
	boolean decreaseQuantity(int itemId,int quantity);
	List<ProductVegie> getProductsByVendorId(Integer userId);
}
	