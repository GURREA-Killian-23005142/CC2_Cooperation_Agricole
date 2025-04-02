package fr.univamu.fr.agricole;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/produits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProduitRessource {
    @Inject
    private ProduitService produitService;

    @GET
    public List<Produit> getProduits() {
        return produitService.getTousProduits();
    }
}