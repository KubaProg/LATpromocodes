package eu.sii.promocodes.service;

import eu.sii.promocodes.exception.promocode.PromoCodeAlreadyExists;
import eu.sii.promocodes.exception.promocode.PromoCodeNotFoundException;
import eu.sii.promocodes.mapper.PromoCodeMapper;
import eu.sii.promocodes.model.PromoCodeAmount;
import eu.sii.promocodes.model.PromoCodePercentage;
import eu.sii.promocodes.model.dto.promoCode.PromoCodeAmountRequestDto;
import eu.sii.promocodes.model.dto.promoCode.PromoCodePercentageRequestDto;
import eu.sii.promocodes.repository.PromoCodeAmountRepository;
import eu.sii.promocodes.repository.PromoCodePercentageRepository;
import eu.sii.promocodes.utils.OperationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PromoCodeService {

    private final PromoCodeAmountRepository promoCodeAmountRepository;
    private final PromoCodePercentageRepository promoCodePercentageRepository;
    private final PromoCodeMapper promoCodeMapper;

    public void addPromoCodeAmount(PromoCodeAmountRequestDto promoCodeAmountRequestDto) {
        OperationUtils.isCurrencyAndDateValid(promoCodeAmountRequestDto.getCurrency(), promoCodeAmountRequestDto.getExpirationDate());
        String code = promoCodeAmountRequestDto.getCode();
        checkCodeIsUnique(code);

        PromoCodeAmount mapped = this.promoCodeMapper.mapToPromoCodeAmount(promoCodeAmountRequestDto);

        this.promoCodeAmountRepository.save(this.promoCodeMapper.mapToPromoCodeAmount(promoCodeAmountRequestDto));
    }

    public void addPromoCodePercentage(PromoCodePercentageRequestDto promoCodePercentageRequestDto) {
        OperationUtils.isCurrencyAndDateValid(promoCodePercentageRequestDto.getCurrency(), promoCodePercentageRequestDto.getExpirationDate());
        String code = promoCodePercentageRequestDto.getCode();
        checkCodeIsUnique(code);

        this.promoCodePercentageRepository.save(this.promoCodeMapper.mapToPromoCodePercentage(promoCodePercentageRequestDto));
    }

    public List<String> getAllPromoCodes() {
        List<String> allPromoCodesPercentageCodes = this.promoCodePercentageRepository.findAll().stream().map(PromoCodePercentage::getCode).toList();
        List<String> allPromoCodesAmountCodes = this.promoCodeAmountRepository.findAll().stream().map(PromoCodeAmount::getCode).toList();

        return Stream.of(allPromoCodesPercentageCodes, allPromoCodesAmountCodes).flatMap(List::stream).toList();
    }

    private void checkCodeIsUnique(String code) {
        this.promoCodeAmountRepository.findByCode(code)
                .ifPresent(promoCode -> {
                    throw new PromoCodeAlreadyExists(code);
                });

        this.promoCodePercentageRepository.findByCode(code)
                .ifPresent(promoCode -> {
                    throw new PromoCodeAlreadyExists(code);
                });
    }

    public Object getPromoCodeDetailsByCode(String code) {
        PromoCodeAmount promoCodeAmount = this.promoCodeAmountRepository.findByCode(code).orElse(null);
        PromoCodePercentage promoCodePercentage = this.promoCodePercentageRepository.findByCode(code).orElse(null);

        if (promoCodeAmount != null) {
            return promoCodeMapper.mapToPromoCodeAmountResponseDto(promoCodeAmount);
        } else if (promoCodePercentage != null) {
            return promoCodeMapper.mapToPromoCodePercentageResponseDto(promoCodePercentage);
        } else {
            throw new PromoCodeNotFoundException(code);
        }
     }

}
