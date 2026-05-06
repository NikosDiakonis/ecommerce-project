package org.ecommerce.domain;

public class PercentageDiscount implements DiscountStrategy {
    double percentage;


    public PercentageDiscount(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public double applyDiscount(double price) {
        double finalPercentage = 0.01 * percentage;
        double result = price * (1-finalPercentage);
        return result;
    }
}
