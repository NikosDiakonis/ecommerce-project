
package org.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

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

    @NotBlank(message = "Name should not be blank")
   public String name;
    @Positive(message = "Price must be greater than zero")
   public double price;
    @NotBlank(message = "sku should not be blank")
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

