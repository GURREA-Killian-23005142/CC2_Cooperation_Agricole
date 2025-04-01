<?php
namespace service;

class APIClient {
    private $baseUrl = "http://localhost:8080/api";

    public function getProducts(){
        return  $this->callApi("/products");
    }

    public function getOrders(){
        return $this->callApi("/orders");
    }

    public function placeOrder($productId, $userId){
        $data = ["productId" => $productId, "userId" => $userId];
        return $this->callApi("/orders", "POST", $data);
    }

    private function callApi($endpoint, $method = "GET", $data = null){
        $url = $this->baseUrl . $endpoint;
        $ch = curl_init($url);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

        if ($method === "POST"){
            curl_setopt($ch, CURLOPT_POST, true);
            curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($data));
            curl_setopt($ch, CURLOPT_HTTPHEADER,["Content-Type: application/json"]);
        }
        $response = curl_exec($ch);
        curl_close($ch);
        return json_decode($response, true);
    }
}