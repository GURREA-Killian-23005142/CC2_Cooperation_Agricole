<?php
namespace src\main\views;

use controllers\Presenter;
/**
 * Classe ViewRegister
 *
 * Cette classe gère l'affichage du formulaire d'inscriptions.
 */
class ViewRegister{
    private $layout;
    private Presenter $presenter;

    public function __construct($layout, Presenter $presenter){
        $this->layout = $layout;
        $this->presenter = $presenter;
    }

    /**
     * Affiche le formulaire d'inscription
     * @return void
     */
    public function display(){
        $message = "";

        if($_SERVER['REQUEST_METHOD'] === "POST") {
            $data = [
                'nom' => $_POST['fullname'],
                'email'=>$_POST['email'],
                'password'=>$_POST['password']
            ];

            if ($data["password"] !== ($_POST["confirm_password"]?? '')){
                $message = "<p class='error'>Les mots de passe ne correspondent pas</p>";
            }elseif($this->presenter->registerUser($data)){
                $message = "<p class='success'>Inscription reussie <a href='/login'>Connectez-vous</a></p>";
            }else {
                $message = "<p class='error'>Erreur lors de l'inscription. Verifier les donnes.</p>";
            }
        }
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