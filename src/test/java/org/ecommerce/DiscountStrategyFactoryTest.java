package org.ecommerce;

import org.ecommerce.domain.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class DiscountStrategyFactoryTest {
    @Test
    public void testGetDiscountStrategyFactory() {
        DiscountStrategyFactory dSFTestOne = new DiscountStrategyFactory();
        DiscountStrategy result = dSFTestOne.getStrategy("FIXED",10.00);
        FixedDiscount fixed = assertInstanceOf(FixedDiscount.class, result);
        assertEquals(10.0,fixed.fixedAmount());
    }

    @Test
    public void testGetDiscountStrategyFactory2() {
        DiscountStrategyFactory dSFTestTwo = new DiscountStrategyFactory();
        DiscountStrategy result = dSFTestTwo.getStrategy("PERCENT",50);
        PercentageDiscount perDi =  assertInstanceOf(PercentageDiscount.class, result);
        assertEquals(50.0, perDi.percentage());
    }


    @Test
    public void testGetDiscountStrategyFactory3() {
        DiscountStrategyFactory dSFTestThree = new DiscountStrategyFactory();
        DiscountStrategy result = dSFTestThree.getStrategy("B2G1", 0);
        assertInstanceOf(Buy2Get1Discount.class, result);
    }
}

