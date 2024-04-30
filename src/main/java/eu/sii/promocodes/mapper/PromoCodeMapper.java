package eu.sii.promocodes.mapper;

import eu.sii.promocodes.model.PromoCodeAmount;
import eu.sii.promocodes.model.PromoCodePercentage;
import eu.sii.promocodes.model.dto.promoCode.PromoCodeAmountRequestDto;
import eu.sii.promocodes.model.dto.promoCode.PromoCodeAmountResponseDto;
import eu.sii.promocodes.model.dto.promoCode.PromoCodePercentageReponseDto;
import eu.sii.promocodes.model.dto.promoCode.PromoCodePercentageRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PromoCodeMapper {

    PromoCodeAmount mapToPromoCodeAmount(PromoCodeAmountRequestDto promoCodeAmountRequestDto);

    PromoCodePercentage mapToPromoCodePercentage(PromoCodePercentageRequestDto promoCodePercentageRequestDto);

    PromoCodeAmountResponseDto mapToPromoCodeAmountResponseDto(PromoCodeAmount promoCodeAmount);

    PromoCodePercentageReponseDto mapToPromoCodePercentageResponseDto(PromoCodePercentage promoCodePercentage);
}
