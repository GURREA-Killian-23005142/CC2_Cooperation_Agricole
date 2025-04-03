<?php
namespace src\main\views;
/**
 * Classe ViewHome
 *
 * Classe qui gère l'affichage de la page d'acceuil.
 */
class ViewHome {
    private $layout;

    public function __construct($layout) {
        $this->layout = $layout;
    }

    /**
     * Affiche le contenue
     * @return void
     */
    public function display() {
        $content = '
            <section class="home-container">
                <h1>Bienvenue sur notre plateforme agricole</h1>
                <p>Découvrez nos produits et passez vos commandes en toute simplicité.</p>
                <div class="buttons">
                    <a href="/products" class="btn">Voir les produits</a>
                    <a href="/placeOrder" class="btn">Passer une commande</a>
                </div>
            </section>
        ';
        $this->layout->render($content);
    }
}
