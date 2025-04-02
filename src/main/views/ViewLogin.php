<?php

class ViewLogin{
    public function display(){
        ?>
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-cale=1.0">
            <title>Se connecter</title>
            <link rel="stylesheet" href="style/login.css">
        </head>
        <body>
            <div class="wrapper">
                <form action="#">
                    <h2>Login</h2>
                    <div class="input-field">
                        <input type="text" required>
                        <label>Entrer votre mail</label>
                    </div>
                    <div class="input-flied">
                        <input type="mdp" required>
                        <label>Entrer votre mot de passe</label>
                    </div>
                </form>
            </div>
        </body>
        </html>
    <?php
    }
}