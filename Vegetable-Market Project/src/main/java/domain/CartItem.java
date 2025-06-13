package domain;

public class CartItem {
	private int itemId;
	private int cartId;
	private int productId;
	private int quantity;
	private int quantityPerUnit;
	
	public CartItem() {}

	public CartItem(int itemId, int cartId, int productId, int quantity, int quantityPerUnit) {
		super();
		this.itemId = itemId;
		this.cartId = cartId;
		this.productId = productId;
		this.quantity = quantity;
		this.quantityPerUnit = quantityPerUnit;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantityPerUnit() {
		return quantityPerUnit;
	}

	public void setQuantityPerUnit(int quantityPerUnit) {
		this.quantityPerUnit = quantityPerUnit;
	}
	
}
