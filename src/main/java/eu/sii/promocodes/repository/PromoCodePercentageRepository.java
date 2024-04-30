package eu.sii.promocodes.repository;

import eu.sii.promocodes.model.PromoCodePercentage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromoCodePercentageRepository extends JpaRepository<PromoCodePercentage, Long>{

    Optional<PromoCodePercentage> findByCode(String code);

}
