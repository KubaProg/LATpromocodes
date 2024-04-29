package eu.sii.promocodes.exception.product;

import eu.sii.promocodes.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ProductAlreadyExistsException extends BusinessException {
    public ProductAlreadyExistsException(String productName) {
        super(HttpStatus.BAD_REQUEST, String.format("Product called: %s already exist", productName));
    }
}
