<?php
namespace fr\univamu\fr\agricole\view;

use fr\univamu\fr\agricole\services\DataAccess;

class ViewProducts {
    private $layout;
    private $dataAccess;

    public function __construct($layout)
    {
        $this->layout = $layout;
        $this->dataAccess = new DataAccess();
    }

    public function display(){
        $products = $this->dataAccess->fetchProducts();
        $content = "<section class='products-container'>";
        $content .= "<h1>Nos Produits</h1><div class='products-list'>";

        foreach ($products as $product){
            $content .= "<div class='product-card'>
                            <h2>{$product['name']}</h2>
                            <p>Prix : {$product['price']}€</p>
                            <button class='btn'>Ajouter au panier</button>
                         </div>";
        }
        $content .= "</div></section>";

        $this->layout->render($content);
    }
}
