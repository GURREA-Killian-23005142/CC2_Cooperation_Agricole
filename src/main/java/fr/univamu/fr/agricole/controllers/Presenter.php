<?php
namespace fr\univamu\fr\agricole\controllers;

use fr\univamu\fr\agricole\services\DataAccess;

class Presenter{
    private DataAccess $dataAccess;

    public function __construct(DataAccess  $dataAccess){
        $this->dataAccess = $dataAccess;
    }

    public function presentProducts(){
        $products = $this->dataAccess->fetchProducts();
        if (empty($products)){
            return "<p>Aucun produit disponible.</p>";
        }
        $html = "<ul>";
        foreach ($products as $product){
            $html .= "<li>{$product['name']} - {$product['price']}€</li>";
        }
        $html .= "</ul>";
        return $html;
    }

    public function presentCart($userId){
        $cartItems = $this->dataAccess->fetchCart();
        if (empty($cartItems)){
            return "<p>Votre panier est vide.</p>";
        }
        $html = "<ul>";
        foreach ($cartItems as $item){
            $html .= "<li>{$item['product_name']} x {$item['quantity']} - "
        }
    }
}