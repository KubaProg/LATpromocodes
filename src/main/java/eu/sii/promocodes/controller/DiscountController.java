package eu.sii.promocodes.controller;

import eu.sii.promocodes.model.dto.discount.DiscountResultResponseDto;
import eu.sii.promocodes.model.dto.discount.DiscountResulRequestDto;
import eu.sii.promocodes.model.dto.discount.SpecialDiscountRequestDto;
import eu.sii.promocodes.service.DiscountService;
import eu.sii.promocodes.utils.OperationUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/discount")
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountService discountService;

    @PostMapping
    public ResponseEntity<DiscountResultResponseDto> getTheDiscountPrice(@Valid @RequestBody DiscountResulRequestDto discountResulRequestDto, BindingResult bindingResult){
        OperationUtils.isRequestValid(bindingResult);
        return ResponseEntity.ok(discountService.getDiscountPrice(discountResulRequestDto));
    }

    @PostMapping("/special")
    public ResponseEntity<DiscountResultResponseDto> getSpecialDiscount(@Valid @RequestBody SpecialDiscountRequestDto specialDiscountRequestDto,
                                                                        BindingResult bindingResult) {
        OperationUtils.isRequestValid(bindingResult);
        return ResponseEntity.ok(discountService.getSpecialDiscountPrice(specialDiscountRequestDto));
    }

}
