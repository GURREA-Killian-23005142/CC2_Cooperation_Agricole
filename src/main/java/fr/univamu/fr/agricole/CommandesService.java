package fr.univamu.fr.agricole;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.util.ArrayList;

/**
 * Classe utilisée pour récupérer les informations nécessaires à la ressource
 * (permet de dissocier ressource et mode d'accès aux données)
 */
public class CommandesService {

    /**
     * Objet permettant d'accéder au dépôt où sont stockées les informations sur les commandes
     */
    protected CommandesRepositoryInterface commandesRepo;

    /**
     * Constructeur permettant d'injecter l'accès aux données
     * @param commandesRepo objet implémentant l'interface d'accès aux données
     */
    public CommandesService(CommandesRepositoryInterface commandesRepo) {
        this.commandesRepo = commandesRepo;
    }

    /**
     * Méthode retournant les informations sur les commandes au format JSON
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getAllCommandesJSON() {
        ArrayList<Commandes> allCommandes = commandesRepo.getAllCommandes();

        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allCommandes);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return result;
    }

    /**
     * Méthode retournant au format JSON les informations sur une commande recherchée
     * @param id la référence de la commande recherchée
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getCommandeJSON(int id) {
        String result = null;
        Commandes myCommande = commandesRepo.getCommande(id);

        if (myCommande != null) {
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myCommande);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    /**
     * Méthode permettant de mettre à jour les informations d'une commande
     * @param id référence de la commande à mettre à jour
     * @param commande les nouvelles informations à utiliser
     * @return true si la commande a pu être mise à jour
     */
    public boolean mettreAjourCommande(int id, Commandes commande) {
        return commandesRepo.mettreAjourCommande(id, commande.idUtilisateur, commande.IDPanier, commande.prixCommandes, commande.relais, commande.dateRetrait);
    }

    /**
     * Méthode permettant de suprimer une commande
     * @param id référence de la commande à supprimer
     * @return true si la commande a pu être suprimer
     */
    public boolean supprimerCommande(int id) {
        return commandesRepo.supprimerCommande(id);
    }

    /**
     * Méthode permettant d'ajouter une commande
     * @param commande la commande à ajouter
     * @return true si la commande a pu être ajoutée
     */
    public boolean ajouterCommande(Commandes commande) {
        return commandesRepo.ajouterCommande(commande);
    }

    /**
     * Méthode permettant de récupérer toutes les commandes d'un utilisateur
     * @param idUtilisateur l'identifiant de l'utilisateur
     * @return une liste de toutes les commandes de l'utilisateur
     */
    public String getAllCommandesByUtilisateurJSON(int idUtilisateur) {
        ArrayList<Commandes> allCommandes = commandesRepo.getAllCommandesByUtilisateur(idUtilisateur);

        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allCommandes);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return result;
    }
}
