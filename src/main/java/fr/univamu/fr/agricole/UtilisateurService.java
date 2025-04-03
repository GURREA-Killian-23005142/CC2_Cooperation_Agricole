package fr.univamu.fr.agricole;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.util.ArrayList;

@ApplicationScoped
public class UtilisateurService {
    
    protected UtilisateurInterface UtilisateurRepo ;

    @Inject
    public UtilisateurService( UtilisateurInterface UtilisateurRepo) {
        this.UtilisateurRepo = UtilisateurRepo;
    }

    public UtilisateurService() {}


    public String getAllUtilisateurJSON(){

        ArrayList<Utilisateur> allUtilisateurs = UtilisateurRepo.getAllUtilisateur();

        // création du json et conversion de la liste de livres
        String result = null;
        try(Jsonb jsonb = JsonbBuilder.create()){
            result = jsonb.toJson(allUtilisateurs);
        }
        catch (Exception e){
            System.err.println( e.getMessage() );
        }

        return result;
    }


    public String getUtilisateurJSON( int id ){
        String result = null;
        Utilisateur myUtilisateur = UtilisateurRepo.getUtilisateur(id);

        // si le livre a été trouvé
        if( myUtilisateur != null ) {

            // création du json et conversion du livre
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myUtilisateur);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }


    public boolean updateUtilisateur(int id, Utilisateur Utilisateur) {
        return UtilisateurRepo.updateUtilisateur(id, Utilisateur.getNomUtilisateur(), Utilisateur.getPrenomUtilisateur(), Utilisateur.getEmailUtilisateur(), Utilisateur.getPasswordUtilisateur(), Utilisateur.getRoleUtilisateur());
    }

    public boolean addUtilisateur(Utilisateur Utilisateur) {
        return UtilisateurRepo.addUtilisateur(Utilisateur);
    }

    public boolean deleteUtilisateur(int id) {
        return UtilisateurRepo.deleteUtilisateur(id);
    }
}
