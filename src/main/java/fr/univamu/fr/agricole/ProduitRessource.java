package fr.univamu.fr.agricole;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/Produits")
@ApplicationScoped
public class ProduitRessource {

    private ProduitService service;

    public ProduitRessource(){}

    public @Inject ProduitRessource(ProduitInterface produitRepo) {
        this.service = new ProduitService( produitRepo);
    }

    @GET
    @Produces("application/json")
    public String getAllProduit() {
        return service.getAllProduitJSON();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getProduit( @PathParam("id") int id){

        String result = service.getProduitJSON(id);

        // si le produit n'a pas été trouvé
        if( result == null )
            throw new NotFoundException();

        return result;
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response updateProduit(@PathParam("id") int id, Produit produit ){

        // si le produit n'a pas été trouvé
        if( ! service.updateProduit(id, produit) )
            throw new NotFoundException();
        else
            return Response.ok("updated").build();
    }

    @POST
    @Consumes("application/json")
    public Response addProduit(Produit produit) {
        if (service.addProduit(produit)) {
            return Response.status(Response.Status.CREATED).entity("Produit ajouté").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Erreur lors de l'ajout").build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteProduit(@PathParam("id") int id) {
        if (!service.deleteProduit(id)) {
            throw new NotFoundException();
        } else {
            return Response.ok("Produit supprimé").build();
        }
    }

}