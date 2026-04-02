package org.ecommerce.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.ecommerce.domain.Product;
import org.ecommerce.repository.ProductRepository;

import java.util.List;

@ApplicationScoped
public class ProductService {
    @Inject
    ProductRepository repository;

    public ProductService() {}

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public void addProduct(Product product){
            if (product.name.equals("") || product.sku.equals("")) {
                throw new IllegalArgumentException("Product name is empty");
            }else if (repository.count("sku = ?1", product.sku) > 0 || repository.count("name = ?1", product.name) > 0) {
                throw new IllegalArgumentException("Duplicate Product");
            } else{
                repository.persist(product);
            }
    }

    public List<Product> getAllProducts(){
        return repository.listAll();
    }
}
