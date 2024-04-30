package eu.sii.promocodes.exception.promocode;

import eu.sii.promocodes.exception.BusinessException;
import org.springframework.http.HttpStatus;
public class PromoCodeNotFoundException extends BusinessException {
    public PromoCodeNotFoundException(String code ) {
        super(HttpStatus.NOT_FOUND, String.format("Promo code with code %s not found", code));
    }
}
