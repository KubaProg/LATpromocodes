package eu.sii.promocodes;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.sii.promocodes.controller.ProductController;
import eu.sii.promocodes.model.dto.product.ProductRequestDto;
import eu.sii.promocodes.model.dto.product.ProductResponseDto;
import eu.sii.promocodes.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = PromocodesApplication.class)
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testAddProduct() throws Exception {
        ProductRequestDto productCreateDto = new ProductRequestDto(
                1L,
                "Smartphone",
                "A flagship smartphone with 5G connectivity and OLED display.",
                BigDecimal.valueOf(799.99),
                "USD"
        );

        mockMvc.perform(post("/product")
                        .content(asJsonString(productCreateDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("The product has been saved successfully"));
    }

    @Test
    public void testGetAllProducts() throws Exception {
        List<ProductResponseDto> mockProductList = List.of(
                new ProductResponseDto(1L, "Smartphone", "A flagship smartphone with 5G connectivity and OLED display.", BigDecimal.valueOf(799.99), "USD"),
                new ProductResponseDto(2L, "Laptop", "A high-end gaming laptop with 16GB RAM and RTX 3070 GPU.", BigDecimal.valueOf(1299.99), "USD")
        );

        when(productService.getAllProducts()).thenReturn(mockProductList);

        mockMvc.perform(get("/product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Smartphone"))
                .andExpect(jsonPath("$[0].regularPrice").value(799.99))
                .andExpect(jsonPath("$[1].name").value("Laptop"))
                .andExpect(jsonPath("$[1].regularPrice").value(1299.99));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
