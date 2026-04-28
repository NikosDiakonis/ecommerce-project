package org.ecommerce.domain;

import jakarta.persistence.Entity;

@Entity
public class DigitalProduct  extends Product {
    public String downloadLink;
    public double fileSize;

    public DigitalProduct() {
        this.downloadLink = downloadLink;
        this.fileSize = fileSize;
    }

    public DigitalProduct(String name, double price, String sku, String downloadLink, double fileSize) {
        super(name, price, sku);
        this.downloadLink = downloadLink;
        this.fileSize = fileSize;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }
}
