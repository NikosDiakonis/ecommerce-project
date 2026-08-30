package org.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
@Entity
public class Discount extends PanacheEntity {
    public DiscountType discountType;
    public double discountValue;
    // Prevents infinite JSON loop.
    @JsonIgnore
    @ManyToOne
    public ProductEntity productEntity;
}
