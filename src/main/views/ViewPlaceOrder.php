<?php

use fr\univamu\fr\agricole\services\DataAccess;

class ViewPlaceOrder {
    private $layout;
    private $dataAccess;

    public function __construct($layout) {
        $this->layout = $layout;
        $this->dataAccess = new DataAccess();
    }

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
