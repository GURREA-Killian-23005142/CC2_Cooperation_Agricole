package fr.univamu.fr.agricole;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
@ApplicationScoped
public class ProduitApplication  extends Application {

    @Produces
    @ApplicationScoped
    private ProduitInterface openDbConnection(){
        BDAccessProduit dbConnection = null;

        try{
            dbConnection = new BDAccessProduit("jdbc:mariadb://mysql-archilogi000.alwaysdata.net/archilogi000_cc2", "395521_cc2", "Projetcc2");
        }
        catch (Exception e){
            throw new RuntimeException("Il y a eu une erreur lors de la connexion à la bd",e);
        }
        return dbConnection;
    }

    private void closeDbConnection(@Disposes ProduitInterface produitRepo ) {
        produitRepo.close();
    }
}