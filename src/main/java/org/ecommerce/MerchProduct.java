package org.ecommerce;

public class MerchProduct extends Product {
    MerchColor color;

    public MerchProduct(String name, double price, String sku, MerchColor color) {
        super(name, price, sku);
        this.color = color;
    }

    public MerchColor getColor() {
        return color;
    }

    public void setColor(MerchColor color) {
        this.color = color;
    }
    public String getInfo() {
        String result = this.name + " " + this.sku + " " + "costs " + this.price + " Euros.Your MerchProduct color is " + this.color + ".";
        return result;
    }
}

