package fr.univamu.fr.agricole;

/**
 * La classe Produit représente un produit avec ses propriétés telles que
 * l'identifiant, le nom, la catégorie, le prix et la quantité.
 */
public class Produit {

    private int idProduit;
    private String nomProduit;
    private String categorieProduit;
    private double prixProduit;
    private int quantiteProduit;

    /**
     * Constructeur permettant d'initialiser toutes les propriétés du produit.
     *
     * @param idProduit Identifiant unique du produit.
     * @param nomProduit Nom du produit.
     * @param categorieProduit Catégorie à laquelle appartient le produit.
     * @param prixProduit Prix unitaire du produit.
     * @param quantiteProduit Quantité disponible en stock.
     */
    public Produit(int idProduit, String nomProduit, String categorieProduit, double prixProduit, int quantiteProduit) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.categorieProduit = categorieProduit;
        this.prixProduit = prixProduit;
        this.quantiteProduit = quantiteProduit;
    }

    /**
     * Constructeur par défaut.
     */
    public Produit() {
    }

    /**
     * Obtient l'identifiant du produit.
     *
     * @return L'identifiant unique du produit.
     */
    public int getIdProduit() {
        return idProduit;
    }

    /**
     * Définit l'identifiant du produit.
     *
     * @param idProduit Identifiant unique du produit.
     */
    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    /**
     * Obtient le nom du produit.
     *
     * @return Le nom du produit.
     */
    public String getNomProduit() {
        return nomProduit;
    }

    /**
     * Définit le nom du produit.
     *
     * @param nomProduit Nom du produit.
     */
    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    /**
     * Obtient la catégorie du produit.
     *
     * @return La catégorie du produit.
     */
    public String getCategorieProduit() {
        return categorieProduit;
    }

    /**
     * Définit la catégorie du produit.
     *
     * @param categorieProduit Catégorie à laquelle appartient le produit.
     */
    public void setCategorieProduit(String categorieProduit) {
        this.categorieProduit = categorieProduit;
    }

    /**
     * Obtient le prix unitaire du produit.
     *
     * @return Le prix du produit.
     */
    public double getPrixProduit() {
        return prixProduit;
    }

    /**
     * Définit le prix du produit.
     *
     * @param prixProduit Prix unitaire du produit.
     */
    public void setPrixProduit(double prixProduit) {
        this.prixProduit = prixProduit;
    }

    /**
     * Obtient la quantité disponible du produit.
     *
     * @return La quantité en stock du produit.
     */
    public int getQuantiteProduit() {
        return quantiteProduit;
    }

    /**
     * Définit la quantité disponible du produit.
     *
     * @param quantiteProduit Quantité en stock du produit.
     */
    public void setQuantiteProduit(int quantiteProduit) {
        this.quantiteProduit = quantiteProduit;
    }

    /**
     * Retourne une représentation textuelle de l'objet Produit.
     *
     * @return Une chaîne de caractères représentant les propriétés du produit.
     */
    @Override
    public String toString() {
        return "Produit{" +
                "id=" + idProduit +
                ", nomProduit='" + nomProduit +
                ", categorieProduit='" + categorieProduit +
                ", prixProduit=" + prixProduit +
                ", quantitéProduit=" + quantiteProduit +
                '}';
    }
}
