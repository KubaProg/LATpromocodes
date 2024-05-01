package eu.sii.promocodes.exception.product;

import eu.sii.promocodes.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends BusinessException {
    public ProductNotFoundException(Long productId) {
        super(HttpStatus.NOT_FOUND, String.format("Product with id: %s not found, please provide existing id", productId));
    }
}
