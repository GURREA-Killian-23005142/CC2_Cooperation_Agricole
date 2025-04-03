<?php
namespace src\main\views;

use controllers\Presenter;
/**
 * Cllase ViewLogin
 *
 * Cette classe gère l'affichage de la page connexion
 */
class ViewLogin{
    private $layout;
    private Presenter $presenter;

    public function __construct($layout, Presenter $presenter) {
        $this->layout = $layout;
        $this->presenter =$presenter;
    }

    /**
     * Affiche la page avec un formulaire
     *
     * @return void
     */
    public function display(){
        $message = "";

        if($_SERVER['REQUEST_METHOD'] === "POST") {
            $email = $_POST["email"] ?? '';
            $password = $_POST["password"] ?? '';
            $users = $this->presenter->getUsers();

            $found =false;
            foreach ($users as $user){
                if($user["email"] === $email && $user["password"] === $password){
                    $_SERVER["user"] = $user;
                    header("Location: /");
                    exit;
                }
            }
            $message = "<p class='error'>Identifiants incorrects.</p>";
        }
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
