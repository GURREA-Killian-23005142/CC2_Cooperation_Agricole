<?php

$request = isset($_GET['page']) ? $_GET['page'] : 'home';

switch ($request){
    case 'login':
        require '../src/pages/login.php';
        break;
    case 'paniers':
        require '../src/pages/paniers.php';
        break;
    case 'commande':
        require '../src/pages/commande.php';
        break;
    default:
        require '../src/pages/home.php';
}