<?php

use fr\univamu\fr\agricole\services\DataAccess;

class ViewPlaceOrder {
    private $layout;
    private $dataAccess;

    public function __construct($layout)
    {
        $this->layout = $layout;
        $this->dataAccess= new DataAccess();
    }

    public function display(){
        if ($_SERVER['REQUEST_METHOD']==="POST"){
            $this->dataAccess->fetch($_POST['productId'], $_POST['userId']);
            echo "<p>Commande passée avec succès! </p>";
        }
        $content = '<h1>Passer une Commande</h1>
            <form method="post">
                <label>ID Produit: <input type="text" name="productId"> </label><br>
                <label>Quantité: <input type="text" name="quantité"> </label><br>
                <button type="submit">Commander</button>
            </form>';
            $this->layout->render($content);
    }
}