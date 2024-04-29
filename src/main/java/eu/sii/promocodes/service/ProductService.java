package eu.sii.promocodes.service;

import eu.sii.promocodes.exception.product.ProductIdNotProvidedException;
import eu.sii.promocodes.exception.product.ProductNotFoundException;
import eu.sii.promocodes.mapper.ProductMapper;
import eu.sii.promocodes.model.Product;
import eu.sii.promocodes.model.dto.product.ProductRequestDto;
import eu.sii.promocodes.model.dto.product.ProductResponseDto;
import eu.sii.promocodes.repository.ProductRepository;
import eu.sii.promocodes.utils.ProductUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public void addProduct(ProductRequestDto productCreateDto) {
        ProductUtils.isProductRequestValid(productCreateDto);
        this.productRepository.save(productMapper.mapToProduct(productCreateDto));
    }

    public List<ProductResponseDto> getAllProducts() {
        return this.productRepository.findAll().stream().map(productMapper::mapToProductResponseDto).toList();
    }

    public ProductResponseDto updateProduct(@RequestBody ProductRequestDto productCreateDto) {
        Long productId = productCreateDto.getId();

        if(productId == null){
            throw new ProductIdNotProvidedException();
        }

        Product productToBeUpdated = this.productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException(productId));


        if (productCreateDto.getName() != null) {
            productToBeUpdated.setName(productCreateDto.getName());
        }
        if (productCreateDto.getRegularPrice() != null) {
            productToBeUpdated.setRegularPrice(productCreateDto.getRegularPrice());
        }
        if (productCreateDto.getCurrency() != null) {
            productToBeUpdated.setCurrency(productCreateDto.getCurrency());
        }
        productToBeUpdated.setDescription(productCreateDto.getDescription());

        return productMapper.mapToProductResponseDto(this.productRepository.save(productToBeUpdated));
    }

}