package org.ecommerce.domain;

import jakarta.persistence.Entity;

@Entity
public class DigitalProductEntity extends ProductEntity {
    public String downloadLink;
    public double fileSize;

    public DigitalProductEntity() {
        //JPA needs this
    }

    public DigitalProductEntity(String name, double price, String sku, String downloadLink, double fileSize) {
        super(name, price, sku);
        this.downloadLink = downloadLink;
        this.fileSize = fileSize;
    }

}
