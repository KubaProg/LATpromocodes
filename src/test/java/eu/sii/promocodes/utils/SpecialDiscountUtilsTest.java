package eu.sii.promocodes.utils;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpecialDiscountUtilsTest {

    @Test
    void shouldReturnTenPercentForMiniPhrase() {
        BigDecimal discount = SpecialDiscountUtils.resolveDiscountPercentage("mini");

        assertEquals(BigDecimal.TEN, discount);
    }
}
