package fr.univamu.fr.agricole;

/**
 * Classe représentant un panier
 */
public class Panier {

    /**
     * identifiant du panier
     */
    protected int IdPanier;

    /**
     * nombre de produit dans le panier
     */
    protected int nbProduit;

    /**
     * nom du produit
     */
    protected String nomProduit;

    /**
     * identifiant du utilisateur
     */
    protected int IDUtilisateur;

    /**
     * Constructeur par défaut
     */
    public Panier(){
    }

    /**
     * Constructeur du Panier
     * @param IdPanier identifiant du panier
     * @param nbProduit nombre de produit dans le panier
     * @param nomProduit nom du produit
     * @param IDUtilisateur identifiant de l'utilisateur
     */
    public Panier(int IdPanier, int nbProduit, String nomProduit, int IDUtilisateur){
        this.IdPanier = IdPanier;
        this.nbProduit = nbProduit;
        this.nomProduit = nomProduit;
        this.IDUtilisateur = IDUtilisateur;
    }

    /**
     * Méthode permettant d'accéder à l'identifiant du panier
     * @return un chaîne de caractères avec l'identifiant du panier
     */
    public int getIdPanier() {
        return IdPanier;
    }

    /**
     * Méthode permettant d'accéder au nombre de produit dans le panier
     * @return un chaîne de caractères avec le nombre de produit dans le panier
     */
    public int getNbProduit() {
        return nbProduit;
    }

    /**
     * Méthode permettant d'accéder au nom du produit
     * @return un chaîne de caractères avec le nom du produit
     */
    public String getNomProduit() {
        return nomProduit;
    }

    /**
     * Méthode permettant d'accéder à l'identifiant de l'utilisateur
     * @return un caractère indiquant l'id de l'utilisateur
     */
    public int getIDUtilisateur() {
        return IDUtilisateur;
    }

    /**
     * Méthode permettant de modifier l'identifiant du panier
     * @param IdPanier une chaîne de caractères avec l'identifiant à utiliser
     */
    public void IdPanier(int IdPanier) {
        this.IdPanier = IdPanier;
    }

    /**
     * Méthode permettant de modifier le nombre d'produit dans le panier
     * @param nbProduit une chaîne de caractères avec le nombre de produit
     */
    public void setNbProduit(int nbProduit) {
        this.nbProduit = nbProduit;
    }

    /**
     * Méthode permettant de modifier le nom du produit
     * @param nomProduit une chaîne de caractères avec le nom du produit
     */
    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    /**
     * Méthode permettant de modifier l'identifiant de l'utilisateur
     * @param IDUtilisateur le caractère à utiliser
     */
    public void setIDUtilisateur(int IDUtilisateur) {
        this.IDUtilisateur = IDUtilisateur;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "IdPanier='" + IdPanier + '\'' +
                ", nbProduit='" + nbProduit + '\'' +
                ", nomProduit='" + nomProduit + '\'' +
                ", IDUtilisateur=" + IDUtilisateur +
                '}';
    }
}
