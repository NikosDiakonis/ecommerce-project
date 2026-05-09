package org.ecommerce.domain;

public record FixedDiscount(double fixedAmount) implements DiscountStrategy {


    @Override
    public double applyDiscount(double price, int quantity) {
        double result = (price - fixedAmount) *  quantity;
        return result;
    }
}
