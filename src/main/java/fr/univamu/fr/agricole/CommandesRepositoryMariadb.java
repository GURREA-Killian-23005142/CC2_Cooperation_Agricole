package fr.univamu.fr.agricole;

import java.io.Closeable;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CommandesRepositoryMariadb implements Closeable, CommandesRepositoryInterface {

    protected Connection connection;

    public CommandesRepositoryMariadb(String infoConn, String user, String pwd) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        this.connection = DriverManager.getConnection(infoConn, user, pwd);
    }

    @Override
    public void close() {
        try{
            this.connection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

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
            e.printStackTrace();
        }
        return (nbRowInserted != 0);
    }

    @Override
    public ArrayList<Commandes> getAllCommandes() {
        ArrayList<Commandes> commandes = new ArrayList<>();
        String sql = "SELECT * FROM commandes";

        try (PreparedStatement ps = connection.prepareStatement(sql) ) {

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
            e.printStackTrace();
        }

        return commandes;
    }

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
            e.printStackTrace();
        }

        return commande;
    }

    @Override
    public boolean supprimerCommande(int IDCommandes) {
        String sql = "DELETE FROM commandes WHERE IDCommandes = ?";
        int nbRowDeleted = 0;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, IDCommandes);
            nbRowDeleted = ps.executeUpdate();
            System.out.println("Commande supprimée !");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (nbRowDeleted != 0);
    }

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
            e.printStackTrace();
        }
        return (nbRowModified != 0);
    }
}
