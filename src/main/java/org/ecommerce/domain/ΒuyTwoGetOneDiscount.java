package org.ecommerce.domain;

public class ΒuyTwoGetOneDiscount implements DiscountStrategy {
    int quantity;

    public ΒuyTwoGetOneDiscount(int quantity){
        this.quantity = quantity;
    }

    @Override
    public double applyDiscount(double price) {
       int freeAmount = quantity / 3;
       int paidAmount = quantity - freeAmount;
       double result = paidAmount * price;
       return result;
    }
}
