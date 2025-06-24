package dao;

import java.util.List;

import domain.ProductVegie;

public interface VendorProductInterface {
	List<ProductVegie> getProductsByVendor(int vendorId);
    void insertProduct(ProductVegie product, int vendorId);
    void updateProduct(ProductVegie product);
    public void deleteProduct(int productId, int vendorId);
    ProductVegie getProductById(int productId);
	List<ProductVegie> getProductsByVendorId(int vendorId); 
	
}
