package eu.sii.promocodes.controller;

import eu.sii.promocodes.model.dto.purchase.PurchaseRequestDto;
import eu.sii.promocodes.service.PurchaseService;
import eu.sii.promocodes.utils.OperationUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;
    @PostMapping
    public ResponseEntity<String> purchase(@Valid @RequestBody PurchaseRequestDto purchaseRequestDto, BindingResult bindingResult){
        OperationUtils.isRequestValid(bindingResult);

        purchaseService.purchaseProduct(purchaseRequestDto);
        return ResponseEntity.ok("The purchase has been completed successfully");
    }

}
