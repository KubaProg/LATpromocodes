package eu.sii.promocodes.controller;

import eu.sii.promocodes.exception.RequestNotValidException;
import eu.sii.promocodes.model.dto.discount.DiscountResulRequestDto;
import eu.sii.promocodes.model.dto.discount.DiscountResultResponseDto;
import eu.sii.promocodes.model.dto.promoCode.PromoCodeAmountRequestDto;
import eu.sii.promocodes.model.dto.promoCode.PromoCodePercentageRequestDto;
import eu.sii.promocodes.service.DiscountService;
import eu.sii.promocodes.service.PromoCodeService;
import eu.sii.promocodes.utils.OperationUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promocode")
@RequiredArgsConstructor
public class PromoCodeController {
    private final PromoCodeService promoCodeService;

    @PostMapping("/amount")
    public ResponseEntity<String> addPromoCodeAmount(@Valid @RequestBody PromoCodeAmountRequestDto promoCodeAmountRequestDto, BindingResult bindingResult) {
        OperationUtils.isRequestValid(bindingResult);

        this.promoCodeService.addPromoCodeAmount(promoCodeAmountRequestDto);
        return ResponseEntity.ok("The amount promo code has been saved successfully");
    }

    @PostMapping("/percentage")
    public ResponseEntity<String> addPromoCodeAmount(@Valid @RequestBody PromoCodePercentageRequestDto promoCodePercentageRequestDto, BindingResult bindingResult) {
        OperationUtils.isRequestValid(bindingResult);

        this.promoCodeService.addPromoCodePercentage(promoCodePercentageRequestDto);
        return ResponseEntity.ok("The percentage promo code has been saved successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllPromoCodes() {
        return ResponseEntity.ok(this.promoCodeService.getAllPromoCodes());
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getPromoCodeDetailsByCode(@PathVariable String code) {
        return ResponseEntity.ok(this.promoCodeService.getPromoCodeDetailsByCode(code));
    }



}
