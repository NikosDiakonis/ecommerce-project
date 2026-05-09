package org.ecommerce;

import org.ecommerce.domain.FixedDiscount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FixedDiscountTest {
    @Test
    public void testFixedDiscount() {
        FixedDiscount f1 = new FixedDiscount(10);
        double result = f1.applyDiscount(100.0,1);
        assertEquals(90.0,result);
    }

}
