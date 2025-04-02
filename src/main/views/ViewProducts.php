<?php

use fr\univamu\fr\agricole\services\DataAccess;

class ViewProducts {
    private $layout;
    private $dataAccess;

    public function __construct($layout)
    {
        $this->layout = $layout;
        $this->dataAccess= new DataAccess();
    }

    public function display(){
        $products = $this->dataAccess->fetchProducts();
        $content = "<h1>Liste des Produits</h1><ul>";
        foreach ($products as $product){
            $content = "<li>{$product['name']} - {$product['price']}€</li>";
        }
        $content .= "</ul>";
        $this->layout->render($content);
    }
}