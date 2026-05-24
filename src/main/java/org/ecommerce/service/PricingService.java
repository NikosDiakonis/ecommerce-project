package org.ecommerce.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.ecommerce.domain.Discount;
import org.ecommerce.domain.DiscountStrategy;
import org.ecommerce.domain.DiscountStrategyFactory;
import org.ecommerce.domain.Product;

@ApplicationScoped
public class PricingService {

    private  DiscountStrategyFactory factory = new  DiscountStrategyFactory();



    public PricingService(){}

    public PricingService(DiscountStrategyFactory factory) {
        this.factory = factory;
    }

    public double calculatePrice(Product product, int quantity) {
        double bestValue = product.price*quantity;
        double finalResult = bestValue;

        // TODO: Implement priority-based discount selection
        // Currently using Best Value strategy (lowest price wins)
        // Future: add 'priority' field to Discount entity
        // Higher priority wins (e.g. priority=2 FIXED overrides priority=1 PERCENT)
        // Equal priority = stack (apply both)

        for( Discount discount : product.discounts) {

            DiscountStrategy activeStrategy = this.factory.getStrategy(discount.discountType,discount.discountValue);
            bestValue = activeStrategy.applyDiscount(product.price, quantity);
            if (finalResult > bestValue) {
                finalResult = bestValue;
            }
        }
        return finalResult;
    }

}
