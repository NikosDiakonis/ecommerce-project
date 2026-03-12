package org.ecommerce;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    @Test
    public void testProduct() {
        Product p1 = new Product("testName", 100.0, "skuTest");
        String result = p1.getInfo();
        assertEquals("testName skuTest costs 100.0 Euros.",  result );
            }
}
