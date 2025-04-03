package fr.univamu.fr.agricole;

import java.util.ArrayList;

/**
 * Interface UtilisateurInterface définissant les opérations CRUD
 * pour la gestion des utilisateurs.
 */
public interface UtilisateurInterface {

    /**
     * Ferme les ressources ou connexions associées.
     */
    public void close();

    /**
     * Récupère un utilisateur spécifique par son identifiant.
     *
     * @param id Identifiant unique de l'utilisateur.
     * @return L'utilisateur correspondant ou null s'il n'est pas trouvé.
     */
    public Utilisateur getUtilisateur(int id);

    /**
     * Récupère tous les utilisateurs disponibles.
     *
     * @return Une liste contenant tous les utilisateurs.
     */
    public ArrayList<Utilisateur> getAllUtilisateur();

    /**
     * Met à jour les informations d'un utilisateur existant.
     *
     * @param idUtilisateur Identifiant unique de l'utilisateur à mettre à jour.
     * @param nomUtilisateur Nouveau nom de l'utilisateur.
     * @param prenomUtilisateur Nouveau prénom de l'utilisateur.
     * @param emailUtilisateur Nouvelle adresse email de l'utilisateur.
     * @param passwordUtilisateur Nouveau mot de passe de l'utilisateur.
     * @param roleUtilisateur Nouveau rôle attribué à l'utilisateur.
     * @return true si la mise à jour a réussi, false sinon.
     */
    public boolean updateUtilisateur(int idUtilisateur, String nomUtilisateur, String prenomUtilisateur, String emailUtilisateur, String passwordUtilisateur, String roleUtilisateur);

    /**
     * Ajoute un nouvel utilisateur.
     *
     * @param utilisateur Objet Utilisateur contenant les informations de l'utilisateur à ajouter.
     * @return true si l'ajout a réussi, false sinon.
     */
    public boolean addUtilisateur(Utilisateur utilisateur);

    /**
     * Supprime un utilisateur spécifique par son identifiant.
     *
     * @param id Identifiant unique de l'utilisateur à supprimer.
     * @return true si la suppression a réussi, false sinon.
     */
    public boolean deleteUtilisateur(int id);
}
