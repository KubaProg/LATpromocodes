package eu.sii.promocodes.exception.promocode;

import eu.sii.promocodes.exception.BusinessException;
import org.springframework.http.HttpStatus;
public class PromoCodeAlreadyExists extends BusinessException {
    public PromoCodeAlreadyExists(String code) {
        super(HttpStatus.BAD_REQUEST, String.format("Promo code with code %s already exists", code));
    }
}
