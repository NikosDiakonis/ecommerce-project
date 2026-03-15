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

    @Test
    public void testGrindOption() {
        CoffeeProduct brazil = new CoffeeProduct("Brazil", 50.0, "BrzSku", GrindOption.Whole);

        String resultBrz = brazil.getInfo();
        assertEquals("Brazil BrzSku costs 50.0 Euros.Your Grind choice is Whole.",  resultBrz );
    }

    @Test
    public void testMerchProduct() {
        MerchProduct merch = new MerchProduct("MerchProduct", 70.0, "skuMerch", MerchColor.black);
        String resultMerch = merch.getInfo();
        assertEquals("MerchProduct skuMerch costs 70.0 Euros.Your MerchProduct color is black.",  resultMerch );
    }
}

