package fr.univamu.fr.agricole;

import java.util.ArrayList;

public interface ProduitInterface {

    public void close();

    public Produit getProduit(int id );

    public ArrayList<Produit> getAllProduits() ;

    public boolean updateProduit(int IDProduit, String nomProduit, String categorieProduit, double prixProduit, int quantiteProduit);

    public boolean addProduit(Produit produit);

    public boolean deleteProduit(int id);
}
