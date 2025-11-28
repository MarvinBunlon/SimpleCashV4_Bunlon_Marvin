package org.simplecash.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class CompteEpargne extends Compte {

    private double taux = 0.03;

    @OneToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Client client;

    public CompteEpargne() {}

    public double getTaux() { return taux; }
    public void setTaux(double taux) { this.taux = taux; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
}
