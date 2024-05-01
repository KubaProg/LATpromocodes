package eu.sii.promocodes.model.dto.promoCode;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class PromoCodePercentageRequestDto {
    private Long id;

    @NotBlank(message = "Code cannot be blank.")
    @NotNull(message = "Code can not be null.")
    @Length(min = 3, max = 24, message = "Code must be between 3 and 24 characters.")
    @Pattern(regexp = "^\\S*$", message = "Code must not contain any whitespace.")
    private String code;

    @NotNull(message = "Expiration date can not be null.")
    @NotBlank(message = "Expiration date can not be blank.")
    private String expirationDate;

    @NotBlank(message = "Currency cannot be blank.")
    @NotNull(message = "Currency can not be null.")
    private String currency;

    @NotNull(message = "Max usages can not be null.")
    @Min(value = 1, message = "Max usages must be at least 1.")
    private int maxUsages;

    @NotNull(message = "Discount rate can not be null.")
    @DecimalMin(value = "0.01", message = "Discount rate must be at least 0.01.")
    @DecimalMax(value = "1.0", message = "Discount rate must be at most 1.0.")
    private BigDecimal discountRate;
}
