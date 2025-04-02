<?php

use fr\univamu\fr\agricole\services\DataAccess;

class ViewOrders {
    private $layout;
    private $dataAccess;

    public function __construct($layout)
    {
        $this->layout = $layout;
        $this->dataAccess= new DataAccess();
    }

    public function display(){
        $orders = $this->dataAccess->fetchUserOrders(id, );
        $content = "<h1>Liste des Commandes</h1><ul>";
        foreach ($orders as $order){
            $content = "<li>Commande {$order['id']} - Produit {$order['productId']} par Utilisateur {$order['userId']}</li>";
        }
        $content .= "</ul>";
        $this->layout->render($content);
    }
}