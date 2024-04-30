package eu.sii.promocodes.exception.general;

import eu.sii.promocodes.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class WrongCurrencyException extends BusinessException {
    public WrongCurrencyException(String currencyCode) {
        super(HttpStatus.BAD_REQUEST, String.format("Currency with code: %s does not exist, please provide EUR or USD or PLN in request", currencyCode));
    }
}
