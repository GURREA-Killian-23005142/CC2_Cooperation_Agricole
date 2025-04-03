<?php
namespace src\main\views;

use controllers\Presenter;

/**
 * Classe ViewCart
 *
 * Cette classe gère l'affichage du panier utilisateur en récupérant les commandes stockées.
 */
class ViewCart {
    private $layout;
    private $presenter;

    public function __construct($layout, Presenter $presenter)
    {
        $this->layout = $layout;
        $this->presenter = $presenter;
    }

    /**
     * Affiche le contenue du panier de l'utilisateur
     *
     * @return void
     */
    public function display(){
        $carts = $this->presenter->getCart($_SESSION['user']['id'] ?? null);
        $content = "<h1>Votre Panier</h1><ul>";
        foreach ($carts as $cart){
            $content = "<li>{$cart['produit']} x {$cart['quantité']} - {$cart['prix']}€</li>";
        }
        $content .= "</ul>";
        $this->layout->render($content);
    }
}