package org.ecommerce;

import org.ecommerce.domain.PhysicalProduct;
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
        Product product = new PhysicalProduct("", 60,"123",10);
        ProductService service = new ProductService();
        assertThrows(IllegalArgumentException.class, () -> {

            service.addProduct(product);
        });
    }

    @Test
    void shouldDenyWhenSKUIsEmpty(){
        Product product =new PhysicalProduct("", 60,"",10);
        ProductService service = new ProductService();
        assertThrows(IllegalArgumentException.class, () -> {
            service.addProduct(product);
        });
    }

    @Test
    void shouldDenyDuplicateSku(){
        Product product = new PhysicalProduct("test1", 60,"123",0);
        ProductRepository repository = mock(ProductRepository.class);
        ProductService service = new ProductService(repository);
        when(repository.count("sku = ?1", "123")).thenReturn(1L);
        assertThrows(IllegalArgumentException.class, () -> {
            service.addProduct(product);
        });


    }

    @Test
    void shouldDenyDuplicateName(){
        Product product = new PhysicalProduct("test", 60,"123",10);
        ProductRepository repository = mock(ProductRepository.class);
        ProductService service = new ProductService(repository);
        when(repository.count("name = ?1", "test")).thenReturn(1L);
        assertThrows(IllegalArgumentException.class, () -> {
            service.addProduct(product);
        });
    }
}
