package org.ecommerce;

import org.ecommerce.domain.ΒuyTwoGetOneDiscount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyTwoGetOneDiscountTest {
    @Test
    public void testBuyTwoGetOneDiscount() {
        ΒuyTwoGetOneDiscount b1 = new ΒuyTwoGetOneDiscount();
        double result = b1.applyDiscount(20,4);
        assertEquals(60,result);

    }
}
