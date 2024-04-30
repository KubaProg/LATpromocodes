package eu.sii.promocodes.repository;

import eu.sii.promocodes.model.PromoCodeAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromoCodeAmountRepository extends JpaRepository<PromoCodeAmount, Long> {

    Optional<PromoCodeAmount> findByCode(String code);

}
