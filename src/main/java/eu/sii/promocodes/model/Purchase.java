package eu.sii.promocodes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "purchases")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_of_purchase", nullable = false)
    private LocalDateTime dateOfPurchase;

    @Column(name = "regular_price", nullable = false)
    private BigDecimal regularPrice;

    @Column(name = "discount_applied")
    private BigDecimal discountApplied;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}
