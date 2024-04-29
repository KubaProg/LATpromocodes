package eu.sii.promocodes.exception.product;

import eu.sii.promocodes.exception.BusinessException;
import org.springframework.http.HttpStatus;
public class ProductIdNotProvidedException extends BusinessException {
    public ProductIdNotProvidedException() {
        super(HttpStatus.BAD_REQUEST, ("Request for updating product must contain id"));
    }
}
