package org.ecommerce.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.ecommerce.domain.DiscountType;

public class DiscountRequestDTO {
    @NotNull(message = "Type must be not null")
    public DiscountType discountType;
    @Positive(message = "Value must be greater than zero")
    public double discountValue;

}
