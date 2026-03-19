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

    @Override
    public String getInfo() {
        String resultA = super.getInfo();
        String resultB = "Your MerchProduct color is " + this.color + ".";
        String result = resultA + resultB;

        return result;
    }


}

