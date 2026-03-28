package org.ecommerce;

public class ProductService {
    private ProductRepository repository;

    public ProductService() {}

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public void addProduct(Product product){
            if (product.name.equals("") || product.sku.equals("")) {
                throw new IllegalArgumentException("Product name is empty");
            }else if (repository.count("sku = ?1", product.sku) > 0 || repository.count("name = ?1", product.name) > 0) {
                throw new IllegalArgumentException("Duplicate Product");
            }
    }
}
