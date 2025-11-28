package org.simplecash.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class CompteEpargne extends Compte {

    private double taux = 0.03;
}
