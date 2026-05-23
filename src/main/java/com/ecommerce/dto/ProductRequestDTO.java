package com.ecommerce.dto;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    @NotBlank(message = "Product name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @Positive(message = "Price must be greater than 0")
    private BigDecimal price;

    @PositiveOrZero(message = "Stock cannot be negative")
    private Integer stock;

    @NotNull(message = "Category ID is required")
    private Long categoryId;
}
