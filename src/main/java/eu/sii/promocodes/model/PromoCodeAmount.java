package eu.sii.promocodes.model;

import eu.sii.promocodes.model.enums.Currency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "promo_codes_amount")
@Getter
@Setter
@ToString
public class PromoCodeAmount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "code", nullable = false, unique = true)
    protected String code;

    @Column(name = "expiration_date", nullable = false)
    protected LocalDateTime expirationDate;

    @Column(name = "currency", nullable = false)
    protected String currency;

    @Column(name = "max_usages", nullable = false)
    protected int maxUsages;

    @Column(name = "current_usages", nullable = false)
    protected int currentUsages;
    @Column(name = "discount_amount", nullable = false)
    private BigDecimal discountAmount;

}