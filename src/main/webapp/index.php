<?php

require_once __DIR__ . '/../java/fr/univamu/fr/agricole/services/DataAccess.php';
require_once __DIR__ . '/../java/fr/univamu/fr/agricole/controllers/Controllers.php';
require_once __DIR__ . '/../java/fr/univamu/fr/agricole/controllers/Presenter.php';
require_once __DIR__ . '/../java/fr/univamu/fr/agricole/view/service/Layout.php';
require_once __DIR__ . '/../java/fr/univamu/fr/agricole/view/ViewCart.php';
require_once __DIR__ . '/../java/fr/univamu/fr/agricole/view/ViewLogin.php';
require_once __DIR__ . '/../java/fr/univamu/fr/agricole/view/ViewOrders.php';
require_once __DIR__ . '/../java/fr/univamu/fr/agricole/view/ViewPlaceOrder.php';
require_once __DIR__ . '/../java/fr/univamu/fr/agricole/view/ViewProducts.php';

use fr\univamu\fr\agricole\view\service\Layout;
use fr\univamu\fr\agricole\view\ViewOrders;
use fr\univamu\fr\agricole\view\ViewProducts;
use fr\univamu\fr\agricole\view\ViewPlaceOrder;
use fr\univamu\fr\agricole\controllers\Presenter;
use fr\univamu\fr\agricole\services\DataAccess;

$dataAccess = new DataAccess();
$presenter = new Presenter();

$uri = parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH);
$layout = new Layout("layout.html");

if ($uri == '/' || $uri == '/index.php'){
    $view = new ViewProducts($layout);
    $view->display();
} elseif ($uri == '/orders'){
    $view = new ViewOrders($layout);
    $view->display();
} elseif ($uri == '/placeOrder'){
    $view = new ViewPlaceOrder($layout);
    $view->display();
} else {
    http_response_code(404);
    echo "Page non trouvée.";
    exit;
}

$view->display();
