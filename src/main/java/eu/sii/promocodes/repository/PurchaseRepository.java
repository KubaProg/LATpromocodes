package eu.sii.promocodes.repository;

import eu.sii.promocodes.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long>{

}
