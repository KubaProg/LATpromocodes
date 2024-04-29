package eu.sii.promocodes.exception.promocode;

import eu.sii.promocodes.exception.BusinessException;
import org.springframework.http.HttpStatus;
public class PromoCodeDiscountAmountFormatException extends BusinessException {
    public PromoCodeDiscountAmountFormatException() {
        super(HttpStatus.BAD_REQUEST, ("Discount amount must have at most two decimal places."));
    }
}
