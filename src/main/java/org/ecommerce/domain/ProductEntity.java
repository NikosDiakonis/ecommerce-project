
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
        @JsonSubTypes.Type(value = PhysicalProductEntity.class, name = "physical"),
                @JsonSubTypes.Type(value = DigitalProductEntity.class, name = "digital")
})

public abstract class ProductEntity extends PanacheEntity {

   public String name;
   public double price;
   public String sku;

   @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
   public List<Discount> discounts = new ArrayList<>();

    public ProductEntity() {
    }


    public ProductEntity(String name, double price, String sku) {
        this.name = name;
        this.price = price;
        this.sku = sku;
    }




}

