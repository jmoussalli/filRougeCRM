package fr.moussalli.projetfilrouge.model;

import fr.moussalli.projetfilrouge.enums.OrderState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "type_presta")
    private String typePresta;

    @Column(name = "designation")
    private String designation;

    @Column(name = "client_id")
    private Integer clientId;

    @Column(name = "nb_days")
    private Integer nbDays;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Transient
    @Column(name = "total_exclude_taxe")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal totalExcludeTaxe;

    @Transient
    @Column(name = "total_with_taxe")
    private BigDecimal totalWithTaxe;

    @Column(name = "state")
    private OrderState state;

}