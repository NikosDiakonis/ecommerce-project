package org.ecommerce;

public class ProductService {

    public void addProduct(Product product){
            if (product.name.equals("")){
                throw new IllegalArgumentException("Product name is empty");
            }
    }
}
