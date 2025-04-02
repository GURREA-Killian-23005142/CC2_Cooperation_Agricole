<?php
namespace fr\univamu\fr\agricole\services;

class DataAccess {
    public function __construct() {
        // Ici, on ne fait rien pour l'instant (on ne charge pas APIClient)
    }

    public function fetchProducts() {
        return [
            ["name" => "Pomme", "price" => 2.5],
            ["name" => "Tomate", "price" => 3.0],
            ["name" => "Carotte", "price" => 1.2]
        ];
    }

    public function fetchCart() {
        return [
            ["product_name" => "Pomme", "quantity" => 2, "total_price" => 5],
            ["product_name" => "Tomate", "quantity" => 1, "total_price" => 3]
        ];
    }

    public function fetchUserOrders($userId) {
        return [
            ["id" => 101, "total" => 15, "status" => "Livré"],
            ["id" => 102, "total" => 25, "status" => "En cours"]
        ];
    }
}
