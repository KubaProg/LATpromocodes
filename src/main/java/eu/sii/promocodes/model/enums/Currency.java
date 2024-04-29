package eu.sii.promocodes.model.enums;

import eu.sii.promocodes.exception.currency.CurrencyNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Currency {
    USD("USD"),
    EUR("EUR"),
    PLN("PLN");

    private final String code;

    @Override
    public String toString() {
        return code;
    }

    public static Currency getCurrencyFromCode(String code) {
        for (Currency currency : Currency.values()) {
            if (currency.getCode().equalsIgnoreCase(code)) {
                return currency;
            }
        }
        throw new CurrencyNotFoundException(code);
    }
}
