package eu.sii.promocodes.service;

import eu.sii.promocodes.exception.product.ProductIdNotProvidedException;
import eu.sii.promocodes.exception.product.ProductNotFoundException;
import eu.sii.promocodes.mapper.ProductMapper;
import eu.sii.promocodes.model.Product;
import eu.sii.promocodes.model.dto.product.ProductRequestDto;
import eu.sii.promocodes.model.dto.product.ProductResponseDto;
import eu.sii.promocodes.repository.ProductRepository;
import eu.sii.promocodes.utils.OperationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    public void addProduct(ProductRequestDto productCreateDto) {
        OperationUtils.isRequestCurrencyValid(productCreateDto.getCurrency());
        this.productRepository.save(productMapper.mapToProduct(productCreateDto));
    }

    public List<ProductResponseDto> getAllProducts() {
        return this.productRepository.findAll().stream().map(productMapper::mapToProductResponseDto).toList();
    }

    @Transactional
    public ProductResponseDto updateProduct(@RequestBody ProductRequestDto productCreateDto) {

        Long productId = productCreateDto.getId();

        if (productId == null){
            throw new ProductIdNotProvidedException();
         }

        Product productToBeUpdated = this.productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException(productId));

         productToBeUpdated.setName(productCreateDto.getName());
         productToBeUpdated.setRegularPrice(productCreateDto.getRegularPrice());
         productToBeUpdated.setCurrency(productCreateDto.getCurrency());
         productToBeUpdated.setDescription(productCreateDto.getDescription());

        return productMapper.mapToProductResponseDto(this.productRepository.save(productToBeUpdated));
    }

}
