package fr.univamu.fr.agricole;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/commandes")
@ApplicationScoped
public class CommandesResource {
    private CommandesService commandesService;

    public CommandesResource() {}

    public @Inject CommandesResource(CommandesRepositoryInterface commandesRepositoryInterface) {this.commandesService = new CommandesService(commandesRepositoryInterface);}

    public CommandesResource(CommandesService commandesService) { this.commandesService = commandesService;}

    @GET
    @Produces("application/json")
    public String getAllCommandes() {
        return commandesService.getAllCommandesJSON();
    }

    @GET
    @Path("{IDCommandes}")
    @Produces("application/json")
    public String getCommandes(@PathParam("IDCommandes") int IDCommandes) {
        String result = commandesService.getCommandeJSON(IDCommandes);
        if (result == null)
            throw new NotFoundException();

        return result;
    }

    @POST
    @Produces("application/json")
    public Response ajouterCommande(Commandes commande) {
        if(!commandesService.ajouterCommande(commande)) {
            throw new BadRequestException();
        }
        else {
            return Response.status(Response.Status.CREATED).entity(commande).build();
        }
    }

    @DELETE
    @Path("/{IDCommandes}")
    @Produces("application/json")
    public Response supprimerCommande(@PathParam("IDCommandes") int IDCommandes) {
        if(!commandesService.supprimerCommande(IDCommandes)) {
            throw new NotFoundException();
        } else {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @PUT
    @Path("/{IDCommandes}")
    @Produces("application/json")
    public Response mettreAjourCommande(@PathParam("IDCommandes") int IDCommandes, Commandes commande) {
        if(!commandesService.mettreAjourCommande(IDCommandes, commande)) {
            throw new NotFoundException();
        } else {
            return Response.ok(commande).build();
        }
    }
}
