<?php
namespace controllers;

/**
 * Classe Presenter
 *
 * Sert d'intermédiaire entre les vues et la logique métier
 */
class Presenter{
    private string $ApiUrlProduits;
    private string $ApiUrlCommande;
    private string $ApiUrlPanier;

    public function __construct(){
        $this->ApiUrlProduits = "http://localhost:8080/agricole-1.0-SNAPSHOT/produits";
        $this->ApiUrlCommande = "http://localhost:8080/agricole-1.0-SNAPSHOT/commandes";
        $this->ApiUrlPanier = "http://localhost:8080/agricole-1.0-SNAPSHOT/paniers";
    }

    public function getProducts(){
        return $this->fetchJson($this->ApiUrlProduits);
    }

    public function getUserOrders(?int $userId = null): array{
        $url = $this->ApiUrlCommande;
        return $this->fetchJson($url);
    }

    public function getCart(?int $userId = null): array{
        $url = $this->ApiUrlPanier;
        return $this->fetchJson($url);
    }

    public function placeOrder(array $data): bool{
        return $this->sendJson($this->ApiUrlCommande, $data);
    }

    private function fetchJson(string $url): array {
        $json = @file_get_contents($url);
        return $json ? json_decode($json, true) : [];
    }

    private function sendJson(string $url, array $data): bool{
        $options = [
            'http' => [
                'method' => 'POST',
                'header' => "Content-Type: application/json",
                'content' => json_encode($data)
            ]
        ];
        $context = stream_context_create($options);
        $response = @file_get_contents($url, false, $context);
        return $response !== false;
    }
}