package fr.univamu.fr.agricole;
import jakarta.inject.Inject;
import jakarta.ws.rs.ApplicationPath;


import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;


public class BDAccess implements ProduitInterface, Closeable {

    protected Connection dbConnection;

    public  @Inject BDAccess () {}

    public BDAccess(String infoConnection, String user, String pwd) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection(infoConnection, user, pwd);
    }

    @Override
    public void close() {
        try {
            dbConnection.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Produit getProduit(int id) {

        Produit selectedProduit = null;

        String query = "SELECT * FROM Produits WHERE IDProduit = ?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id); // Correction : utiliser setInt pour un id de type int

            ResultSet result = ps.executeQuery();

            if (result.next()) {
                String nom = result.getString("nomProduit");
                String categorie = result.getString("categorieProduit");
                double prix = result.getDouble("prixProduit");
                int quantite = result.getInt("quantiteProduit");

                // Création et initialisation de l'objet Produit
                selectedProduit = new Produit(id, nom, categorie, prix, quantite);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedProduit;
    }

    @Override
    public ArrayList<Produit> getAllProduits() {
        ArrayList<Produit> listProduits = new ArrayList<>();

        String query = "SELECT * FROM Produits";



        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ResultSet result = ps.executeQuery();

            listProduits = new ArrayList<>();

            while (result.next()) {
                int id = result.getInt("IDProduit");
                String nom = result.getString("nomProduit");
                String categorie = result.getString("categorieProduit");
                double prix = result.getDouble("prixProduit");
                int quantite = result.getInt("quantiteProduit");

                Produit currentProduit = new Produit(id, nom, categorie, prix, quantite);
                listProduits.add(currentProduit);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listProduits;
    }
    @Override
    public boolean updateProduit(int id, String nom, String categorie, double prix, int quantite) {
        String query = "UPDATE Produits SET nomProduit=?, categorieProduit=?, prixProduit=?, quantiteProduit=? WHERE IDProduit=?";
        int nbRowModified = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, nom);
            ps.setString(2, categorie);
            ps.setDouble(3, prix);
            ps.setInt(4, quantite);
            ps.setInt(5, id);

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return (nbRowModified != 0);
    }
}