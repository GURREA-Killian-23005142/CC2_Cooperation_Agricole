package fr.univamu.fr.agricole;

import jakarta.inject.Inject;
import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

/**
 * La classe BDAccessProduit fournit des fonctionnalités pour interagir avec
 * une base de données afin de gérer des produits. Implémente ProduitInterface
 * et Closeable pour assurer une fermeture correcte de la connexion.
 */
public class BDAccessProduit implements ProduitInterface, Closeable {

    protected Connection dbConnection;

    /**
     * Constructeur par défaut injecté via Jakarta Dependency Injection.
     */
    public @Inject BDAccessProduit() {}

    /**
     * Constructeur permettant de créer une instance avec des informations
     * de connexion spécifiques.
     *
     * @param infoConnection Informations de connexion à la base de données.
     * @param user Nom d'utilisateur pour la connexion.
     * @param pwd Mot de passe pour la connexion.
     * @throws SQLException Si une erreur de connexion à la base survient.
     * @throws ClassNotFoundException Si le driver de base de données est introuvable.
     */
    public BDAccessProduit(String infoConnection, String user, String pwd) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection(infoConnection, user, pwd);
    }

    /**
     * Ferme la connexion à la base de données.
     */
    @Override
    public void close() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Récupère un produit spécifique par son identifiant.
     *
     * @param id Identifiant unique du produit.
     * @return Le produit correspondant ou null s'il n'est pas trouvé.
     */
    @Override
    public Produit getProduit(int id) {
        Produit selectedProduit = null;
        String query = "SELECT * FROM Produits WHERE IDProduit = ?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                String nom = result.getString("nomProduit");
                String categorie = result.getString("categorieProduit");
                double prix = result.getDouble("prixProduit");
                int quantite = result.getInt("quantiteProduit");
                selectedProduit = new Produit(id, nom, categorie, prix, quantite);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedProduit;
    }

    /**
     * Récupère tous les produits de la base de données.
     *
     * @return Liste de tous les produits.
     */
    @Override
    public ArrayList<Produit> getAllProduits() {
        ArrayList<Produit> listProduits = new ArrayList<>();
        String query = "SELECT * FROM Produits";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ResultSet result = ps.executeQuery();
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

    /**
     * Met à jour les informations d'un produit existant dans la base.
     *
     * @param id Identifiant du produit.
     * @param nom Nouveau nom du produit.
     * @param categorie Nouvelle catégorie du produit.
     * @param prix Nouveau prix du produit.
     * @param quantite Nouvelle quantité du produit.
     * @return true si la mise à jour a réussi, false sinon.
     */
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

    /**
     * Ajoute un nouveau produit à la base de données.
     *
     * @param produit Objet Produit contenant les informations à insérer.
     * @return true si l'ajout a réussi, false sinon.
     */
    @Override
    public boolean addProduit(Produit produit) {
        String query = "INSERT INTO Produits (nomProduit, categorieProduit, prixProduit, quantiteProduit) VALUES (?, ?, ?, ?)";
        int nbRowModified = 0;
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, produit.getNomProduit());
            ps.setString(2, produit.getCategorieProduit());
            ps.setDouble(3, produit.getPrixProduit());
            ps.setInt(4, produit.getQuantiteProduit());
            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (nbRowModified != 0);
    }

    /**
     * Supprime un produit spécifique de la base de données.
     *
     * @param id Identifiant du produit à supprimer.
     * @return true si la suppression a réussi, false sinon.
     */
    @Override
    public boolean deleteProduit(int id) {
        String query = "DELETE FROM Produits WHERE IDProduit = ?";
        int nbRowModified = 0;
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (nbRowModified != 0);
    }
}
