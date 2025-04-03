package fr.univamu.fr.agricole;

import jakarta.inject.Inject;
import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;


public class BDAccessUtilisateur implements UtilisateurInterface, Closeable {

    protected Connection dbConnection;

    public @Inject BDAccessUtilisateur() {}

    public BDAccessUtilisateur(String infoConnection, String user, String pwd) throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection(infoConnection, user, pwd);
    }

    @Override
    public void close() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

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
