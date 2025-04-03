package fr.univamu.fr.agricole;

import java.util.ArrayList;

public interface UtilisateurInterface {

    public void close();

    public Utilisateur getUtilisateur(int id );

    public ArrayList<Utilisateur> getAllUtilisateur();

    public boolean updateUtilisateur(int idUtilisateur, String nomUtilisateur, String prenomUtilisateur, String emailUtilisateur, String passwordUtilisateur, String roleUtilisateur);

    public boolean addUtilisateur(Utilisateur utilisateur);

    public boolean deleteUtilisateur(int id);
}
