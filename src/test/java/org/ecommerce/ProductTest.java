package org.ecommerce;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    @Test
    public void testProduct() {
        Product p1 = new Product("testName", 100.0, "skuTest",grindOption.Ground);
        String result = p1.getInfo();
        assertEquals("testName skuTest costs 100.0 Euros.Your Grind choice is Ground.",  result );
            }

    @Test
    public void testgrindOption() {
        Product brazil = new Product("Brazil", 50.0, "BrzSku", grindOption.Whole);

        String resultBrz = brazil.getInfo();
        assertEquals("Brazil BrzSku costs 50.0 Euros.Your Grind choice is Whole.",  resultBrz );
    }
}

