package org.ecommerce.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.ecommerce.domain.Product;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {

}
