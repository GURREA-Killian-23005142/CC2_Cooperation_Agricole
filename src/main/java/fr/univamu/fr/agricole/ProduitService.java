package fr.univamu.fr.agricole;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.util.ArrayList;

@ApplicationScoped
public class ProduitService {

    protected ProduitInterface produitRepo ;

    @Inject
    public ProduitService( ProduitInterface produitRepo) {
        this.produitRepo = produitRepo;
    }


    public String getAllProduitJSON(){

        ArrayList<Produit> allProduits = produitRepo.getAllProduits();

        // création du json et conversion de la liste de livres
        String result = null;
        try(Jsonb jsonb = JsonbBuilder.create()){
            result = jsonb.toJson(allProduits);
        }
        catch (Exception e){
            System.err.println( e.getMessage() );
        }

        return result;
    }


    public String getProduitJSON( int id ){
        String result = null;
        Produit myProduit = produitRepo.getProduit(id);

        // si le livre a été trouvé
        if( myProduit != null ) {

            // création du json et conversion du livre
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myProduit);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }


    public boolean updateProduit(int id, Produit Produit) {
        return produitRepo.updateProduit(id, Produit.getNomProduit(), Produit.getCategorieProduit(), Produit.getPrixProduit(), Produit.getQuantiteProduit());
    }

    public boolean addProduit(Produit produit) {
        return produitRepo.addProduit(produit);
    }

    public boolean deleteProduit(int id) {
        return produitRepo.deleteProduit(id);
    }
}