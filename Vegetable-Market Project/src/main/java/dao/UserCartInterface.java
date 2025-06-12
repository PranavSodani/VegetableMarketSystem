package dao;

public interface UserCartInterface {
	public int getOrCreateCartId(int userId);
	boolean addToCart(int cartId, int productId, int quantity, int quantityPerUnit);
}
