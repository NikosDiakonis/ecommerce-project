package org.ecommerce;

import org.ecommerce.domain.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiscountStrategyFactoryTest {
    @Test
    public void testGetDiscountStrategyFactory() {
        DiscountStrategyFactory dSFTestOne = new DiscountStrategyFactory();
        DiscountStrategy result = dSFTestOne.getStrategy(DiscountType.FIXED, 10.00);
        FixedDiscount fixed = assertInstanceOf(FixedDiscount.class, result);
        assertEquals(10.0, fixed.fixedAmount());
    }

    @Test
    public void testGetDiscountStrategyFactory2() {
        DiscountStrategyFactory dSFTestTwo = new DiscountStrategyFactory();
        DiscountStrategy result = dSFTestTwo.getStrategy(DiscountType.PERCENT, 50);
        PercentageDiscount perDi = assertInstanceOf(PercentageDiscount.class, result);
        assertEquals(50.0, perDi.percentage());
    }


    @Test
    public void testGetDiscountStrategyFactory3() {
        DiscountStrategyFactory dSFTestThree = new DiscountStrategyFactory();
        DiscountStrategy result = dSFTestThree.getStrategy(DiscountType.B2G1, 0);
        assertInstanceOf(Buy2Get1Discount.class, result);
    }

    @Test
    public void shouldThrowWhenDiscountTypeIsUnknown(){
        DiscountStrategyFactory dSFTestFour = new DiscountStrategyFactory();
        assertThrows(IllegalArgumentException.class, () -> dSFTestFour.getStrategy(null, 5));

    }

}



