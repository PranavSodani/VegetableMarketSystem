package dao;

import java.util.List;

import domain.CartItem;

public interface UserCartInterface {
	public int getOrCreateCartId(int userId);
	boolean addToCart(int cartId, int productId, int quantity, int quantityPerUnit);
	List<CartItem> getCartItems(int cartId);
}
