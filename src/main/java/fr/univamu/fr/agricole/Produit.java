package fr.univamu.fr.agricole;

public class Produit {

    private int idProduit;
    private String nomProduit;
    private String categorieProduit;
    private double prixProduit;
    private int quantiteProduit;

    public Produit(int idProduit, String nomProduit, String categorieProduit, double prixProduit, int quantiteProduit) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.categorieProduit = categorieProduit;
        this.prixProduit = prixProduit;
        this.quantiteProduit = quantiteProduit;
    }

    public Produit() {

    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getCategorieProduit() {
        return categorieProduit;
    }

    public void setCategorieProduit(String categorieProduit) {
        this.categorieProduit = categorieProduit;
    }

    public double getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(double prixProduit) {
        this.prixProduit = prixProduit;
    }

    public int getQuantiteProduit() {
        return quantiteProduit;
    }

    public void setQuantiteProduit(int quantiteProduit) {
        this.quantiteProduit = quantiteProduit;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + idProduit +
                ", nomProduit='" + nomProduit +
                ", categorieProduit='" + categorieProduit +
                ", prixProduit=" + prixProduit +
                ", quantitéProduit=" + quantiteProduit +
                '}';
    }
}