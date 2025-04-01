package fr.univamu.fr.agricole;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

public class ProduitBD {
    @PersistenceContext
    private EntityManager em;

    public void ajouterProduit(Produit produit) {
        em.persist(produit);
    }

    public List<Produit> getProduits() {
        return em.createQuery("SELECT p FROM Produit p", Produit.class).getResultList();
    }
}
