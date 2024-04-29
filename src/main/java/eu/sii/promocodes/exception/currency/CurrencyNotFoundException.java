package eu.sii.promocodes.exception.currency;

import eu.sii.promocodes.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class CurrencyNotFoundException extends BusinessException {
    public CurrencyNotFoundException(String currencyCode) {
        super(HttpStatus.NOT_FOUND, String.format("Currency with code: %s already exist", currencyCode));
    }
}
