package org.simplecash.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String adresse;
    private String ville;

    @OneToOne
    @JoinColumn(name = "gerant_id")
    private Gerant gerant;

    @OneToMany(mappedBy = "agence", cascade = CascadeType.ALL)
    private List<Conseiller> conseillers = new ArrayList<>();

    public Agence() {}

    public Long getId() { return id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public Gerant getGerant() { return gerant; }
    public void setGerant(Gerant gerant) {
        this.gerant = gerant;
        if (gerant != null) {
            gerant.setAgence(this);
        }
    }

    public List<Conseiller> getConseillers() { return conseillers; }

    public void addConseiller(Conseiller conseiller) {
        conseillers.add(conseiller);
        conseiller.setAgence(this);
    }
}
