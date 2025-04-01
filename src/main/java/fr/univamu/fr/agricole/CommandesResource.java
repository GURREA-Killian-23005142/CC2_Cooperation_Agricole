package fr.univamu.fr.agricole;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/commandes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommandesResource {

    @Inject
    private CommandesDAO CommandesDAO;

    @POST
    public Response ajouterCommande(Commandes Commandes) {
        CommandesDAO.ajouterCommande(Commandes);
        return Response.status(Response.Status.CREATED).entity(Commandes).build();
    }

    @GET
    public List<Commandes> listerCommandes() {
        return CommandesDAO.listerCommandes();
    }

    @GET
    @Path("/{id}")
    public Response obtenirCommande(@PathParam("id") Long id) {
        Commandes Commandes = CommandesDAO.trouverCommandes(id);
        if (Commandes == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(Commandes).build();
    }

    @DELETE
    @Path("/{id}")
    public Response supprimerCommande(@PathParam("id") Long id) {
        CommandesDAO.supprimerCommandes(id);
        return Response.noContent().build();
    }
}
