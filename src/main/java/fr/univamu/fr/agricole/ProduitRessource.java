package fr.univamu.fr.agricole;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * Ressource REST pour la gestion des produits.
 * Fournit les endpoints pour effectuer des opérations CRUD sur les produits.
 */
@Path("/Produits")
@ApplicationScoped
public class ProduitRessource {

    private ProduitService service;

    /**
     * Constructeur par défaut.
     */
    public ProduitRessource() {}

    /**
     * Constructeur avec injection d'une interface ProduitInterface pour initialiser le service.
     *
     * @param produitRepo Instance de ProduitInterface pour interagir avec les données des produits.
     */
    public @Inject ProduitRessource(ProduitInterface produitRepo) {
        this.service = new ProduitService(produitRepo);
    }

    /**
     * Récupère tous les produits en format JSON.
     *
     * @return Une chaîne JSON contenant tous les produits.
     */
    @GET
    @Produces("application/json")
    public String getAllProduit() {
        return service.getAllProduitJSON();
    }

    /**
     * Récupère un produit spécifique par son identifiant en format JSON.
     *
     * @param id Identifiant unique du produit.
     * @return Une chaîne JSON représentant le produit.
     * @throws NotFoundException Si le produit n'est pas trouvé.
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getProduit(@PathParam("id") int id) {
        String result = service.getProduitJSON(id);

        if (result == null) {
            throw new NotFoundException();
        }

        return result;
    }

    /**
     * Met à jour un produit existant.
     *
     * @param id Identifiant unique du produit à mettre à jour.
     * @param produit Objet Produit contenant les nouvelles informations.
     * @return Une réponse HTTP avec le statut de l'opération.
     * @throws NotFoundException Si le produit à mettre à jour n'est pas trouvé.
     */
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response updateProduit(@PathParam("id") int id, Produit produit) {
        if (!service.updateProduit(id, produit)) {
            throw new NotFoundException();
        } else {
            return Response.ok("updated").build();
        }
    }

    /**
     * Ajoute un nouveau produit.
     *
     * @param produit Objet Produit contenant les informations du produit à ajouter.
     * @return Une réponse HTTP avec le statut de l'opération.
     */
    @POST
    @Consumes("application/json")
    public Response addProduit(Produit produit) {
        if (service.addProduit(produit)) {
            return Response.status(Response.Status.CREATED).entity("Produit ajouté").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Erreur lors de l'ajout").build();
        }
    }

    /**
     * Supprime un produit par son identifiant.
     *
     * @param id Identifiant unique du produit à supprimer.
     * @return Une réponse HTTP avec le statut de l'opération.
     * @throws NotFoundException Si le produit à supprimer n'est pas trouvé.
     */
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