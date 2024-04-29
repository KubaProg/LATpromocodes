package eu.sii.promocodes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "promo_codes_amount")
public class PromoCodeAmount extends PromoCode {
    @Column(name = "discount_amount", nullable = false)
    private BigDecimal discountAmount;

}