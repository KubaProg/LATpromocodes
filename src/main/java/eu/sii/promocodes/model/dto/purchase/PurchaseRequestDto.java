package eu.sii.promocodes.model.dto.purchase;

import eu.sii.promocodes.model.dto.interfaces.ProductOperationRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class PurchaseRequestDto implements ProductOperationRequest {
    @NotNull
    private Long productId;
    @Length(min = 3, max = 24, message = "Code must be between 3 and 24 characters.")
    @Pattern(regexp = "^\\S*$", message = "Code must not contain any whitespace.")
    private String promoCode;
}
