package org.ecommerce.domain;

import jakarta.persistence.Entity;

@Entity
public class PhysicalProductEntity extends ProductEntity {
   public double weight;
   // TODO: refactor to Dimensions class (length, width, height) when needed
    public String dimensions;



    public PhysicalProductEntity() {

    }

    public PhysicalProductEntity(String name, double price, String sku, double weight) {
        super(name, price, sku);
        this.weight = weight;
    }

}
