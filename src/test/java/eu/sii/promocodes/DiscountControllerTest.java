package eu.sii.promocodes;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.sii.promocodes.controller.DiscountController;
import eu.sii.promocodes.service.DiscountService;
import eu.sii.promocodes.model.dto.discount.DiscountResulRequestDto;
import eu.sii.promocodes.model.dto.discount.DiscountResultResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = PromocodesApplication.class)
@AutoConfigureMockMvc
public class DiscountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private DiscountService discountService;

    @Test
    public void testGetTheDiscountPrice() throws Exception {
        DiscountResulRequestDto discountRequest = new DiscountResulRequestDto(
                1L,
                "SAVE10"
        );

        DiscountResultResponseDto discountResponse = DiscountResultResponseDto.builder()
                .discountPrice(BigDecimal.valueOf(899.99))
                .discountMessage("10% discount applied successfully")
                .productPriceCurrency("USD")
                .build();

        when(discountService.getDiscountPrice(discountRequest)).thenReturn(discountResponse);

        mockMvc.perform(post("/discount")
                        .content(asJsonString(discountRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
