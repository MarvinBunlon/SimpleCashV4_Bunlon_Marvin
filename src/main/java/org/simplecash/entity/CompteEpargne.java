package org.simplecash.entity;

import jakarta.persistence.Entity;

@Entity
public class CompteEpargne extends Compte {

    private double taux = 0.03;

    public CompteEpargne() {}

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }
}
