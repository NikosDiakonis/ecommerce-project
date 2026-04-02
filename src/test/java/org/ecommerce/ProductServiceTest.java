package org.ecommerce;

import org.ecommerce.domain.Product;
import org.ecommerce.repository.ProductRepository;
import org.ecommerce.service.ProductService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceTest {
    @Test
    void shouldDenyWhenNameIsEmpty(){
        Product product = new Product("", 60,"123");
        ProductService service = new ProductService();
        assertThrows(IllegalArgumentException.class, () -> {

            service.addProduct(product);
        });
    }

    @Test
    void shouldDenyWhenSKUIsEmpty(){
        Product product = new Product("", 60,"");
        ProductService service = new ProductService();
        assertThrows(IllegalArgumentException.class, () -> {
            service.addProduct(product);
        });
    }

    @Test
    void shouldDenyDuplicateSku(){
        Product product = new Product("test1", 60,"123");
        ProductRepository repository = mock(ProductRepository.class);
        ProductService service = new ProductService(repository);
        when(repository.count("sku = ?1", "123")).thenReturn(1L);
        assertThrows(IllegalArgumentException.class, () -> {
            service.addProduct(product);
        });


    }

    @Test
    void shouldDenyDuplicateName(){
        Product product = new Product("test", 60,"123");
        ProductRepository repository = mock(ProductRepository.class);
        ProductService service = new ProductService(repository);
        when(repository.count("name = ?1", "test")).thenReturn(1L);
        assertThrows(IllegalArgumentException.class, () -> {
            service.addProduct(product);
        });
    }
}
