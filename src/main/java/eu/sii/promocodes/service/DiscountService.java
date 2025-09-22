package eu.sii.promocodes.service;

import eu.sii.promocodes.exception.product.ProductNotFoundException;
import eu.sii.promocodes.model.Product;
import eu.sii.promocodes.model.dto.discount.DiscountResultResponseDto;
import eu.sii.promocodes.model.dto.discount.SpecialDiscountRequestDto;
import eu.sii.promocodes.model.dto.interfaces.ProductOperationRequest;
import eu.sii.promocodes.repository.ProductRepository;
import eu.sii.promocodes.utils.DiscountCalculatingUtils;
import eu.sii.promocodes.utils.SpecialDiscountUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class DiscountService {

    private final PromoCodeService promoCodeService;
    private final ProductRepository productRepository;

    public DiscountResultResponseDto getDiscountPrice(ProductOperationRequest productOperationRequest) {

        Object promoCode = promoCodeService.getPromoCodeDetailsByCode(productOperationRequest.getPromoCode());
        Product product = productRepository.findById(productOperationRequest.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(productOperationRequest.getProductId()));

        return DiscountCalculatingUtils.calculateDiscountPrice(promoCode, product);
    }

    public DiscountResultResponseDto getSpecialDiscountPrice(SpecialDiscountRequestDto specialDiscountRequestDto) {
        Product product = productRepository.findById(specialDiscountRequestDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(specialDiscountRequestDto.getProductId()));

        BigDecimal discountPercentage = SpecialDiscountUtils.resolveDiscountPercentage(specialDiscountRequestDto.getPromoCode());
        if (discountPercentage.compareTo(BigDecimal.ZERO) <= 0) {
            return DiscountResultResponseDto.builder()
                    .discountPrice(product.getRegularPrice())
                    .discountMessage("Special discount phrase not recognized.")
                    .productPriceCurrency(product.getCurrency())
                    .build();
        }

        BigDecimal multiplier = BigDecimal.ONE.subtract(discountPercentage.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP));
        BigDecimal discountedPrice = product.getRegularPrice().multiply(multiplier).setScale(2, RoundingMode.HALF_UP);

        return DiscountResultResponseDto.builder()
                .discountPrice(discountedPrice.max(BigDecimal.ZERO))
                .discountMessage(String.format("Special discount (%s%%) applied successfully.", discountPercentage.stripTrailingZeros().toPlainString()))
                .productPriceCurrency(product.getCurrency())
                .build();
    }

}
