package org.ecommerce;

import org.ecommerce.domain.PhysicalProductEntity;
import org.ecommerce.domain.ProductEntity;
import org.ecommerce.repository.ProductRepository;
import org.ecommerce.service.ProductService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceTest {


    @Test
    void shouldDenyDuplicateSku(){
        ProductEntity productEntity = new PhysicalProductEntity("test1", 60,"123",0);
        ProductRepository repository = mock(ProductRepository.class);
        ProductService service = new ProductService(repository);
        when(repository.count("sku = ?1", "123")).thenReturn(1L);
        assertThrows(IllegalArgumentException.class, () -> {
            service.addProduct(productEntity);
        });


    }

    @Test
    void shouldDenyDuplicateName(){
        ProductEntity productEntity = new PhysicalProductEntity("test", 60,"123",10);
        ProductRepository repository = mock(ProductRepository.class);
        ProductService service = new ProductService(repository);
        when(repository.count("name = ?1", "test")).thenReturn(1L);
        assertThrows(IllegalArgumentException.class, () -> {
            service.addProduct(productEntity);
        });
    }
}
