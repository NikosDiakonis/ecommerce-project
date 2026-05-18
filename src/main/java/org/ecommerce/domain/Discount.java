package org.ecommerce.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
@Entity
public class Discount extends PanacheEntity {
    public String discountType;
    public double discountValue;
    @ManyToOne
    public Product product;
}
