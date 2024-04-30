package eu.sii.promocodes.exception.general;

import eu.sii.promocodes.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ExpirationDateNotValidException extends BusinessException {
    public ExpirationDateNotValidException() {
        super(HttpStatus.BAD_REQUEST, ("Expiration date is not valid, please provide date in format yyyy-MM-dd'T'HH:mm:ss"));
    }
}
