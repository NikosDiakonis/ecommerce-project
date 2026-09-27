package org.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class DigitalProductRequestDTO {
    @NotBlank(message = "Name should not be blank")
    public String name;

    @Positive(message = "Price must be greater than zero")
    public double price;

    @NotBlank(message = "SKU should not be blank")
    public String sku;

    public String downloadLink;
    public double fileSizeInBytes;
}
