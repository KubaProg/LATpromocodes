package eu.sii.promocodes.repository;

import eu.sii.promocodes.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
