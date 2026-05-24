package org.ecommerce.domain;

import jakarta.persistence.Entity;

@Entity
public class PhysicalProduct  extends Product {
   public double weight;
   // TODO: refactor to Dimensions class (length, width, height) when needed
    public String dimensions;



    public PhysicalProduct() {
        //JPA needs this
    }

    public PhysicalProduct(String name, double price, String sku, double weight) {
        super(name, price, sku);
        this.weight = weight;
    }

}
