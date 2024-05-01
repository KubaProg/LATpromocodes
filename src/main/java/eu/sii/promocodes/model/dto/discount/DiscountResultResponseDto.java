package eu.sii.promocodes.model.dto.discount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class DiscountResultResponseDto {
    private BigDecimal discountPrice;
    private String discountMessage;
    private String productPriceCurrency;
}
