package org.ecommerce.domain;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DiscountStrategyFactory {

    public DiscountStrategy getStrategy(DiscountType discountType, double discountValue) {

        if (discountType == null) {
            throw new IllegalArgumentException("Discount type must not be null");
        }

        switch (discountType) {
            case FIXED:
                return new FixedDiscount(discountValue);

            case PERCENT:
                return new PercentageDiscount(discountValue);

            case B2G1:
                return new Buy2Get1Discount();

            default:
                throw new IllegalArgumentException("Unknown discount type: " + discountType);
        }
    }
}