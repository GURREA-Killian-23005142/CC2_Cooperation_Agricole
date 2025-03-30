<?php
include '../includes/header.php';
include '../api/apiClient.php';

//$api = new ApiClient();
//$paniers = $pai->callAPI("/paniers");

echo "<h1>Liste des elements dans le paniers</h1>";
if(!empty($paniers)){
    foreach ($paniers as $panier){
        include '';
    }
} else {
    echo "<p>Panier vide.</p>";
}

include '../includes/footer.php';