package eu.sii.promocodes.model.dto.product;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProductRequestDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal regularPrice;
    private String currency;
}
