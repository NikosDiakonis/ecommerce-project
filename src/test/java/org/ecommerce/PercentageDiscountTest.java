package org.ecommerce;

import org.ecommerce.domain.PercentageDiscount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PercentageDiscountTest {
    @Test
    public void testPercentageDiscount() {
        PercentageDiscount p1 = new PercentageDiscount(25);
        double result = p1.applyDiscount(100,1);
        assertEquals(75,result);
    }
}
