<?php
use fr\univamu\fr\agricole\services\DataAccess;

/**
 * Classe ViewPlaceOrder
 *
 * Cette classe permet à un utilisateur de passer une commande en remplissant un formulaire
 */
class ViewPlaceOrder {
    private $layout;
    private $dataAccess;

    public function __construct($layout) {
        $this->layout = $layout;
        $this->dataAccess = new DataAccess();
    }

    /**
     * Affiche le formulaire de commande et gère la soumission
     *
     * Il :
     * - Verifie si la requete est de type POST.
     * - Verifie si les champs obligatoires sont remplis.
     * - Passe la commande en appelant 'placeOrder' et 'DataAccess'
     * - Affiche un message de succès ou d'erreur)
     * @return void
     */
    public function display() {
        $message = "";
        if ($_SERVER['REQUEST_METHOD'] === "POST") {
            if (!empty($_POST['productId']) && !empty($_POST['quantity']) && !empty($_POST['userId'])) {
                $this->dataAccess->placeOrder($_POST['productId'], $_POST['userId'], $_POST['quantity']);
                $message = "<p class='success'>Commande passée avec succès !</p>";
            } else {
                $message = "<p class='error'>Veuillez remplir tous les champs.</p>";
            }
        }
        $content = '
            <h1>Passer une Commande</h1>
            ' . $message . '
            <form method="post" class="order-form">
                <label for="productId">ID Produit:</label>
                <input type="text" name="productId" id="productId" required><br>

                <label for="userId">ID Utilisateur:</label>
                <input type="text" name="userId" id="userId" required><br>

                <label for="quantity">Quantité:</label>
                <input type="number" name="quantity" id="quantity" min="1" required><br>

                <button type="submit">Commander</button>
            </form>
        ';

        $this->layout->render($content);
    }
}
