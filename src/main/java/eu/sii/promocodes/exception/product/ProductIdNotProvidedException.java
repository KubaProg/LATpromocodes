package eu.sii.promocodes.exception.product;

import eu.sii.promocodes.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ProductIdNotProvidedException extends BusinessException {
    public ProductIdNotProvidedException() {
        super(HttpStatus.BAD_REQUEST,"Please provide product id in order to update it");
    }
}
