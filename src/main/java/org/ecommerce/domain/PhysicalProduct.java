package org.ecommerce.domain;

import jakarta.persistence.Entity;

@Entity
public class PhysicalProduct  extends Product {
   public double weight;

    public PhysicalProduct() {
        this.weight = weight;
    }

    public PhysicalProduct(String name, double price, String sku, double weight) {
        super(name, price, sku);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
