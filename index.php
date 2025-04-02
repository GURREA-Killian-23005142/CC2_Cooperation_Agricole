<?php
include_once 'src/main/java/fr/univamu/fr/agricole/view/services/DataAccess.php';
include_once 'src/main/java/fr/univamu/fr/agricole/controllers/Presenter.php';
include_once 'src/main/java/fr/univamu/fr/agricole/views/ViewLogin.php';
include_once 'src/main/java/fr/univamu/fr/agricole/views/ViewOrders.php';
include_once 'src/main/java/fr/univamu/fr/agricole/views/ViewPlaceOrder.php';
include_once 'src/main/java/fr/univamu/fr/agricole/views/ViewLayout.php';

use fr\univamu\fr\agricole\controllers\Presenter;
use fr\univamu\fr\agricole\services\DataAccess;
use fr\univamu\fr\agricole\view\{ViewPlaceOrder, Layout, ViewOrders, ViewLogin, ViewProducts};


$dataAccess = new DataAccess();
$presenter = new Presenter($dataAccess);

$uri = parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH);
var_dump($uri);
if ('/' == $uri || '/index.php' == $uri){
    $layout = new Layout("src/main/webapp/layout.html");
    $view = new ViewProducts($layout);
    $view->display();
} elseif ($uri == '/orders'){
    $layout = new Layout("src/main/webapp/layout.html");
    $view = new ViewOrders($layout);
    $view->display();
} elseif ($uri == '/placeOrder'){
    $layout = new Layout("src/main/webapp/layout.html");
    $view = new ViewPlaceOrder($layout);
    $view->display();
} else {
    http_response_code(404);
    echo "Page non trouvée.";
    exit;
}
