package org.ecommerce.dto;

import org.ecommerce.domain.DigitalProductEntity;
import org.ecommerce.domain.PhysicalProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface ProductMapper {
    PhysicalProductEntity toPhysicalEntity(PhysicalProductRequestDTO dto);
    DigitalProductEntity toDigitalEntity (DigitalProductRequestDTO dto);
}
