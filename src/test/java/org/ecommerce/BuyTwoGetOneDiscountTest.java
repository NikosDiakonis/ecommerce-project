package org.ecommerce;

import org.ecommerce.domain.Buy2Get1Discount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BuyTwoGetOneDiscountTest {
    @Test
    public void testBuyTwoGetOneDiscount() {
        Buy2Get1Discount b1 = new Buy2Get1Discount();
        double result = b1.applyDiscount(20, 4);
        assertEquals(60, result);

    }

    @Test
    public void shouldThrowWhenQuantityIsNegative() {
        Buy2Get1Discount b1 = new Buy2Get1Discount();
        assertThrows(IllegalArgumentException.class,
                () -> b1.applyDiscount(20, -5));
    }
}


