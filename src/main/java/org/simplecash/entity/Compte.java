package org.simplecash.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCompte;
    private BigDecimal solde;
    private LocalDate dateOuverture;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
