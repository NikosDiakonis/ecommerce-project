package org.ecommerce.service;

import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.ecommerce.domain.Discount;
import org.ecommerce.domain.ProductEntity;
import org.ecommerce.domain.ProductSortOption;
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


    public List<ProductEntity> getAllProducts(int page, int size, ProductSortOption sortBy) {
        String sortField = sortBy.name().toLowerCase();
        return repository.findAll(Sort.by(sortField)).page(Page.of(page,size)).list();
    }

    public ProductEntity findById(Long id) {
        return ProductEntity.findById(id);
    }
}
