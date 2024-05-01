package eu.sii.promocodes.model.dto.discount;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class DiscountResulRequestDto {
    @NotNull
    private Long productId;
    @NotBlank(message = "Code cannot be blank.")
    @NotNull(message = "Code can not be null.")
    @Length(min = 3, max = 24, message = "Code must be between 3 and 24 characters.")
    @Pattern(regexp = "^\\S*$", message = "Code must not contain any whitespace.")
    private String promoCode;
}
