package fr.univamu.fr.agricole;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;

@Stateless
public class ProduitService {
    @Inject
    private ProduitBD produitBD;

    public List<Produit> getTousProduits() {
        return produitBD.getProduits();
    }
}