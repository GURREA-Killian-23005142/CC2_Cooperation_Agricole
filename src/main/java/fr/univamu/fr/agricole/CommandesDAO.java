package fr.univamu.fr.agricole;

import fr.univamu.fr.agricole.BDAccess;
import fr.univamu.fr.agricole.Commandes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandesDAO {

    public void ajouterCommande(Commandes commande) {
        String sql = "INSERT INTO commandes (IDPanier, prixCommandes, relais, dateRetrait) VALUES (?, ?, ?, ?)";

        try (BDAccess db = new BDAccess();
             Connection conn = db.BDAccessPDO();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, commande.getIDPanier());
            stmt.setDouble(2, commande.getPrixCommandes());
            stmt.setString(3, commande.getRelais());
            stmt.setDate(4, Date.valueOf(commande.getDateRetrait()));

            stmt.executeUpdate();
            System.out.println("Commande ajoutée avec succès !");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Commandes> getAllCommandes() {
        List<Commandes> commandes = new ArrayList<>();
        String sql = "SELECT * FROM commandes";

        try (BDAccess db = new BDAccess();
             Connection conn = db.BDAccessPDO();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

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
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return commandes;
    }

    public void supprimerCommande(int IDCommandes) {
        String sql = "DELETE FROM commandes WHERE IDCommandes = ?";

        try (BDAccess db = new BDAccess();
             Connection conn = db.BDAccessPDO();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, IDCommandes);
            stmt.executeUpdate();
            System.out.println("Commande supprimée !");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
