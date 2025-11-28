package org.simplecash.entity;

import jakarta.persistence.Entity;

@Entity
public class CompteCourant extends Compte {

    private double decouvert = 1000.0;

    public CompteCourant() {}

    public double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }
}
