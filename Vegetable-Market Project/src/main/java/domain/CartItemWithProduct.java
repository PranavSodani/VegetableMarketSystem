package domain;

public class CartItemWithProduct extends CartItem {
    private String productName;
    private String productDescription;
    private String imageAddress;
    private int price;

    public CartItemWithProduct() {}

    public CartItemWithProduct(int itemId, int cartId, int productId, int quantity, int quantityPerUnit,
                               String productName, String productDescription, String imageAddress, int price) {
        super(itemId, cartId, productId, quantity, quantityPerUnit);
        this.productName = productName;
        this.productDescription = productDescription;
        this.imageAddress = imageAddress;
        this.price = price;
    }

    // Getters and setters for new fields
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getProductDescription() { return productDescription; }
    public void setProductDescription(String productDescription) { this.productDescription = productDescription; }

    public String getImageAddress() { return imageAddress; }
    public void setImageAddress(String imageAddress) { this.imageAddress = imageAddress; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
}
