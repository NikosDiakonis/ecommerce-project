package org.ecommerce.domain;

public class DiscountStrategyFactory {
    public DiscountStrategy getStrategy(String discountType, double discountValue) {

        switch (discountType) {
            case "FIXED":
                return new FixedDiscount(discountValue);


            case "PERCENT":
                return new PercentageDiscount(discountValue);


            case "B2G1":
                return new Buy2Get1Discount();

                default:
                return null;

        }

    }
}
