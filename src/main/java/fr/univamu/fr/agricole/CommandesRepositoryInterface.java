package fr.univamu.fr.agricole;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Interface définissant les opérations pour la gestion des commandes.
 */
public interface CommandesRepositoryInterface {

    /**
     * Ferme la connexion avec la base de données.
     */
    public void close();

    /**
     * Ajoute une nouvelle commande à la base de données.
     *
     * @param commande La commande à ajouter.
     * @return {@code true} si l'ajout a réussi, {@code false} sinon.
     */
    public boolean ajouterCommande(Commandes commande);

    /**
     * Supprime une commande de la base de données en fonction de son ID.
     *
     * @param IDCommandes Identifiant unique de la commande à supprimer.
     * @return {@code true} si la suppression a réussi, {@code false} sinon.
     */
    public boolean supprimerCommande(int IDCommandes);

    /**
     * Récupère toutes les commandes de la base de données.
     *
     * @return Une liste de toutes les commandes enregistrées.
     */
    public ArrayList<Commandes> getAllCommandes();

    /**
     * Met à jour une commande existante dans la base de données.
     *
     * @param id Identifiant unique de la commande à mettre à jour.
     * @param idPanier Identifiant du panier associé.
     * @param prixCommandes Prix de la commande.
     * @param relais Point relais pour le retrait de la commande.
     * @param dateRetrait Date prévue pour le retrait de la commande.
     * @return {@code true} si la mise à jour a réussi, {@code false} sinon.
     */
    public boolean mettreAjourCommande(int id,int idUtilisateur, int idPanier, double prixCommandes, String relais, LocalDate dateRetrait);

    /**
     * Récupère une commande spécifique à partir de son ID.
     *
     * @param IDCommandes Identifiant unique de la commande.
     * @return La commande correspondante, ou {@code null} si elle n'existe pas.
     */
    public Commandes getCommande(int IDCommandes);

    /**
     * Récupère toutes les commandes d'un utilisateur spécifique.
     *
     * @param idUtilisateur Identifiant unique de l'utilisateur.
     * @return Une liste de toutes les commandes de l'utilisateur.
     */
    public ArrayList<Commandes> getAllCommandesByUtilisateur(int idUtilisateur);
}
