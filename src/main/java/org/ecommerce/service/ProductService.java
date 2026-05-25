package org.ecommerce.service;

import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.ecommerce.domain.Discount;
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

    public void addProduct(Product product) {
        //TODO: add @NotBlank validation on the request object and remove this validation check
        if (product.name.equals("") || product.sku.equals("")) {
            throw new IllegalArgumentException("Product name is empty");
            //TODO: add unique indexes on sku and name and remove this if
        } else if (repository.count("sku = ?1", product.sku) > 0 || repository.count("name = ?1", product.name) > 0) {
            throw new IllegalArgumentException("Duplicate Product");
        } else {
            // Re-attach discounts to this product before saving.
            // Because we use @JsonIgnore on Discount to prevent infinite JSON loops,
            // the incoming JSON leaves discount.product as null.
            // We must set it manually here so Hibernate saves the foreign key (product_id) correctly.
            if (product.discounts != null) {
                for (Discount discount : product.discounts) {
                    discount.product = product;
                }
            }


            repository.persist(product);
            //TODO: after the persist function, the id will have a value, use this to create a response object
        }
    }

    public List<Product> getAllProducts(int page, int size, String sortBy) {
        return repository.findAll(Sort.by(sortBy)).page(Page.of(page,size)).list();
    }
}
