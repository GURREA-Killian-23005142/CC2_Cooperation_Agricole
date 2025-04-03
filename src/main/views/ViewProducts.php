<?php
namespace src\main\views;

use controllers\Presenter;
/**
 * Classe ViewProducts
 *
 * Affiche la liste des produits disponibles sur la platforme
 */
class ViewProducts {
    private $layout;
    private Presenter $presenter;

    public function __construct($layout, Presenter $presenter)
    {
        $this->layout = $layout;
        $this->presenter = $presenter;
    }

    /**
     * Affiche la liste des produits disponibles
     *
     * - Recupere la liste des produits avec 'fetchProducts()'.
     * - Genere une liste des produits sour forme de cartes.
     * - Chaque produit est affiché avec son nom et son prix
     * - Un bouton "Ajouter au panier' est présent pour chaque produit.
     *
     * @return void
     */
    public function display(){
        $products = $this->presenter->getProducts();
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
