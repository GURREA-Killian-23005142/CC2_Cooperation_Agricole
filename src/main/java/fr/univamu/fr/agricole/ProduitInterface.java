package fr.univamu.fr.agricole;

import java.util.ArrayList;

/**
 * Interface ProduitInterface définissant les opérations CRUD
 * pour la gestion des produits.
 */
public interface ProduitInterface {

    /**
     * Ferme les ressources ou connexions associées.
     */
    public void close();

    /**
     * Récupère un produit spécifique par son identifiant.
     *
     * @param id Identifiant unique du produit.
     * @return Le produit correspondant ou null s'il n'est pas trouvé.
     */
    public Produit getProduit(int id);

    /**
     * Récupère tous les produits disponibles.
     *
     * @return Une liste contenant tous les produits.
     */
    public ArrayList<Produit> getAllProduits();

    /**
     * Met à jour les informations d'un produit existant.
     *
     * @param IDProduit Identifiant unique du produit à mettre à jour.
     * @param nomProduit Nouveau nom du produit.
     * @param categorieProduit Nouvelle catégorie du produit.
     * @param prixProduit Nouveau prix du produit.
     * @param quantiteProduit Nouvelle quantité disponible pour le produit.
     * @return true si la mise à jour a réussi, false sinon.
     */
    public boolean updateProduit(int IDProduit, String nomProduit, String categorieProduit, double prixProduit, int quantiteProduit);

    /**
     * Ajoute un nouveau produit.
     *
     * @param produit Objet Produit contenant les informations du produit à ajouter.
     * @return true si l'ajout a réussi, false sinon.
     */
    public boolean addProduit(Produit produit);

    /**
     * Supprime un produit spécifique par son identifiant.
     *
     * @param id Identifiant unique du produit à supprimer.
     * @return true si la suppression a réussi, false sinon.
     */
    public boolean deleteProduit(int id);
}
