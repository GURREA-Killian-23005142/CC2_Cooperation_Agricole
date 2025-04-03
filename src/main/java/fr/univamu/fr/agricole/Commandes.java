package fr.univamu.fr.agricole;

import java.time.LocalDate;

/**
 * Représente une commande avec ses détails.
 */
public class Commandes {

    /**
     * Identifiant unique de la commande.
     */
    protected int IDCommandes;

    /**
     * Identifiant du panier associé à la commande.
     */
    protected int IDPanier;

    /**
     * Prix total de la commande.
     */
    protected double prixCommandes;

    /**
     * Point relais pour le retrait de la commande.
     */
    protected String relais;

    /**
     * Date prévue pour le retrait de la commande.
     */
    protected LocalDate dateRetrait;

    /**
     * Identifiant de l'utilisateur ayant passé la commande.
     */
    protected int idUtilisateur;

    /**
     * Constructeur avec paramètres pour initialiser une commande.
     *
     * @param IDCommandes Identifiant unique de la commande.
     * @param IDPanier Identifiant du panier associé.
     * @param prixCommandes Prix total de la commande.
     * @param relais Point relais pour le retrait.
     * @param dateRetrait Date prévue pour le retrait.
     */
    public Commandes(int IDCommandes, int IDPanier, double prixCommandes, String relais, LocalDate dateRetrait) {
        this.IDCommandes = IDCommandes;
        this.IDPanier = IDPanier;
        this.prixCommandes = prixCommandes;
        this.relais = relais;
        this.dateRetrait = dateRetrait;
    }

    /**
     * Constructeur par défaut.
     */
    public Commandes() {}

    /**
     * Retourne l'identifiant de la commande.
     * @return L'identifiant de la commande.
     */
    public int getIDCommandes() { return IDCommandes; }

    /**
     * Définit l'identifiant de la commande.
     * @param IDCommandes L'identifiant de la commande.
     */
    public void setIDCommandes(int IDCommandes) { this.IDCommandes = IDCommandes; }

    /**
     * Retourne l'identifiant du panier.
     * @return L'identifiant du panier.
     */
    public int getIDPanier() { return IDPanier; }

    /**
     * Définit l'identifiant du panier.
     * @param IDPanier L'identifiant du panier.
     */
    public void setIDPanier(int IDPanier) { this.IDPanier = IDPanier; }

    /**
     * Retourne le prix total de la commande.
     * @return Le prix total de la commande.
     */
    public double getPrixCommandes() { return prixCommandes; }

    /**
     * Définit le prix total de la commande.
     * @param prixCommandes Le prix total de la commande.
     */
    public void setPrixCommandes(double prixCommandes) { this.prixCommandes = prixCommandes; }

    /**
     * Retourne le point relais de la commande.
     * @return Le point relais.
     */
    public String getRelais() { return relais; }

    /**
     * Définit le point relais de la commande.
     * @param relais Le point relais.
     */
    public void setRelais(String relais) { this.relais = relais; }

    /**
     * Retourne la date de retrait de la commande.
     * @return La date de retrait.
     */
    public LocalDate getDateRetrait() { return dateRetrait; }

    /**
     * Définit la date de retrait de la commande.
     * @param dateRetrait La date de retrait.
     */
    public void setDateRetrait(LocalDate dateRetrait) { this.dateRetrait = dateRetrait; }

    /**
     * Retourne l'identifiant de l'utilisateur ayant passé la commande.
     * @return L'identifiant de l'utilisateur.
     */
    public int getIdUtilisateur() {return idUtilisateur;}

    /**
     * Définit l'identifiant de l'utilisateur ayant passé la commande.
     * @param idUtilisateur L'identifiant de l'utilisateur.
     */
    public void setIdUtilisateur(int idUtilisateur) {this.idUtilisateur = idUtilisateur;}

    /**
     * Retourne une représentation textuelle de la commande.
     * @return Une chaîne contenant les détails de la commande.
     */
    @Override
    public String toString() {
        return "Commande{" +
                "IDCommandes=" + IDCommandes +
                " IDUtilisateur=" + idUtilisateur +
                ", IDPanier=" + IDPanier +
                ", prixCommandes=" + prixCommandes +
                ", relais='" + relais + '\'' +
                ", dateRetrait=" + dateRetrait +
                "}\n";
    }
}