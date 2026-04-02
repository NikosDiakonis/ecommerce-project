package org.ecommerce.domain;

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

    @Override
    public String getInfo() {
        String resultA = super.getInfo();
        String resultB = "Your Grind choice is " + this.grindOpt + ".";
        String result = resultA + resultB;

        return result;
    }

}



