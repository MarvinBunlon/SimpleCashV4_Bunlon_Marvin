package org.simplecash.entity;

import jakarta.persistence.*;

@Entity
public class CompteCourant extends Compte {

    private double decouvert = 1000.0;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public CompteCourant() {}

    public double getDecouvert() { return decouvert; }
    public void setDecouvert(double decouvert) { this.decouvert = decouvert; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
}
