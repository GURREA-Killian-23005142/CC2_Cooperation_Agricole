package fr.univamu.fr.agricole;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Classe principale de l'application, configurant le point d'accès de l'API REST.
 * Elle gère la connexion à la base de données et l'injection des dépendances.
 */
@ApplicationPath("/api")
@ApplicationScoped
public class CommandesApplication extends Application {

    /**
     * Méthode appelée par l'API CDI pour injecter la connection à la base de données au moment de la création
     * de la ressource
     * @return un objet implémentant l'interface CommandesRepositoryInterface utilisée
     *          pour accéder aux données des commandes, voire les modifier
     */
    @Produces
    @ApplicationScoped
    private CommandesRepositoryInterface openDbConnection(){
        CommandesRepositoryMariadb db = null;

        try{
            db = new CommandesRepositoryMariadb("jdbc:mariadb://mysql-archilogici.alwaysdata.net/archilogici_agricole_bd", "396957_agricole", "agricole1234!");
        }
        catch (Exception e){
            throw new RuntimeException("Erreur de connexion à la base de données : " + e.getMessage());
        }
        return db;
    }

    /**
     * Méthode permettant de fermer la connexion à la base de données lorsque l'application est arrêtée
     * @param commandesRepo la connexion à la base de données instanciée dans la méthode @openDbConnection
     */
    private void closeDbConnection(@Disposes CommandesRepositoryInterface commandesRepo) {
        commandesRepo.close();
    }
}