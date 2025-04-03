package fr.univamu.fr.agricole;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Classe ProduitApplication pour configurer les ressources REST
 * et gérer la production et la fermeture des connexions à la base de données.
 */
@ApplicationPath("/api")
@ApplicationScoped
public class ProduitApplication extends Application {

    /**
     * Produit une connexion à la base de données pour gérer les produits.
     *
     * @return Une instance de ProduitInterface pour interagir avec la base de données.
     * @throws RuntimeException Si une erreur se produit lors de la connexion à la base de données.
     */
    @Produces
    @ApplicationScoped
    private ProduitInterface openDbConnection() {
        BDAccessProduit dbConnection = null;
        try {
            dbConnection = new BDAccessProduit("jdbc:mariadb://mysql-archilogi000.alwaysdata.net/archilogi000_cc2", "395521_cc2", "Projetcc2");
        } catch (Exception e) {
            throw new RuntimeException("Il y a eu une erreur lors de la connexion à la bd", e);
        }
        return dbConnection;
    }

    /**
     * Ferme la connexion à la base de données lorsqu'elle n'est plus utilisée.
     *
     * @param produitRepo Une instance de ProduitInterface à fermer.
     */
    private void closeDbConnection(@Disposes ProduitInterface produitRepo) {
        produitRepo.close();
    }
}