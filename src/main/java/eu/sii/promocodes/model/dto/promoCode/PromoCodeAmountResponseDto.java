package eu.sii.promocodes.model.dto.promoCode;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PromoCodeAmountResponseDto {
    private Long id;
    private String code;
    private String expirationDate;
    private String currency;
    private int maxUsages;
    private int currentUsages;
    private BigDecimal discountAmount;
}
