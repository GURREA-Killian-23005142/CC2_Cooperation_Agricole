package fr.univamu.fr.agricole;

import jakarta.inject.Inject;
import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

/**
 * La classe BDAccessUtilisateur fournit des fonctionnalités pour interagir avec
 * une base de données afin de gérer des utilisateurs. Implémente UtilisateurInterface
 * et Closeable pour assurer une fermeture correcte de la connexion.
 */
public class BDAccessUtilisateur implements UtilisateurInterface, Closeable {

    protected Connection dbConnection;

    /**
     * Constructeur par défaut injecté via Jakarta Dependency Injection.
     */
    public @Inject BDAccessUtilisateur() {}

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
    public BDAccessUtilisateur(String infoConnection, String user, String pwd) throws SQLException, ClassNotFoundException {
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
     * Récupère un utilisateur spécifique par son identifiant.
     *
     * @param id Identifiant unique de l'utilisateur.
     * @return L'utilisateur correspondant ou null s'il n'est pas trouvé.
     */
    @Override
    public Utilisateur getUtilisateur(int id) {
        Utilisateur selectedUtilisateur = null;
        String query = "SELECT * FROM Utilisateurs WHERE IDUtilisateur = ?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                String nom = result.getString("nomUtilisateur");
                String prenom = result.getString("prenomUtilisateur");
                String email = result.getString("emailUtilisateur");
                String password = result.getString("passwordUtilisateur");
                String role = result.getString("roleUtilisateur");
                selectedUtilisateur = new Utilisateur(id, nom, prenom, email, password, role);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedUtilisateur;
    }

    /**
     * Récupère tous les utilisateurs de la base de données.
     *
     * @return Liste de tous les utilisateurs.
     */
    @Override
    public ArrayList<Utilisateur> getAllUtilisateur() {
        ArrayList<Utilisateur> listUtilisateurs = new ArrayList<>();
        String query = "SELECT * FROM Utilisateurs";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int id = result.getInt("IDUtilisateur");
                String nom = result.getString("nomUtilisateur");
                String prenom = result.getString("prenomUtilisateur");
                String email = result.getString("emailUtilisateur");
                String password = result.getString("passwordUtilisateur");
                String role = result.getString("roleUtilisateur");
                Utilisateur currentUtilisateur = new Utilisateur(id, nom, prenom, email, password, role);
                listUtilisateurs.add(currentUtilisateur);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listUtilisateurs;
    }

    /**
     * Met à jour les informations d'un utilisateur existant dans la base.
     *
     * @param id Identifiant de l'utilisateur.
     * @param nom Nouveau nom de l'utilisateur.
     * @param prenom Nouveau prénom de l'utilisateur.
     * @param email Nouvel email de l'utilisateur.
     * @param password Nouveau mot de passe de l'utilisateur.
     * @param role Nouveau rôle de l'utilisateur.
     * @return true si la mise à jour a réussi, false sinon.
     */
    @Override
    public boolean updateUtilisateur(int id, String nom, String prenom, String email, String password, String role) {
        String query = "UPDATE Utilisateurs SET nomUtilisateur=?, prenomUtilisateur=?, emailUtilisateur=?, passwordUtilisateur=?, roleUtilisateur=? WHERE IDUtilisateur=?";
        int nbRowModified = 0;
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, role);
            ps.setInt(6, id);
            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (nbRowModified != 0);
    }

    /**
     * Ajoute un nouvel utilisateur à la base de données.
     *
     * @param utilisateur Objet Utilisateur contenant les informations à insérer.
     * @return true si l'ajout a réussi, false sinon.
     */
    @Override
    public boolean addUtilisateur(Utilisateur utilisateur) {
        String query = "INSERT INTO Utilisateurs (nomUtilisateur, prenomUtilisateur, emailUtilisateur, passwordUtilisateur, roleUtilisateur) VALUES (?, ?, ?, ?, ?)";
        int nbRowModified = 0;
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, utilisateur.getNomUtilisateur());
            ps.setString(2, utilisateur.getPrenomUtilisateur());
            ps.setString(3, utilisateur.getEmailUtilisateur());
            ps.setString(4, utilisateur.getPasswordUtilisateur());
            ps.setString(5, utilisateur.getRoleUtilisateur());
            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (nbRowModified != 0);
    }

    /**
     * Supprime un utilisateur spécifique de la base de données.
     *
     * @param id Identifiant de l'utilisateur à supprimer.
     * @return true si la suppression a réussi, false sinon.
     */
    @Override
    public boolean deleteUtilisateur(int id) {
        String query = "DELETE FROM Utilisateurs WHERE IDUtilisateur = ?";
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