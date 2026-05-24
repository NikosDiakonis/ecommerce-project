
package org.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include =  JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PhysicalProduct.class, name = "physical"),
                @JsonSubTypes.Type(value = DigitalProduct.class, name = "digital")
})

public abstract class Product extends PanacheEntity {
   public String name;
   public double price;
   public String sku;

   @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
   public List<Discount> discounts = new ArrayList<>();

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

