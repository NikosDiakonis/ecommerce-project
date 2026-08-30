package org.ecommerce;

import org.ecommerce.domain.DigitalProductEntity;
import org.ecommerce.domain.Discount;
import org.ecommerce.domain.DiscountType;
import org.ecommerce.domain.PhysicalProductEntity;
import org.ecommerce.service.PricingService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PricingServiceTest {
    @Test
    public void pricingServiceTest1() {
        PricingService serviceTest1 = new PricingService();
        PhysicalProductEntity espresso = new PhysicalProductEntity("Espresso",60,"ESP-001",500);
        Discount discount = new Discount();
        discount.discountType = DiscountType.FIXED;
        discount.discountValue = 10.00;
        espresso.discounts.add(discount);
        double result = serviceTest1.calculatePrice(espresso, 3);
        assertEquals(150,result);

    }

    @Test
    public void pricingServiceTest2() {
        PricingService serviceTest2 = new PricingService();
        PhysicalProductEntity espressoBlend = new PhysicalProductEntity("EspressoBlend",60,"ESBL-001",500);
        Discount discount = new Discount();
        discount.discountType = DiscountType.PERCENT;
        discount.discountValue = 50;
        espressoBlend.discounts.add(discount);
        double result = serviceTest2.calculatePrice(espressoBlend, 3);
        assertEquals(90,result);
    }

    @Test
    public void pricingServiceTest3() {
        PricingService serviceTest3 = new PricingService();
        PhysicalProductEntity espressoGold = new PhysicalProductEntity("EspressoGold",60,"ESGL-001",500);
        Discount discountFixed = new Discount();
        discountFixed.discountType = DiscountType.FIXED;
        discountFixed.discountValue = 10;
        espressoGold.discounts.add(discountFixed);
        Discount discountPercent = new Discount();
        discountPercent.discountType = DiscountType.PERCENT;
        discountPercent.discountValue = 50;
        espressoGold.discounts.add(discountPercent);
        double result = serviceTest3.calculatePrice(espressoGold, 3);
        assertEquals(90,result);
    }

    @Test
    public void pricingServiceTest4() {
        PricingService serviceTest4 = new PricingService();
        DigitalProductEntity recipesEbook = new DigitalProductEntity("BestRecipies", 100, "EBK001","download.com/download",600);
        Discount discount = new Discount();
        discount.discountType = DiscountType.B2G1;
        discount.discountValue = 0;
        recipesEbook.discounts.add(discount);
        double result = serviceTest4.calculatePrice(recipesEbook, 3);
        assertEquals(200,result);
    }

}
