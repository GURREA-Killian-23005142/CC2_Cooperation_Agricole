<?php
namespace src\main\views;
/**
 * Classe ViewRegister
 *
 * Cette classe gère l'affichage du formulaire d'inscriptions.
 */
class ViewRegister{
    private $layout;

    public function __construct($layout){
        $this->layout = $layout;
    }

    /**
     * Affiche le formulaire d'inscription
     * @return void
     */
    public function display(){
        $content = '
            <section class="register-container">
                <form action="register_process.php" method="POST">
                    <h2>Créer un compte</h2>
                    <div class="input-field">
                        <input type="text" name="fullname" required>
                        <label> : Nom</label>
                    </div>
                    <div class="input-flied">
                        <input type="email" name="email" required>
                        <label> : Adresse Email</label>
                    </div>
                    <div class="input-flied">
                        <input type="password" name="password" required>
                        <label> Mot de passe </label>
                    </div>
                    <div class="input-flied">
                        <input type="password" name="confirm_password" required>
                        <label> Confirmer le mot de passe </label>
                    </div>
                    <div class="submit-flied">
                        <button type="submit">S\'inscrire</button>
                    </div>
                    <p>Déjà un compte ? <a href="/login">Se connecter</a></p>
                </form>
            </section>
        ';
        $this->layout->render($content);
    }
}