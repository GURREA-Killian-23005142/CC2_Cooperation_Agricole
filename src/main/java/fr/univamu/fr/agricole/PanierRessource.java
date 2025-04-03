package fr.univamu.fr.agricole;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/Panier")
@ApplicationScoped
public class PanierRessource {

    private PanierService service;

    public PanierRessource() {}

    @Inject
    public PanierRessource(PanierRepositoryInterface panierRepo) {
        this.service = new PanierService(panierRepo);
    }

    public PanierRessource(PanierService service) {
        this.service = service;
    }

    @GET
    @Produces("application/json")
    public String getAllPanier() {
        return service.getAllPanierJSON();
    }

    @GET
    @Path("{IdPanier}")
    @Produces("application/json")
    public String getPanier(@PathParam("IdPanier") int IdPanier) {
        String result = service.getPanierJSON(IdPanier);
        if (result == null) {
            throw new NotFoundException();
        }
        return result;
    }

    @PUT
    @Path("{IdPanier}")
    @Consumes("application/json")
    public Response updatepanier(@PathParam("IdPanier") int IdPanier, Panier panier) {
        if (!service.updatePanier(IdPanier, panier)) {
            throw new NotFoundException();
        } else {
            return Response.ok("updated").build();
        }
    }
}