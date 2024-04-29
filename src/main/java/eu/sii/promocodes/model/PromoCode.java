package eu.sii.promocodes.model;

import eu.sii.promocodes.model.enums.Currency;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class PromoCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "code", nullable = false, unique = true)
    protected String code;

    @Column(name = "expiration_date", nullable = false)
    protected LocalDateTime expirationDate;

    @Column(name = "currency", nullable = false)
    protected Currency currency;

    @Column(name = "max_usages", nullable = false)
    protected int maxUsages;

    @Column(name = "current_usages", nullable = false)
    protected int currentUsages;
}
