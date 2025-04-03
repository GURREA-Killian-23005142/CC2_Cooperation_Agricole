<?php
use fr\univamu\fr\agricole\services\DataAccess;

/**
 * Classe ViewCart
 *
 * Cette classe gère l'affichage du panier utilisateur en récupérant les commandes stockées.
 */
class ViewCart {
    private $layout;
    private $dataAccess;

    public function __construct($layout)
    {
        $this->layout = $layout;
        $this->dataAccess= new DataAccess();
    }

    /**
     * Affiche le contenue du panier de l'utilisateur
     *
     * @return void
     */
    public function display(){
        $carts = $this->dataAccess->fetchUserOrders();
        $content = "<h1>Panier</h1><ul>";
        foreach ($carts as $cart){
            $content = "<li>Commande {Produit {$cart['productId']} - Quantité {$cart['quantité']}</li>";
        }
        $content .= "</ul>";
        $this->layout->render($content);
    }
}