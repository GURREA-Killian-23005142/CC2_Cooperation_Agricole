<?php

include_once 'data/DataAccess.php';
include_once 'gui/Layout.php';
include_once 'gui/ViewProducts.php';
include_once 'gui/ViewOrders.php';
include_once 'gui/ViewPlaceOrder.php';

use gui\{Layout, ViewProducts, ViewOrders, ViewPlaceOrder};

$uri = parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH);
$layout = new Layout("gui/layout.html");

if ($uri == '/' || $uri == '/index.php'){
    $view = new ViewProducts($layout);
} elseif ($uri == '/orders'){
    $view = new ViewOrders($layout);
} elseif ($uri == '/placeOrder'){
    $view = new ViewPlaceOrder($layout);
} else {
    http_response_code(404);
    echo "Page non trouvée.";
    exit;
}

$view->display();
