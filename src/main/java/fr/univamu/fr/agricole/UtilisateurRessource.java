package fr.univamu.fr.agricole;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * Ressource REST pour la gestion des utilisateurs.
 * Fournit les endpoints pour effectuer des opérations CRUD sur les utilisateurs.
 */
@Path("/Utilisateurs")
@ApplicationScoped
public class UtilisateurRessource {

    private UtilisateurService service;

    /**
     * Constructeur par défaut.
     */
    public UtilisateurRessource() {}

    /**
     * Constructeur avec injection d'une interface UtilisateurInterface pour initialiser le service.
     *
     * @param utilisateurRepo Instance de UtilisateurInterface pour interagir avec les données des utilisateurs.
     */
    public @Inject UtilisateurRessource(UtilisateurInterface utilisateurRepo) {
        this.service = new UtilisateurService(utilisateurRepo);
    }

    /**
     * Récupère tous les utilisateurs en format JSON.
     *
     * @return Une chaîne JSON contenant tous les utilisateurs.
     */
    @GET
    @Produces("application/json")
    public String getAllUtilisateur() {
        return service.getAllUtilisateurJSON();
    }

    /**
     * Récupère un utilisateur spécifique par son identifiant en format JSON.
     *
     * @param id Identifiant unique de l'utilisateur.
     * @return Une chaîne JSON représentant l'utilisateur.
     * @throws NotFoundException Si l'utilisateur n'est pas trouvé.
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getUtilisateur(@PathParam("id") int id) {
        String result = service.getUtilisateurJSON(id);

        if (result == null) {
            throw new NotFoundException();
        }

        return result;
    }

    /**
     * Met à jour un utilisateur existant.
     *
     * @param id Identifiant unique de l'utilisateur à mettre à jour.
     * @param utilisateur Objet Utilisateur contenant les nouvelles informations.
     * @return Une réponse HTTP avec le statut de l'opération.
     * @throws NotFoundException Si l'utilisateur à mettre à jour n'est pas trouvé.
     */
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response updateUtilisateur(@PathParam("id") int id, Utilisateur utilisateur) {
        if (!service.updateUtilisateur(id, utilisateur)) {
            throw new NotFoundException();
        } else {
            return Response.ok("updated").build();
        }
    }

    /**
     * Ajoute un nouvel utilisateur.
     *
     * @param utilisateur Objet Utilisateur contenant les informations de l'utilisateur à ajouter.
     * @return Une réponse HTTP avec le statut de l'opération.
     */
    @POST
    @Consumes("application/json")
    public Response addUtilisateur(Utilisateur utilisateur) {
        if (service.addUtilisateur(utilisateur)) {
            return Response.status(Response.Status.CREATED).entity("Utilisateur ajouté").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Erreur lors de l'ajout").build();
        }
    }

    /**
     * Supprime un utilisateur par son identifiant.
     *
     * @param id Identifiant unique de l'utilisateur à supprimer.
     * @return Une réponse HTTP avec le statut de l'opération.
     * @throws NotFoundException Si l'utilisateur à supprimer n'est pas trouvé.
     */
    @DELETE
    @Path("{id}")
    public Response deleteUtilisateur(@PathParam("id") int id) {
        if (!service.deleteUtilisateur(id)) {
            throw new NotFoundException();
        } else {
            return Response.ok("Utilisateur supprimé").build();
        }
    }
}
