package org.ecommerce;

public class Product {
    String name;
    double price;
    String sku;
    grindOption grindOpt;


    public Product(String name, double price, String sku, grindOption grind) {
        this.name = name;
        this.price = price;
        this.sku = sku;
        this.grindOpt = grind;
    }

    public void setGrindOpt(grindOption grindOpt) {
        this.grindOpt = grindOpt;
    }

    public grindOption getGrindOpt() {
        return grindOpt;
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

    }

    public String getInfo() {
        String result = this.name + " " + this.sku + " " + "costs " + this.price + " Euros.Your Grind choice is " + this.grindOpt + ".";
        return result;
    }
}

