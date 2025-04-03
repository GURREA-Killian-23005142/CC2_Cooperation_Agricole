package fr.univamu.fr.agricole;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;


@ApplicationPath("/api")
@ApplicationScoped
public class PanierApplication extends Application {

    /**
     * Méthode appelée par l'API CDI pour injecter la connection à la base de données au moment de la création
     * de la ressource
     * @return un objet implémentant l'interface PanierRepositoryInterface utilisée
     *          pour accéder aux données des paniers
     */
    @Produces
    private PanierRepositoryInterface openDbConnection(){
        PanierRepositoryMariadb db = null;

        try{
            db = new PanierRepositoryMariadb("jdbc:mariadb://mysql-test123456789.alwaysdata.net/test123456789_panier_db", "390140_panier", "AlwaysDataMDP83!");
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return db;
    }

    /**
     * Méthode permettant de fermer la connexion à la base de données lorsque l'application est arrêtée
     * @param panierRepo la connexion à la base de données instanciée dans la méthode @openDbConnection
     */
    private void closeDbConnection(@Disposes PanierRepositoryInterface panierRepo ) {
        panierRepo.close();
    }
}
