package org.ecommerce;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductServiceTest {
    @Test
    void shouldDenyWhenFieldsEmpty(){
        Product product = new Product("", 60,"");
        ProductService service = new ProductService();
        assertThrows(IllegalArgumentException.class, () -> {
            service.addProduct(product);
        });
    }
}
