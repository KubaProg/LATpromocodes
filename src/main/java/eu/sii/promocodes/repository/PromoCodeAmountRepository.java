package eu.sii.promocodes.repository;

import eu.sii.promocodes.model.PromoCodeAmount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromoCodeAmountRepository extends JpaRepository<PromoCodeAmount, Long> {
}
