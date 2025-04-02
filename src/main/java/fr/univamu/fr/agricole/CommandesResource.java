package fr.univamu.fr.agricole;

import fr.univamu.fr.agricole.CommandesDAO;
import fr.univamu.fr.agricole.Commandes;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/commandes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommandesResource {
    private CommandesDAO commandesDAO = new CommandesDAO();

    @GET
    public List<Commandes> getCommandes() {
        return commandesDAO.getAllCommandes();
    }

    @POST
    public Response ajouterCommande(Commandes commande) {
        commandesDAO.ajouterCommande(commande);
        return Response.status(Response.Status.CREATED).entity(commande).build();
    }

    @DELETE
    @Path("/{IDCommandes}")
    public void supprimerCommande(@PathParam("IDCommandes") int IDCommandes) {
        commandesDAO.supprimerCommande(IDCommandes);
    }
}
