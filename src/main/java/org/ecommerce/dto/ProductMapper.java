package org.ecommerce.dto;

import org.ecommerce.domain.DigitalProductEntity;
import org.ecommerce.domain.PhysicalProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface ProductMapper {
    PhysicalProductEntity toPhysicalEntity(ProductRequestDTO dto);
    DigitalProductEntity toDigitalEntity (ProductRequestDTO dto);
}
