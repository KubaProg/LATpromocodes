package eu.sii.promocodes.exception.promocode;

import eu.sii.promocodes.exception.BusinessException;
import org.springframework.http.HttpStatus;
public class PromoCodeNotFoundException extends BusinessException {
    public PromoCodeNotFoundException() {
        super(HttpStatus.NOT_FOUND, ("Discount amount must have at most two decimal places."));
    }
}
