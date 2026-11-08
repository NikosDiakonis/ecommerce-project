package org.ecommerce.domain;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DiscountStrategyFactory {

    public DiscountStrategy getStrategy(DiscountType discountType, double discountValue) {


        if (discountType == null) {
            throw new IllegalArgumentException("Discount type must not be null");
        }

        DiscountStrategy discountStrategy = switch (discountType) {
            case FIXED -> new FixedDiscount(discountValue);

            case PERCENT -> new PercentageDiscount(discountValue);

            case B2G1 -> new Buy2Get1Discount();

        };
        return  discountStrategy;
    }
}
