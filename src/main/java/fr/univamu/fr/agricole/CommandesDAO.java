package fr.univamu.fr.agricole;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@Transactional
public class CommandesDAO {

    @PersistenceContext
    private EntityManager em;

    public void ajouterCommande(Commandes Commandes) {
        em.persist(Commandes);
    }

    public Commandes trouverCommandes(int id) {
        return em.find(Commandes.class, id);
    }

    public List<Commandes> listerCommandes() {
        return em.createQuery("SELECT c FROM Commandes c", Commandes.class).getResultList();
    }

    public void supprimerCommandes(int id) {
        Commandes c = em.find(Commandes.class, id);
        if (c != null) em.remove(c);
    }
}
