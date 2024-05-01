package eu.sii.promocodes.utils;

import eu.sii.promocodes.exception.RequestNotValidException;
import eu.sii.promocodes.exception.general.ExpirationDateNotValidException;
import eu.sii.promocodes.exception.general.WrongCurrencyException;
import eu.sii.promocodes.model.enums.Currency;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class OperationUtils {

    public static void isCurrencyAndDateValid(String currency, String dateStr) {
        isRequestCurrencyValid(currency);
        isDateFormatValid(dateStr);
    }

    public static void isRequestCurrencyValid(String currency) {
        try {
            Currency.valueOf(currency.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new WrongCurrencyException(currency);
        }
    }

    public static LocalDateTime isDateFormatValid(String dateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            return LocalDateTime.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            throw new ExpirationDateNotValidException();
        }
    }

    public static void isRequestValid(BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new RequestNotValidException(bindingResult);
        }
    }
}


