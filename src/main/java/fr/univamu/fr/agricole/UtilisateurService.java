package fr.univamu.fr.agricole;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.util.ArrayList;

/**
 * Service pour gérer les opérations liées aux utilisateurs.
 * Cette classe agit comme une couche intermédiaire entre la ressource REST
 * et la logique métier.
 */
@ApplicationScoped
public class UtilisateurService {

    protected UtilisateurInterface UtilisateurRepo;

    /**
     * Constructeur avec injection d'un dépôt d'utilisateurs.
     *
     * @param UtilisateurRepo Interface UtilisateurInterface utilisée pour interagir avec les données des utilisateurs.
     */
    @Inject
    public UtilisateurService(UtilisateurInterface UtilisateurRepo) {
        this.UtilisateurRepo = UtilisateurRepo;
    }

    /**
     * Constructeur par défaut.
     */
    public UtilisateurService() {}

    /**
     * Récupère tous les utilisateurs sous forme de JSON.
     *
     * @return Une chaîne JSON représentant la liste de tous les utilisateurs,
     * ou null en cas d'erreur lors de la conversion.
     */
    public String getAllUtilisateurJSON() {
        ArrayList<Utilisateur> allUtilisateurs = UtilisateurRepo.getAllUtilisateur();
        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allUtilisateurs);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    /**
     * Récupère un utilisateur spécifique par son identifiant sous forme de JSON.
     *
     * @param id Identifiant unique de l'utilisateur.
     * @return Une chaîne JSON représentant l'utilisateur, ou null si l'utilisateur n'existe pas ou en cas d'erreur.
     */
    public String getUtilisateurJSON(int id) {
        String result = null;
        Utilisateur myUtilisateur = UtilisateurRepo.getUtilisateur(id);
        if (myUtilisateur != null) {
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myUtilisateur);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    /**
     * Met à jour un utilisateur existant.
     *
     * @param id Identifiant unique de l'utilisateur à mettre à jour.
     * @param Utilisateur Objet Utilisateur contenant les nouvelles informations.
     * @return true si la mise à jour a réussi, false sinon.
     */
    public boolean updateUtilisateur(int id, Utilisateur Utilisateur) {
        return UtilisateurRepo.updateUtilisateur(id, Utilisateur.getNomUtilisateur(), Utilisateur.getPrenomUtilisateur(), Utilisateur.getEmailUtilisateur(), Utilisateur.getPasswordUtilisateur(), Utilisateur.getRoleUtilisateur());
    }

    /**
     * Ajoute un nouvel utilisateur.
     *
     * @param Utilisateur Objet Utilisateur contenant les informations de l'utilisateur à ajouter.
     * @return true si l'ajout a réussi, false sinon.
     */
    public boolean addUtilisateur(Utilisateur Utilisateur) {
        return UtilisateurRepo.addUtilisateur(Utilisateur);
    }

    /**
     * Supprime un utilisateur par son identifiant.
     *
     * @param id Identifiant unique de l'utilisateur à supprimer.
     * @return true si la suppression a réussi, false sinon.
     */
    public boolean deleteUtilisateur(int id) {
        return UtilisateurRepo.deleteUtilisateur(id);
    }
}