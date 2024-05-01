package eu.sii.promocodes.model.dto.promoCode;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
public class PromoCodeAmountRequestDto {
    private Long id;

    @NotBlank(message = "Code cannot be blank.")
    @NotNull(message = "Code can not be null.")
    @Length(min = 3, max = 24, message = "Code must be between 3 and 24 characters.")
    @Pattern(regexp = "^\\S*$", message = "Code must not contain any whitespace.")
    private String code;

    @NotNull(message = "Expiration date can not be null.")
    @NotBlank(message = "Expiration date can not be blank.")
    private String expirationDate;

    @NotNull(message = "Currency can not be null.")
    @NotBlank(message = "Currency cannot be blank.")
    private String currency;

    @NotNull(message = "Max usages can not be null.")
    @Min(value = 1, message = "Max usages must be at least 1.")
    private int maxUsages;

    @NotNull(message = "Discount amount can not be null.")
    @DecimalMin(value = "0.1", message = "Discount amount must be at least 0.1.")
    private BigDecimal discountAmount;

}
