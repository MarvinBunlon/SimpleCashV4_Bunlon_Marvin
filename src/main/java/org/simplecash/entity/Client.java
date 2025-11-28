package org.simplecash.entity;

import jakarta.persistence.*;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String adresse;
    private String codePostal;
    private String ville;
    private String telephone;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private CompteCourant compteCourant;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private CompteEpargne compteEpargne;

    public Client() {}

    public Long getId() {
        return id;
    }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getCodePostal() { return codePostal; }
    public void setCodePostal(String codePostal) { this.codePostal = codePostal; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public CompteCourant getCompteCourant() { return compteCourant; }
    public void setCompteCourant(CompteCourant compteCourant) { this.compteCourant = compteCourant; }

    public CompteEpargne getCompteEpargne() { return compteEpargne; }
    public void setCompteEpargne(CompteEpargne compteEpargne) { this.compteEpargne = compteEpargne; }
}
