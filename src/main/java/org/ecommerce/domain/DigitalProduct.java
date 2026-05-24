package org.ecommerce.domain;

import jakarta.persistence.Entity;

@Entity
public class DigitalProduct  extends Product {
    public String downloadLink;
    public double fileSize;

    public DigitalProduct() {
        //JPA needs this
    }

    public DigitalProduct(String name, double price, String sku, String downloadLink, double fileSize) {
        super(name, price, sku);
        this.downloadLink = downloadLink;
        this.fileSize = fileSize;
    }

}
