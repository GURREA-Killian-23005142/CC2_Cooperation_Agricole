package fr.univamu.fr.agricole;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface CommandesRepositoryInterface {

    public void close();

    public boolean ajouterCommande(Commandes commande);

    public boolean supprimerCommande(int IDCommandes);

    public ArrayList<Commandes> getAllCommandes();

    public boolean mettreAjourCommande(int id, int idPanier, double prixCommandes, String relais, LocalDate dateRetrait);

    public Commandes getCommande(int IDCommandes);
}