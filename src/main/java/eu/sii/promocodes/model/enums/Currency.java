package eu.sii.promocodes.model.enums;

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

}
