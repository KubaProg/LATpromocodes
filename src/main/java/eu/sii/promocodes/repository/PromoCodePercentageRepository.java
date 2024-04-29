package eu.sii.promocodes.repository;

import eu.sii.promocodes.model.PromoCodePercentage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoCodePercentageRepository extends JpaRepository<PromoCodePercentage, Long>{
}
