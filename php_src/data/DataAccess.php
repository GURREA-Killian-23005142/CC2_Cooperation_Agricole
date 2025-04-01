<?php
namespace data;

use service\APIClient;

class DataAccess {
    private $apiClient;

    public function __construct()
    {
        $this->apiClient = new APIClient();
    }

    public function getProducts(){
        return $this->apiClient->getProducts();
    }

    public function getOrders(){
        return $this->apiClient->getOrders();
    }

    public function placeOrder($productId, $userId){
        return $this->apiClient->placeOrder($productId, $userId);
    }
}