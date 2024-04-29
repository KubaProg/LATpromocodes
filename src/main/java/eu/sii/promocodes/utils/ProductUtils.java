package eu.sii.promocodes.utils;

import eu.sii.promocodes.exception.currency.WrongCurrencyException;
import eu.sii.promocodes.exception.product.ProductRequestNotCompleteException;
import eu.sii.promocodes.model.dto.product.ProductRequestDto;
import eu.sii.promocodes.model.enums.Currency;

public class ProductUtils {

    public static void isProductRequestValid(ProductRequestDto productCreateDto){
        isProductComplete(productCreateDto);
        isProductRequestCurrencyValid(productCreateDto);
    }

    private static void isProductRequestCurrencyValid(ProductRequestDto productCreateDto) {
        try {
            Currency.valueOf(productCreateDto.getCurrency().toUpperCase());
              } catch (IllegalArgumentException e) {
            throw new WrongCurrencyException(productCreateDto.getCurrency());
        }
    }

    private static void isProductComplete(ProductRequestDto productCreateDto) {
        boolean allRequiredPassed = productCreateDto.getCurrency() != null &&
                productCreateDto.getName() != null &&
                productCreateDto.getRegularPrice() != null;

        if(!allRequiredPassed){
            throw new ProductRequestNotCompleteException();
        }

    }
}
