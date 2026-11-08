package org.ecommerce.service;

import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.ecommerce.domain.Discount;
import org.ecommerce.domain.ProductEntity;
import org.ecommerce.domain.ProductSortOption;
import org.ecommerce.dto.DiscountRequestDTO;
import org.ecommerce.dto.ProductMapper;
import org.ecommerce.repository.ProductRepository;

import java.util.List;

@ApplicationScoped
public class ProductService {
    @Inject
    ProductRepository repository;

    @Inject ProductMapper mapper;

    public ProductEntity addProduct(ProductEntity productEntity) {

            // Re-attach discounts to this productEntity before saving.
            // Because we use @JsonIgnore on Discount to prevent infinite JSON loops,
            // the incoming JSON leaves discount.productEntity as null.
            // We must set it manually here so Hibernate saves the foreign key (product_id) correctly.
            if (productEntity.discounts != null) {
                //TODO: Add a test with null discounts
                for (Discount discount : productEntity.discounts) {
                    //TODO: add a test with non empty discounts list
                    discount.productEntity = productEntity;
                }
            }


            repository.persist(productEntity);
            //TODO: Add a message broker to publish an event that a product was added. Decide if a topic (publish-subscribe model with durable subscription). see: https://trello.com/c/SElRZ9gJ
            return productEntity;

        }


    public List<ProductEntity> getAllProducts(int page, int size, ProductSortOption sortBy) {
        String sortField = sortBy.name().toLowerCase();
        return repository.findAll(Sort.by(sortField)).page(Page.of(page,size)).list();
    }

    public ProductEntity findById(Long id) {
        return ProductEntity.findById(id);
    }

    public Discount addDiscount (Long productId, DiscountRequestDTO dto){

      ProductEntity entity =  findById(productId);
      Discount discount = mapper.toDiscount(dto);
      discount.productEntity = entity;
      discount.persist();
      return discount;

    }
}

