package fr.univamu.fr.agricole;

/**
 * La classe Utilisateur représente un utilisateur avec ses propriétés telles que
 * l'identifiant, le nom, le prénom, l'email, le mot de passe, et le rôle.
 */
public class Utilisateur {

    private int idUtilisateur;
    private String nomUtilisateur;
    private String prenomUtilisateur;
    private String emailUtilisateur;
    private String passwordUtilisateur;
    private String roleUtilisateur;

    /**
     * Constructeur permettant d'initialiser toutes les propriétés de l'utilisateur.
     *
     * @param idUtilisateur Identifiant unique de l'utilisateur.
     * @param nomUtilisateur Nom de l'utilisateur.
     * @param prenomUtilisateur Prénom de l'utilisateur.
     * @param emailUtilisateur Adresse email de l'utilisateur.
     * @param passwordUtilisateur Mot de passe de l'utilisateur.
     * @param roleUtilisateur Rôle attribué à l'utilisateur (par exemple : client, admin).
     */
    public Utilisateur(int idUtilisateur, String nomUtilisateur, String prenomUtilisateur, String emailUtilisateur, String passwordUtilisateur, String roleUtilisateur) {
        this.idUtilisateur = idUtilisateur;
        this.nomUtilisateur = nomUtilisateur;
        this.prenomUtilisateur = prenomUtilisateur;
        this.emailUtilisateur = emailUtilisateur;
        this.passwordUtilisateur = passwordUtilisateur;
        this.roleUtilisateur = roleUtilisateur;
    }

    /**
     * Constructeur par défaut.
     */
    public Utilisateur() {
    }

    /**
     * Obtient l'identifiant de l'utilisateur.
     *
     * @return L'identifiant unique de l'utilisateur.
     */
    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    /**
     * Définit l'identifiant de l'utilisateur.
     *
     * @param id Identifiant unique de l'utilisateur.
     */
    public void setIdUtilisateur(int id) {
        this.idUtilisateur = id;
    }

    /**
     * Obtient le nom de l'utilisateur.
     *
     * @return Le nom de l'utilisateur.
     */
    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    /**
     * Définit le nom de l'utilisateur.
     *
     * @param nom Nom de l'utilisateur.
     */
    public void setNomUtilisateur(String nom) {
        this.nomUtilisateur = nom;
    }

    /**
     * Obtient le prénom de l'utilisateur.
     *
     * @return Le prénom de l'utilisateur.
     */
    public String getPrenomUtilisateur() {
        return prenomUtilisateur;
    }

    /**
     * Définit le prénom de l'utilisateur.
     *
     * @param prenom Prénom de l'utilisateur.
     */
    public void setPrenomUtilisateur(String prenom) {
        this.prenomUtilisateur = prenom;
    }

    /**
     * Obtient l'adresse email de l'utilisateur.
     *
     * @return L'adresse email de l'utilisateur.
     */
    public String getEmailUtilisateur() {
        return emailUtilisateur;
    }

    /**
     * Définit l'adresse email de l'utilisateur.
     *
     * @param email Adresse email de l'utilisateur.
     */
    public void setEmailUtilisateur(String email) {
        this.emailUtilisateur = email;
    }

    /**
     * Obtient le mot de passe de l'utilisateur.
     *
     * @return Le mot de passe de l'utilisateur.
     */
    public String getPasswordUtilisateur() {
        return passwordUtilisateur;
    }

    /**
     * Définit le mot de passe de l'utilisateur.
     *
     * @param password Mot de passe de l'utilisateur.
     */
    public void setPasswordUtilisateur(String password) {
        this.passwordUtilisateur = password;
    }

    /**
     * Obtient le rôle de l'utilisateur.
     *
     * @return Le rôle attribué à l'utilisateur.
     */
    public String getRoleUtilisateur() {
        return roleUtilisateur;
    }

    /**
     * Définit le rôle de l'utilisateur.
     *
     * @param role Rôle attribué à l'utilisateur.
     */
    public void setRoleUtilisateur(String role) {
        this.roleUtilisateur = role;
    }

    /**
     * Retourne une représentation textuelle de l'objet Utilisateur.
     *
     * @return Une chaîne de caractères représentant les propriétés de l'utilisateur.
     */
    @Override
    public String toString() {
        return "Utilisateur{" +
                "idUtilisateur=" + idUtilisateur +
                ", nomUtilisateur='" + nomUtilisateur +
                ", prenomUtilisateur='" + prenomUtilisateur +
                ", emailUtilisateur='" + emailUtilisateur +
                ", roleUtilisateur='" + roleUtilisateur +
                '}';
    }
}