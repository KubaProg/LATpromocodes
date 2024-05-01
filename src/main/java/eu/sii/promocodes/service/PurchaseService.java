package eu.sii.promocodes.service;

import eu.sii.promocodes.exception.product.ProductNotFoundException;
import eu.sii.promocodes.model.Product;
import eu.sii.promocodes.model.Purchase;
import eu.sii.promocodes.model.dto.discount.DiscountResultResponseDto;
import eu.sii.promocodes.model.dto.purchase.PurchaseRequestDto;
import eu.sii.promocodes.repository.ProductRepository;
import eu.sii.promocodes.repository.PurchaseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final DiscountService discountService;
    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;
    private final PromoCodeService promoCodeService;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Transactional
    public void purchaseProduct(PurchaseRequestDto purchaseRequestDto) {
        DiscountResultResponseDto discountPrice = null;

        if(purchaseRequestDto.getPromoCode()!=null){
           discountPrice = discountService.getDiscountPrice(purchaseRequestDto);
        }

        Product productToBePurchased = productRepository.findById(purchaseRequestDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(purchaseRequestDto.getProductId()));


        purchaseRepository.save(Purchase.builder()
                .product(productToBePurchased)
                .regularPrice(productToBePurchased.getRegularPrice())
                .dateOfPurchase(LocalDateTime.parse(LocalDateTime.now().format(formatter)))
                .discountApplied(discountPrice != null ?  productToBePurchased.getRegularPrice().subtract(discountPrice.getDiscountPrice()) : BigDecimal.ZERO)
                .build());

        promoCodeService.updatePromoCodeUsages(purchaseRequestDto.getPromoCode());
    }
}
