<?php
namespace src\main\views;
use controllers\Presenter;
/**
 * Classe ViewPlaceOrder
 *
 * Cette classe permet à un utilisateur de passer une commande en remplissant un formulaire
 */
class ViewPlaceOrder {
    private $layout;
    private Presenter $presenter;

    public function __construct($layout, Presenter $presenter) {
        $this->layout = $layout;
        $this->presenter = $presenter;
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
            $data = [
                'product' => $_POST['productId'],
                'userId'=>$_POST['userId'],
                'quantity'=>$_POST['quantity']
            ];

            if($this->presenter->placeOrder($data)){
                $message = "<p class='success'>Commande passée !</p>";
            }else{
                $message = "<p class='error>Erreur lors de la commande.</p>";
            }
        }
        $content = '
            <h1>Passer une Commande</h1>
            {$message}
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
