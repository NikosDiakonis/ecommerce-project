
package org.ecommerce;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Product extends PanacheEntity {
    String name;
    double price;
    String sku;

    public Product() {
    }

    public Product(String name, double price, String sku) {
        this.name = name;
        this.price = price;
        this.sku = sku;
    }


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getSku() {
        return sku;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getInfo() {
        String result = this.name + " " + this.sku + " " + "costs " + this.price + " Euros.";
        return result;
    }
}

