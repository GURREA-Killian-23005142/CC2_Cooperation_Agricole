package fr.univamu.fr.agricole;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
/**
 * Ressource REST pour gérer les commandes.
 */
@Path("/commandes")
@ApplicationScoped
public class CommandesResource {

    /**
     * Service de gestion des commandes.
     */
    private CommandesService commandesService;

    /**
     * Constructeur par défaut.
     */
    public CommandesResource() {}

    /**
     * Constructeur avec injection de dépendance.
     *
     * @param commandesRepositoryInterface Interface du dépôt des commandes.
     */
    public @Inject CommandesResource(CommandesRepositoryInterface commandesRepositoryInterface) {
        this.commandesService = new CommandesService(commandesRepositoryInterface);
    }

    /**
     * Récupère toutes les commandes au format JSON.
     *
     * @return Liste des commandes sous forme JSON.
     */
    @GET
    @Produces("application/json")
    public String getAllCommandes() {
        return commandesService.getAllCommandesJSON();
    }

    /**
     * Récupère une commande spécifique en fonction de son ID.
     *
     * @param IDCommandes Identifiant unique de la commande.
     * @return La commande au format JSON.
     * @throws NotFoundException Si la commande n'est pas trouvée.
     */
    @GET
    @Path("{IDCommandes}")
    @Produces("application/json")
    public String getCommandes(@PathParam("IDCommandes") int IDCommandes) {
        String result = commandesService.getCommandeJSON(IDCommandes);
        if (result == null)
            throw new NotFoundException();
        return result;
    }

    /**
     * Ajoute une nouvelle commande.
     *
     * @param commande Commande à ajouter.
     * @return Réponse HTTP indiquant le succès ou l'échec de l'opération.
     * @throws BadRequestException Si la requête est invalide.
     */
    @POST
    @Produces("application/json")
    public Response ajouterCommande(Commandes commande) {
        if (!commandesService.ajouterCommande(commande)) {
            throw new BadRequestException();
        } else {
            return Response.status(Response.Status.CREATED).entity(commande).build();
        }
    }

    /**
     * Supprime une commande en fonction de son ID.
     *
     * @param IDCommandes Identifiant unique de la commande.
     * @return Réponse HTTP indiquant le succès ou l'échec de l'opération.
     * @throws NotFoundException Si la commande n'est pas trouvée.
     */
    @DELETE
    @Path("/{IDCommandes}")
    @Produces("application/json")
    public Response supprimerCommande(@PathParam("IDCommandes") int IDCommandes) {
        if (!commandesService.supprimerCommande(IDCommandes)) {
            throw new NotFoundException();
        } else {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    /**
     * Met à jour une commande en fonction de son ID.
     *
     * @param IDCommandes Identifiant unique de la commande à mettre à jour.
     * @param commande Nouvelle version de la commande.
     * @return Réponse HTTP indiquant le succès ou l'échec de l'opération.
     * @throws NotFoundException Si la commande n'est pas trouvée.
     */
    @PUT
    @Path("/{IDCommandes}")
    @Produces("application/json")
    public Response mettreAjourCommande(@PathParam("IDCommandes") int IDCommandes, Commandes commande) {
        if (!commandesService.mettreAjourCommande(IDCommandes, commande)) {
            throw new NotFoundException();
        } else {
            return Response.ok(commande).build();
        }
    }
}