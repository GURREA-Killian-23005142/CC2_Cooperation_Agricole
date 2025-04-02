package fr.univamu.fr.agricole;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.List;

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
        List<Commandes> allCommandes = commandesRepo.getAllCommandes();

        // création du json et conversion de la liste de commandes
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

        // si la commande a été trouvée
        if (myCommande != null) {
            // création du json et conversion de la commande
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
        return commandesRepo.mettreAjourCommande(id, commande.IDPanier, commande.prixCommandes, commande.relais, commande.dateRetrait);
    }

    public boolean supprimerCommande(int id) {
        return commandesRepo.supprimerCommande(id);
    }

    public boolean ajouterCommande(Commandes commande) {
        return commandesRepo.ajouterCommande(commande);
    }
}