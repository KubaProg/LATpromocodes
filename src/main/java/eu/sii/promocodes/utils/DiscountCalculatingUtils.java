package eu.sii.promocodes.utils;

import eu.sii.promocodes.model.Product;
import eu.sii.promocodes.model.dto.discount.DiscountResultResponseDto;
import eu.sii.promocodes.model.dto.promoCode.PromoCodeAmountResponseDto;
import eu.sii.promocodes.model.dto.promoCode.PromoCodePercentageReponseDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class DiscountCalculatingUtils {

    public static DiscountResultResponseDto calculateDiscountPrice(Object promoCode, Product product) {
        return calculateDiscount(promoCode, product);
    }

    private static DiscountResultResponseDto calculateDiscount(Object promoCode, Product product) {
        if (promoCode instanceof PromoCodeAmountResponseDto promoCodeAmountResponseDto) {
            return calculateForPromoCodeAmount(promoCodeAmountResponseDto, product);
        } else if (promoCode instanceof PromoCodePercentageReponseDto promoCodePercentageReponseDto) {
            return calculateForPromoCodePercentage(promoCodePercentageReponseDto, product);
        } else {
            throw new IllegalArgumentException("Unknown promo code type.");
        }
    }

    private static DiscountResultResponseDto calculateForPromoCodeAmount(PromoCodeAmountResponseDto promoCodeAmountResponseDto, Product product) {
        DiscountResultResponseDto validationResult = validatePromoCode(promoCodeAmountResponseDto, product);
        if (validationResult != null) return validationResult;

        BigDecimal discountPrice = calculateDiscountPriceForAmount(product.getRegularPrice(), promoCodeAmountResponseDto.getDiscountAmount());

        return DiscountResultResponseDto.builder()
                .discountPrice(discountPrice)
                .discountMessage("Amount discount applied successfully")
                .productPriceCurrency(promoCodeAmountResponseDto.getCurrency())
                .build();
    }

    private static DiscountResultResponseDto calculateForPromoCodePercentage(PromoCodePercentageReponseDto promoCodePercentageReponseDto, Product product) {
        DiscountResultResponseDto validationResult = validatePromoCode(promoCodePercentageReponseDto, product);
        if (validationResult != null) return validationResult;

        BigDecimal discountPrice = calculateDiscountPriceForPercentage(product.getRegularPrice(), promoCodePercentageReponseDto.getDiscountRate());

        return DiscountResultResponseDto.builder()
                .discountPrice(discountPrice)
                .discountMessage("Percentage discount applied successfully")
                .productPriceCurrency(promoCodePercentageReponseDto.getCurrency())
                .build();
    }

    private static DiscountResultResponseDto validatePromoCode(Object promoCode, Product product) {
        LocalDateTime expirationDate = LocalDateTime.parse((promoCode instanceof PromoCodeAmountResponseDto)
                ? ((PromoCodeAmountResponseDto) promoCode).getExpirationDate()
                : ((PromoCodePercentageReponseDto) promoCode).getExpirationDate());

        if (expirationDateExpired(expirationDate)) {
            return DiscountResultResponseDto.builder()
                    .discountPrice(product.getRegularPrice())
                    .discountMessage("The expiration date of the given promo code has expired.")
                    .productPriceCurrency(product.getCurrency())
                    .build();
        }

        String promoCodeCurrency = (promoCode instanceof PromoCodeAmountResponseDto)
                ? ((PromoCodeAmountResponseDto) promoCode).getCurrency()
                : ((PromoCodePercentageReponseDto) promoCode).getCurrency();

        if (currencyDoesNotMatch(promoCodeCurrency, product.getCurrency())) {
            return DiscountResultResponseDto.builder()
                    .discountPrice(product.getRegularPrice())
                    .discountMessage("The currency of the given promo code does not match the currency of the product.")
                    .productPriceCurrency(product.getCurrency())
                    .build();
        }

        int currentUsages = (promoCode instanceof PromoCodeAmountResponseDto)
                ? ((PromoCodeAmountResponseDto) promoCode).getCurrentUsages()
                : ((PromoCodePercentageReponseDto) promoCode).getCurrentUsages();

        int maxUsages = (promoCode instanceof PromoCodeAmountResponseDto)
                ? ((PromoCodeAmountResponseDto) promoCode).getMaxUsages()
                : ((PromoCodePercentageReponseDto) promoCode).getMaxUsages();

        if (maxUsagesNumberExceeded(currentUsages, maxUsages)) {
            return DiscountResultResponseDto.builder()
                    .discountPrice(product.getRegularPrice())
                    .discountMessage("The maximum number of usages of the given promo code has been exceeded.")
                    .productPriceCurrency(product.getCurrency())
                    .build();
        }

        return null;
    }

    private static boolean maxUsagesNumberExceeded(int currentUsages, int maxUsages) {
        return currentUsages >= maxUsages;
    }

    private static boolean currencyDoesNotMatch(String promoCodeCurrency, String productCurrency) {
        return !promoCodeCurrency.equals(productCurrency);
    }

    private static boolean expirationDateExpired(LocalDateTime expirationDate) {
        return expirationDate.isBefore(LocalDateTime.now());
    }

    private static BigDecimal calculateDiscountPriceForAmount(BigDecimal regularPrice, BigDecimal discountAmount) {
        BigDecimal amountDiscountPrice = regularPrice.subtract(discountAmount);
        return amountDiscountPrice.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : amountDiscountPrice;
    }

    private static BigDecimal calculateDiscountPriceForPercentage(BigDecimal regularPrice, BigDecimal discountRate) {
        BigDecimal percentageDiscountPrice = regularPrice.multiply(BigDecimal.ONE.subtract(discountRate.divide(BigDecimal.valueOf(100)))).setScale(2, RoundingMode.HALF_UP);
        return percentageDiscountPrice.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : percentageDiscountPrice;
    }

}
