package fr.univamu.fr.agricole;


public class Utilisateur {

    private int idUtilisateur;
    private String nomUtilisateur;
    private String prenomUtilisateur;
    private String emailUtilisateur;
    private String passwordUtilisateur;
    private String roleUtilisateur;

    public Utilisateur(int idUtilisateur, String nomUtilisateur, String prenomUtilisateur, String emailUtilisateur, String passwordUtilisateur, String roleUtilisateur) {
        this.idUtilisateur = idUtilisateur;
        this.nomUtilisateur = nomUtilisateur;
        this.prenomUtilisateur = prenomUtilisateur;
        this.emailUtilisateur = emailUtilisateur;
        this.passwordUtilisateur = passwordUtilisateur;
        this.roleUtilisateur = roleUtilisateur;
    }

    public Utilisateur() {

    }

    public int getIdUtilisateur() { return idUtilisateur; }
    public void setIdUtilisateur(int id) { this.idUtilisateur = id; }

    public String getNomUtilisateur() { return nomUtilisateur; }
    public void setNomUtilisateur(String nom) { this.nomUtilisateur = nom; }

    public String getPrenomUtilisateur() { return prenomUtilisateur; }
    public void setPrenomUtilisateur(String prenom) { this.prenomUtilisateur = prenom; }

    public String getEmailUtilisateur() { return emailUtilisateur; }
    public void setEmailUtilisateur(String email) { this.emailUtilisateur = email; }

    public String getPasswordUtilisateur() { return passwordUtilisateur; }
    public void setPasswordUtilisateur(String password) { this.passwordUtilisateur = password; }

    public String getRoleUtilisateur() { return roleUtilisateur; }
    public void setRoleUtilisateur(String role) { this.roleUtilisateur = role; }


    @Override
    public String toString() {
        return "Utilisateur{" +
                "idUtilisateur=" + idUtilisateur +
                ", nomUtilisateur='" + nomUtilisateur  +
                ", prenomUtilisateur='" + prenomUtilisateur +
                ", emailUtilisateur='" + emailUtilisateur  +
                ", roleUtilisateur='" + roleUtilisateur +
                '}';
    }
}