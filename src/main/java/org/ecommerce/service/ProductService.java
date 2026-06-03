package org.ecommerce.service;

import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.ecommerce.domain.Discount;
import org.ecommerce.domain.ProductEntity;
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

    public void addProduct(ProductEntity productEntity) {
        //TODO: add @NotBlank validation on the request object and remove this validation check
        if (productEntity.name.equals("") || productEntity.sku.equals("")) {
            throw new IllegalArgumentException("ProductEntity name is empty");
            //TODO: add unique indexes on sku and name and remove this if
        } else if (repository.count("sku = ?1", productEntity.sku) > 0 || repository.count("name = ?1", productEntity.name) > 0) {
            throw new IllegalArgumentException("Duplicate ProductEntity");
        } else {
            // Re-attach discounts to this productEntity before saving.
            // Because we use @JsonIgnore on Discount to prevent infinite JSON loops,
            // the incoming JSON leaves discount.productEntity as null.
            // We must set it manually here so Hibernate saves the foreign key (product_id) correctly.
            if (productEntity.discounts != null) {
                for (Discount discount : productEntity.discounts) {
                    discount.productEntity = productEntity;
                }
            }


            repository.persist(productEntity);
            //TODO: after the persist function, the id will have a value, use this to create a response object
        }
    }

    public List<ProductEntity> getAllProducts(int page, int size, String sortBy) {
        return repository.findAll(Sort.by(sortBy)).page(Page.of(page,size)).list();
    }
}
