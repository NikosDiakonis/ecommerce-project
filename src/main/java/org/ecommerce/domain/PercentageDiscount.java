package org.ecommerce.domain;

public record PercentageDiscount(double percentage) implements DiscountStrategy {

    @Override
    public double applyDiscount(double price, int quantity) {
        double finalPercentage = 0.01 * percentage;
        double result = (price * (1 - finalPercentage)) * quantity;
        return result;
    }
}
