package eu.sii.promocodes.controller;

import eu.sii.promocodes.exception.RequestNotValidException;
import eu.sii.promocodes.model.dto.product.ProductRequestDto;
import eu.sii.promocodes.model.dto.product.ProductResponseDto;
import eu.sii.promocodes.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    ResponseEntity<String> addProduct(@Valid @RequestBody ProductRequestDto productCreateDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestNotValidException(bindingResult);
        }

        this.productService.addProduct(productCreateDto);
        return ResponseEntity.ok("The product has been saved successfully");
    }

    @GetMapping
    ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(this.productService.getAllProducts());
    }

    @PutMapping
    ResponseEntity<ProductResponseDto> updateProduct(@RequestBody ProductRequestDto productCreateDto) {
        return ResponseEntity.ok(this.productService.updateProduct(productCreateDto));
    }

}
