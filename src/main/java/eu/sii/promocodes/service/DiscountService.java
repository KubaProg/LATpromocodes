package eu.sii.promocodes.service;

import eu.sii.promocodes.exception.product.ProductNotFoundException;
import eu.sii.promocodes.model.Product;
import eu.sii.promocodes.model.dto.discount.DiscountResulRequestDto;
import eu.sii.promocodes.model.dto.discount.DiscountResultResponseDto;
import eu.sii.promocodes.repository.ProductRepository;
import eu.sii.promocodes.utils.DiscountCalculatingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscountService {

    private final PromoCodeService promoCodeService;
    private final ProductRepository productRepository;

    public DiscountResultResponseDto getDiscountPrice(DiscountResulRequestDto discountResulRequestDto) {

        Object promoCode = promoCodeService.getPromoCodeDetailsByCode(discountResulRequestDto.getPromoCode());
        Product product = productRepository.findById(discountResulRequestDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(discountResulRequestDto.getProductId()));

        return DiscountCalculatingUtils.calculateDiscountPrice(promoCode, product);
    }

}
