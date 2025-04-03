package fr.univamu.fr.agricole;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.util.ArrayList;

/**
 * Service pour gérer les opérations liées aux produits.
 * Cette classe agit comme une couche intermédiaire entre la ressource REST
 * et la logique métier.
 */
@ApplicationScoped
public class ProduitService {

    protected ProduitInterface produitRepo;

    /**
     * Constructeur avec injection d'un dépôt de produits.
     *
     * @param produitRepo Interface ProduitInterface utilisée pour interagir avec les données des produits.
     */
    @Inject
    public ProduitService(ProduitInterface produitRepo) {
        this.produitRepo = produitRepo;
    }

    /**
     * Récupère tous les produits sous forme de JSON.
     *
     * @return Une chaîne JSON représentant la liste de tous les produits,
     * ou null en cas d'erreur lors de la conversion.
     */
    public String getAllProduitJSON() {
        ArrayList<Produit> allProduits = produitRepo.getAllProduits();
        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allProduits);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    /**
     * Récupère un produit spécifique par son identifiant sous forme de JSON.
     *
     * @param id Identifiant unique du produit.
     * @return Une chaîne JSON représentant le produit, ou null si le produit n'existe pas ou en cas d'erreur.
     */
    public String getProduitJSON(int id) {
        String result = null;
        Produit myProduit = produitRepo.getProduit(id);
        if (myProduit != null) {
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myProduit);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    /**
     * Met à jour un produit existant.
     *
     * @param id Identifiant unique du produit à mettre à jour.
     * @param produit Objet Produit contenant les nouvelles informations.
     * @return true si la mise à jour a réussi, false sinon.
     */
    public boolean updateProduit(int id, Produit produit) {
        return produitRepo.updateProduit(id, produit.getNomProduit(), produit.getCategorieProduit(), produit.getPrixProduit(), produit.getQuantiteProduit());
    }

    /**
     * Ajoute un nouveau produit.
     *
     * @param produit Objet Produit contenant les informations du produit à ajouter.
     * @return true si l'ajout a réussi, false sinon.
     */
    public boolean addProduit(Produit produit) {
        return produitRepo.addProduit(produit);
    }

    /**
     * Supprime un produit par son identifiant.
     *
     * @param id Identifiant unique du produit à supprimer.
     * @return true si la suppression a réussi, false sinon.
     */
    public boolean deleteProduit(int id) {
        return produitRepo.deleteProduit(id);
    }
}