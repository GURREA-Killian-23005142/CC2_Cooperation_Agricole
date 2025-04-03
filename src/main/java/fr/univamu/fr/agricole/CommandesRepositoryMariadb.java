package fr.univamu.fr.agricole;

import java.io.Closeable;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant l'accès aux données des commandes via une base MariaDB.
 */
public class CommandesRepositoryMariadb implements Closeable, CommandesRepositoryInterface {

    /**
     * Connexion à la base de données.
     */
    protected Connection connection;

    /**
     * Constructeur initialisant la connexion à la base de données.
     *
     * @param infoConn URL de connexion à la base de données.
     * @param user Nom d'utilisateur pour l'accès à la base de données.
     * @param pwd Mot de passe pour l'accès à la base de données.
     * @throws SQLException En cas d'erreur SQL.
     * @throws ClassNotFoundException Si le driver JDBC n'est pas trouvé.
     */
    public CommandesRepositoryMariadb(String infoConn, String user, String pwd) throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        this.connection = DriverManager.getConnection(infoConn, user, pwd);
    }

    /**
     * Ferme la connexion à la base de données.
     */
    @Override
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Ajoute une nouvelle commande dans la base de données.
     *
     * @param commande Objet Commandes à ajouter.
     * @return true si l'ajout est réussi, sinon false.
     */
    @Override
    public boolean ajouterCommande(Commandes commande) {
        String sql = "INSERT INTO commandes (IDPanier, prixCommandes, relais, dateRetrait) VALUES (?, ?, ?, ?)";
        int nbRowInserted = 0;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, commande.IDPanier);
            ps.setDouble(2, commande.prixCommandes);
            ps.setString(3, commande.relais);
            ps.setDate(4, Date.valueOf(commande.dateRetrait));
            nbRowInserted = ps.executeUpdate();
            System.out.println("Commande ajoutée avec succès !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (nbRowInserted != 0);
    }

    /**
     * Récupère toutes les commandes depuis la base de données.
     *
     * @return Liste des commandes enregistrées.
     */
    @Override
    public ArrayList<Commandes> getAllCommandes() {
        ArrayList<Commandes> commandes = new ArrayList<>();
        String sql = "SELECT * FROM commandes";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Commandes commande = new Commandes(
                        rs.getInt("IDCommandes"),
                        rs.getInt("IDPanier"),
                        rs.getDouble("prixCommandes"),
                        rs.getString("relais"),
                        rs.getDate("dateRetrait").toLocalDate()
                );
                commandes.add(commande);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return commandes;
    }

    /**
     * Récupère une commande spécifique en fonction de son ID.
     *
     * @param IDCommandes Identifiant unique de la commande.
     * @return Objet Commandes correspondant à l'ID spécifié, ou null si non trouvé.
     */
    @Override
    public Commandes getCommande(int IDCommandes) {
        String sql = "SELECT * FROM commandes WHERE IDCommandes = ?";
        Commandes commande = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, IDCommandes);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                commande = new Commandes(
                        rs.getInt("IDCommandes"),
                        rs.getInt("IDPanier"),
                        rs.getDouble("prixCommandes"),
                        rs.getString("relais"),
                        rs.getDate("dateRetrait").toLocalDate()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return commande;
    }

    /**
     * Supprime une commande de la base de données en fonction de son ID.
     *
     * @param IDCommandes Identifiant unique de la commande à supprimer.
     * @return true si la suppression a réussi, sinon false.
     */
    @Override
    public boolean supprimerCommande(int IDCommandes) {
        String sql = "DELETE FROM commandes WHERE IDCommandes = ?";
        int nbRowDeleted = 0;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, IDCommandes);
            nbRowDeleted = ps.executeUpdate();
            System.out.println("Commande supprimée !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (nbRowDeleted != 0);
    }

    /**
     * Met à jour une commande existante dans la base de données.
     *
     * @param id Identifiant unique de la commande à mettre à jour.
     * @param idPanier Identifiant du panier associé à la commande.
     * @param prixCommandes Prix total de la commande.
     * @param relais Nom du relais de livraison.
     * @param dateRetrait Date de retrait de la commande.
     * @return true si la mise à jour est réussie, sinon false.
     */
    @Override
    public boolean mettreAjourCommande(int id, int idPanier, double prixCommandes, String relais, LocalDate dateRetrait) {
        String sql = "UPDATE commandes SET IDPanier = ?, prixCommandes = ?, relais = ?, dateRetrait = ? WHERE IDCommandes = ?";
        int nbRowModified = 0;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idPanier);
            ps.setDouble(2, prixCommandes);
            ps.setString(3, relais);
            ps.setDate(4, Date.valueOf(dateRetrait));
            ps.setInt(5, id);
            nbRowModified = ps.executeUpdate();
            System.out.println("Commande mise à jour !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (nbRowModified != 0);
    }
}
