package fr.univamu.fr.agricole;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/Utilisateurs")
@ApplicationScoped
public class UtilisateurRessource {
    private UtilisateurService service;

    public UtilisateurRessource(){}

    public @Inject UtilisateurRessource(UtilisateurInterface utilisateurRepo) {
        this.service = new UtilisateurService( utilisateurRepo);
    }

    @GET
    @Produces("application/json")
    public String getAllUtilisateur() {
        return service.getAllUtilisateurJSON();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getUtilisateur( @PathParam("id") int id){

        String result = service.getUtilisateurJSON(id);

        // si le Utilisateur n'a pas été trouvé
        if( result == null )
            throw new NotFoundException();

        return result;
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response updateUtilisateur(@PathParam("id") int id, Utilisateur utilisateur ){

        // si le Utilisateur n'a pas été trouvé
        if( ! service.updateUtilisateur(id, utilisateur) )
            throw new NotFoundException();
        else
            return Response.ok("updated").build();
    }

    @POST
    @Consumes("application/json")
    public Response addUtilisateur(Utilisateur utilisateur) {
        if (service.addUtilisateur(utilisateur)) {
            return Response.status(Response.Status.CREATED).entity("Utilisateur ajouté").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Erreur lors de l'ajout").build();
        }
    }

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
