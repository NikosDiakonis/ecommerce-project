package org.ecommerce.domain;

public class Buy2Get1Discount implements DiscountStrategy {


    @Override
    public double applyDiscount(double price,int quantity) {
       int freeAmount = quantity / 3;
       int paidAmount = quantity - freeAmount;
       double result = paidAmount * price;
       return result;
    }
}
