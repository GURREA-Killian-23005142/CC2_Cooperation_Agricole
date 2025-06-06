package fr.univamu.fr.agricole;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.util.ArrayList;

/**
 * Classe utilisée pour récupérer les informations nécessaires à la ressource
 * (permet de dissocier ressource et mode d'accès aux données)
 */
public class PanierService {

    /**
     * Objet permettant d'accéder au dépôt où sont stockées les informations sur les paniers
     */
    protected PanierRepositoryInterface PanierRepo;

    /**
     * Constructeur permettant d'injecter l'accès aux données
     * @param PanierRepo objet implémentant l'interface d'accès aux données
     */
    public PanierService(PanierRepositoryInterface PanierRepo) {
        this.PanierRepo = PanierRepo;
    }

    /**
     * Méthode retournant les informations sur les paniers au format JSON
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getAllPanierJSON() {
        ArrayList<Panier> allPaniers = PanierRepo.getAllPanier();

        // création du json et conversion de la liste de paniers
        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allPaniers);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return result;
    }

    /**
     * Méthode retournant au format JSON les informations sur un panier recherché
     * @param idPanier l'identifiant du panier recherché
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getPanierJSON(int idPanier) {
        String result = null;
        Panier myPanier = PanierRepo.getPanier(idPanier);

        // si le panier a été trouvé
        if (myPanier != null) {
            // création du json et conversion du panier
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myPanier);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    /**
     * Méthode permettant de mettre à jours les informations d'un panier
     * @param idPanier l'identifiant du panier à mettre à jours
     * @param panier les nouvelles infromations a été utiliser
     * @return true si le panier a pu être mis à jours
     */
    public boolean updatePanier(int idPanier, Panier panier) {
        return PanierRepo.UpdatePanier(idPanier, panier.nbProduit, panier.nomProduit, panier.IdPanier);
    }
}