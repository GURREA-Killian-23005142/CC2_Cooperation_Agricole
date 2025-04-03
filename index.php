<?php
include_once 'src/main/controllers/Presenter.php';
include_once 'src/main/views/ViewLogin.php';
include_once 'src/main/views/ViewOrders.php';
include_once 'src/main/views/ViewPlaceOrder.php';
include_once 'src/main/views/ViewProducts.php';
include_once 'src/main/views/ViewHome.php';
include_once 'src/main/views/Layout.php';
include_once 'src/main/views/ViewRegister.php';

use controllers\Presenter;
use src\main\views\Layout;
use src\main\views\ViewHome;
use src\main\views\ViewLogin;
use src\main\views\ViewOrders;
use src\main\views\ViewPlaceOrder;
use src\main\views\ViewProducts;
use src\main\views\ViewRegister;

/**
 * Affihage des erreurs pour le débogage.
 */
error_reporting(E_ALL);
ini_set('display_errors', 1);

$presenter = new Presenter();

$uri = parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH);

/*Simulation d'une connexion temporaire le temps que la personne cote serveur la realise, et cela
va me permettre de voir si mes commandes marche*/
session_start();
if (!isset($_SESSION['user'])){
    $_SESSION['user'] = [
        'id' => 1,
        'name' => 'Jean Jacques-Francois',
        'email' => 'jean.jacques.francois@etu.univ-amu.fr'];
}

/**
 * Routage des requetes vers les differentes vues.
 */
if ('/' == $uri || '/index.php' == $uri){
    $layout = new Layout("src/main/views/layout.html");
    $view = new ViewHome($layout);
    $view->display();
} elseif ($uri == '/orders'){
    $layout = new Layout("src/main/views/layout.html");
    $view = new ViewOrders($layout, $presenter);
    $view->display();
} elseif ($uri == '/placeOrder'){
    $layout = new Layout("src/main/views/layout.html");
    $view = new ViewPlaceOrder($layout, $presenter);
    $view->display();
} elseif ($uri == '/login'){
    $layout = new Layout("src/main/views/layout.html");
    $view = new ViewLogin($layout, $presenter);
    $view->display();
} elseif ($uri == '/products'){
    $layout = new Layout("src/main/views/layout.html");
    $view = new ViewProducts($layout, $presenter);
    $view->display();
} elseif ($uri == "/register"){
    $layout = new Layout("src/main/views/layout.html");
    $view = new ViewRegister($layout,  $presenter);
    $view->display();
} else {
    http_response_code(404);
    echo "Page non trouvée.";
    exit;
}
