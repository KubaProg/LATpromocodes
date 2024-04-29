package eu.sii.promocodes.exception.product;

import eu.sii.promocodes.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ProductRequestNotCompleteException extends BusinessException {
    public ProductRequestNotCompleteException() {
        super(HttpStatus.BAD_REQUEST, ("Product request is not complete, please provide name, price and currency in request"));
    }
}
