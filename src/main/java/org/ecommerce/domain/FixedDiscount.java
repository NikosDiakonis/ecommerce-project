package org.ecommerce.domain;

public class FixedDiscount implements DiscountStrategy {
    double fixedAmount;

    public FixedDiscount(double fixedAmount) {
        this.fixedAmount = fixedAmount;
    }

    @Override
    public double applyDiscount(double price) {
        double result = price - fixedAmount;
        return result;
    }
}
