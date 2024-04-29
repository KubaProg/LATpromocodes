package eu.sii.promocodes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "promo_codes_percentage")
public class PromoCodePercentage extends PromoCode {

    @Column(name = "discount_rate", nullable = false)
    private BigDecimal discountRate;

}