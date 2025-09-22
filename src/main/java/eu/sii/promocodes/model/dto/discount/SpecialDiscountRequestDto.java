package eu.sii.promocodes.model.dto.discount;

import eu.sii.promocodes.model.dto.interfaces.ProductOperationRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpecialDiscountRequestDto implements ProductOperationRequest {

    @NotNull
    private Long productId;

    @NotBlank(message = "Secret phrase cannot be blank.")
    @Pattern(regexp = "^\\S+$", message = "Secret phrase must not contain whitespace.")
    private String promoCode;
}
