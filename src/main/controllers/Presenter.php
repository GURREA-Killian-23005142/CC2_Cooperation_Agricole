<?php
namespace controllers;

/**
 * Classe Presenter
 *
 * Sert d'intermédiaire entre les vues PHP et les API.
 * Fournit des methodes pour récuperer les produits, le panier, les commandes et enovyer
 * une nouvelle commande via les requetes HTTP.
 */
class Presenter{
    private string $ApiUrlProduits;
    private string $ApiUrlCommande;
    private string $ApiUrlPanier;

    /**
     * Constructeur du Presenter
     * Initialise les URLS des APIs à utiliser
     */
    public function __construct(){
        $this->ApiUrlProduits = "http://localhost:8080/agricole-1.0-SNAPSHOT/produits";
        $this->ApiUrlCommande = "http://localhost:8080/agricole-1.0-SNAPSHOT/commandes";
        $this->ApiUrlPanier = "http://localhost:8080/agricole-1.0-SNAPSHOT/paniers";
    }

    /**
     * Recupere les produits depuis L'API
     * @return array
     */
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

    /**
     * Envoie une nouvelle commande à L'API
     * @param array $data
     * @return bool
     */
    public function placeOrder(array $data): bool{
        return $this->sendJson($this->ApiUrlCommande, $data);
    }

    /**
     * une requete HTTP GET et retourne le JSON décodé
     * @param string $url
     * @return arrayEffectue
     */
    private function fetchJson(string $url): array {
        $json = @file_get_contents($url);
        return $json ? json_decode($json, true) : [];
    }

    /**
     * Effectue une requete HTTP POST avec un corps JSON
     * 
     * @param string $url
     * @param array $data
     * @return bool
     */
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