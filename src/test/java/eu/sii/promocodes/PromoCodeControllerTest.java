package eu.sii.promocodes;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.sii.promocodes.service.PromoCodeService;
import eu.sii.promocodes.model.dto.promoCode.PromoCodeAmountRequestDto;
import eu.sii.promocodes.model.dto.promoCode.PromoCodePercentageRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = PromocodesApplication.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@TestPropertySource(properties = "spring.sql.init.mode=never")
public class PromoCodeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PromoCodeService promoCodeService;

    @Test
    public void testAddPromoCodeAmount() throws Exception {
        PromoCodeAmountRequestDto promoCodeAmountRequestDto = new PromoCodeAmountRequestDto(
                1L, "SAVE20", String.valueOf(LocalDate.of(2024, 6, 30)), "USD", 100, BigDecimal.valueOf(50.00)
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/promocode/amount")
                        .content(asJsonString(promoCodeAmountRequestDto))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("The amount promo code has been saved successfully"));
    }

    @Test
    public void testAddPromoCodePercentage() throws Exception {
        PromoCodePercentageRequestDto promoCodePercentageRequestDto = new PromoCodePercentageRequestDto(
                1L, "DISCOUNT10", String.valueOf(LocalDate.of(2024, 12, 31)), "USD", 50, BigDecimal.valueOf(0.5)
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/promocode/percentage")
                        .content(asJsonString(promoCodePercentageRequestDto))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("The percentage promo code has been saved successfully"));
    }

    @Test
    public void testGetAllPromoCodes() throws Exception {
        List<String> promoCodes = List.of("SAVE20", "DISCOUNT10");

        when(promoCodeService.getAllPromoCodes()).thenReturn(promoCodes);

        mockMvc.perform(MockMvcRequestBuilders.get("/promocode/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("SAVE20"))
                .andExpect(jsonPath("$[1]").value("DISCOUNT10"));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
