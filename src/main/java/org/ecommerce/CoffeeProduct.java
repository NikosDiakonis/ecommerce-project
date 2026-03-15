package org.ecommerce;

public class CoffeeProduct extends Product {
    GrindOption grindOpt;


    public CoffeeProduct(String name, double price, String sku, GrindOption option){
       super(name,price,sku);
        this.grindOpt = option;
    }

    public void setGrindOpt(GrindOption grindOpt) {
        this.grindOpt = grindOpt;
    }

    public GrindOption getGrindOpt() {
        return grindOpt;
    }

    public String getInfo() {
        String result = this.name + " " + this.sku + " " + "costs " + this.price + " Euros.Your Grind choice is " + this.grindOpt + ".";
        return result;
    }

}



