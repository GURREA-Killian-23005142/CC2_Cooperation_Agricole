<?php
namespace fr\univamu\fr\agricole\view;

/**
 * Cllase ViewLogin
 *
 * Cette classe gère l'affichage de la page connexion
 */
class ViewLogin{
    private $layout;

    public function __construct($layout) {
        $this->layout = $layout;
    }

    /**
     * Affiche la page avec un formulaire
     *
     * @return void
     */
    public function display(){
        $content = '
            <section class="login-container">
                <form action="login_process.php" method="POST">
                    <h2>Se connecter</h2>
                    <div class="input-field">
                        <input type="email" name="email" required>
                        <label>Adresse Email</label>
                    </div>
                    <div class="input-field">
                        <input type="password" name="password" required>
                        <label>Mot de passe</label>
                    </div>
                    <div class="submit-field">
                        <button type="submit">Connexion</button>
                    </div>
                    <p>Pas encore de compte ? <a href="/register">Inscrivez-vous</a></p>
                </form>
            </section>';
        $this->layout->render($content);
    }
}
