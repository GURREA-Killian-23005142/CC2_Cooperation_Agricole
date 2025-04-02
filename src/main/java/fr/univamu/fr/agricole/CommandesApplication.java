package fr.univamu.fr.agricole;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

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
    private CommandesRepositoryInterface openDbConnection(){
        CommandesRepositoryMariadb db = null;

        try{
            db = new CommandesRepositoryMariadb("jdbc:mariadb://mysql-archilogici.alwaysdata.net/archilogici_library_db", "396957_library", "archilog13!");
        }
        catch (Exception e){
            System.err.println(e.getMessage());
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