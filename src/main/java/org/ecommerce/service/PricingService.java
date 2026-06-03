package org.ecommerce.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.ecommerce.domain.Discount;
import org.ecommerce.domain.DiscountStrategy;
import org.ecommerce.domain.DiscountStrategyFactory;
import org.ecommerce.domain.ProductEntity;

@ApplicationScoped
public class PricingService {

    private  DiscountStrategyFactory factory = new  DiscountStrategyFactory();



    public PricingService(){}

    public PricingService(DiscountStrategyFactory factory) {
        this.factory = factory;
    }

    public double calculatePrice(ProductEntity productEntity, int quantity) {
        double bestValue = productEntity.price*quantity;
        double finalResult = bestValue;

        // TODO: Implement priority-based discount selection
        // Currently using Best Value strategy (lowest price wins)
        // Future: add 'priority' field to Discount entity
        // Higher priority wins (e.g. priority=2 FIXED overrides priority=1 PERCENT)
        // Equal priority = stack (apply both)

        for( Discount discount : productEntity.discounts) {

            DiscountStrategy activeStrategy = this.factory.getStrategy(discount.discountType,discount.discountValue);
            bestValue = activeStrategy.applyDiscount(productEntity.price, quantity);
            if (finalResult > bestValue) {
                finalResult = bestValue;
            }
        }
        return finalResult;
    }

}
