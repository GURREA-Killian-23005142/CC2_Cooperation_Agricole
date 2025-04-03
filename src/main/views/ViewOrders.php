<?php
namespace src\main\views;

use controllers\Presenter;

/**
 * Classe ViewOrders
 *
 * Cette classe est responsable de l'affichage des commandes.
 */
class ViewOrders {
    private $layout;
    private $presenter;

    public function __construct($layout, Presenter $presenter)
    {
        $this->layout = $layout;
        $this->presenter = $presenter;
    }

    /**
     * Affiche la liste des commandes de l'utilisateur
     *
     * - Si il est pas connecté, sa affiche une erreur.
     * - Si il est connecté, ses commandes sont affichés sous forme d'un tableau
     *
     * @return void
     */
    public function display(){
        if (!isset($_SESSION['user'])){
            $content = '<p class="error">Vous devez être connecté pour voir vos commandes <a href="/">Se connecter</a></p>';
        }else{
            $orders = $this->presenter->getUserOrders($_SESSION['user']['id']);

            $content = "<div class='orders-container'>
                        <h2>Liste de vos commandes</h2>
                        <table class='orders-table'>
                            <tr>
                                <th>ID Commande</th>
                                <th>Produit</th>
                                <th>Quantité</th>
                                <th>Prix</th>
                            </tr>";
            foreach ($orders as $order){
                $content .= "<tr>
                    <td>{$order['id']}</td>
                    <td>{$order['produit']}</td> 
                    <td>{$order['quantité']}</td>
                    <td>{$order['prix']}</td>
                    </tr>";
            }
            $content .= "</table>";
        }
        $this->layout->render($content);
    }
}