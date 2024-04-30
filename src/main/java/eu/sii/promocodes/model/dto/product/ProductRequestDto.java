package eu.sii.promocodes.model.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequestDto {
    private Long id;

    @NotBlank(message = "Product name cannot be blank.")
    @NotNull(message = "Product name can not be null.")
    private String name;

    @NotBlank(message = "Product description cannot be blank.")
    private String description;

    @NotNull(message = "Product regular price cannot be null.")
    @Min(value = 0, message = "Product regular price must be greater than 0.")
    private BigDecimal regularPrice;

    @NotBlank(message = "Currency cannot be blank.")
    @NotNull(message = "Currency cannot be null.")
    private String currency;
}