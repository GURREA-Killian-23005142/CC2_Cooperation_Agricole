package fr.univamu.fr.agricole;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "commandes")
public class Commandes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO-INCREMENT
    @Column(name = "IDCommandes")
    private int IDCommandes;

    @Column(name = "IDPanier")
    private int IDPanier;

    @Column(name = "prixCommandes")
    private double prixCommandes;

    @Column(name = "relais")
    private String relais;

    @Column(name = "dateRetrait")
    private LocalDate dateRetrait;

    public Commandes(int IDCommandes, int IDPanier, double prixCommandes, String relais, LocalDate dateRetrait) {
        this.IDCommandes = IDCommandes;
        this.IDPanier = IDPanier;
        this.prixCommandes = prixCommandes;
        this.relais = relais;
        this.dateRetrait = dateRetrait;
    }

    public Commandes() {}

    public int getIDCommandes() { return IDCommandes; }
    public void setIDCommandes(int IDCommandes) { this.IDCommandes = IDCommandes; }

    public int getIDPanier() { return IDPanier; }
    public void setIDPanier(int IDPanier) { this.IDPanier = IDPanier; }

    public double getPrixCommandes() { return prixCommandes; }
    public void setPrixCommandes(double prixCommandes) { this.prixCommandes = prixCommandes; }

    public String getRelais() { return relais; }
    public void setRelais(String relais) { this.relais = relais; }

    public LocalDate getDateRetrait() { return dateRetrait; }
    public void setDateRetrait(LocalDate dateRetrait) { this.dateRetrait = dateRetrait; }

    @Override
    public String toString() {
        return "Commande{" +
                "IDCommandes=" + IDCommandes +
                ", IDPanier=" + IDPanier +
                ", prixCommandes=" + prixCommandes +
                ", relais='" + relais + '\'' +
                ", dateRetrait=" + dateRetrait +
                '}';
    }
}
