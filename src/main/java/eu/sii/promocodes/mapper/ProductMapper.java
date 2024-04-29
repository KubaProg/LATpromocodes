package eu.sii.promocodes.mapper;

import eu.sii.promocodes.model.Product;
import eu.sii.promocodes.model.dto.product.ProductRequestDto;
import eu.sii.promocodes.model.dto.product.ProductResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    Product mapToProduct(ProductRequestDto productCreateDto);

    ProductResponseDto mapToProductResponseDto(Product product);

}
