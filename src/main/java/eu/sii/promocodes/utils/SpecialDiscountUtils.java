package eu.sii.promocodes.utils;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.Locale;

@UtilityClass
public class SpecialDiscountUtils {

    private static final BigDecimal TEN_PERCENT = BigDecimal.TEN;
    private static final BigDecimal FIFTY_PERCENT = BigDecimal.valueOf(50);
    private static final BigDecimal NINETY_PERCENT = BigDecimal.valueOf(90);

    public static BigDecimal resolveDiscountPercentage(String phrase) {
        if (phrase == null) {
            return BigDecimal.ZERO;
        }
        String normalized = phrase.trim().toUpperCase(Locale.ROOT);
        return switch (normalized) {
            case "MINI" -> TEN_PERCENT;
            case "EXTRA" -> FIFTY_PERCENT;
            case "EXTRA90", "EXTRA_90", "EXTRA-90", "MEGAEXTRA", "SUPEREXTRA" -> NINETY_PERCENT;
            default -> BigDecimal.ZERO;
        };
    }
}
