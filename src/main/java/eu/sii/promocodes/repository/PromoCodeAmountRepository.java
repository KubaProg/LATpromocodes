package eu.sii.promocodes.repository;

import eu.sii.promocodes.model.PromoCodeAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoCodeAmountRepository extends JpaRepository<PromoCodeAmount, Long> {
}
