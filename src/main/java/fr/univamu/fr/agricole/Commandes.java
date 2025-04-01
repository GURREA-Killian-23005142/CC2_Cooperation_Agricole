package fr.univamu.fr.agricole;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "commandes")
public class Commandes implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int panierId;
    private Double prixTotal;
    private String relais;
    private LocalDate dateRetrait;

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPanierId() { return panierId; }
    public void setPanierId(int panierId) { this.panierId = panierId; }

    public Double getPrixTotal() { return prixTotal; }
    public void setPrixTotal(Double prixTotal) { this.prixTotal = prixTotal; }

    public String getRelais() { return relais; }
    public void setRelais(String relais) { this.relais = relais; }

    public LocalDate getDateRetrait() { return dateRetrait; }
    public void setDateRetrait(LocalDate dateRetrait) { this.dateRetrait = dateRetrait; }
}
