package domain;

public class ProductVegie {
	private int id;
	private String name;
	private String description;
	private int price;
	private String image_address;
	private int quantity_kg;
	
	public ProductVegie() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImage_address() {
		return image_address;
	}

	public void setImage_address(String image_address) {
		this.image_address = image_address;
	}

	public int getQuantity_kg() {
		return quantity_kg;
	}

	public void setQuantity_kg(int quantity_kg) {
		this.quantity_kg = quantity_kg;
	}
	
	
	
}
