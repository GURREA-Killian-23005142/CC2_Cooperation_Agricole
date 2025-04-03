package fr.univamu.fr.agricole;

import java.util.ArrayList;

/**
 * Interface d'accès aux données des paniers
 */
public interface PanierRepositoryInterface {

    /**
     *  Méthode fermant le dépôt où sont stockées les informations sur les paniers
     */
    public void close();

    /**
     * Méthode retournant le panier dont la référence est passée en paramètre
     * @param IdPanier identifiant du panier recherché
     * @return un objet Panier représentant le panier  recherché
     */
    public Panier getPanier(int IdPanier );

    /**
     * Méthode retournant la liste des paniers
     * @return une liste d'objets paniers
     */
    public ArrayList<Panier> getAllPanier() ;

    /**
     * Méthode permettant de mettre à jours un panier enregistré
     * @param IdPanier identifiant du panier à mettre à jours
     * @param nbProduit nouveau nombre du panier
     * @param nomProduit nouveau nom du produit
     * @param IDUtilisateur nouveau identifiant de l'utilisateur
     * @return true si le panier existe et la mise à jours a été faite, false sinon
     */
    public boolean UpdatePanier( int IdPanier, int nbProduit, String nomProduit, int IDUtilisateur);

    /**
     * Méthode permettant de créer un panier
     * @param panier le panier à créer
     * @return true si le panier a été créé, false sinon
     */
    boolean createPanier(Panier panier);

    /**
     * Méthode permettant de supprimer un panier
     * @param idPanier l'identifiant du panier à supprimer
     * @return true si le panier a été supprimé, false sinon
     */
    boolean deletePanier(int idPanier);


}

