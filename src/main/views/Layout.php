<?php
namespace src\main\views;
/**
 * Classe Layout
 *
 * Cette classe gère l'affichage du layout principale en intégrant le contenu des vues.
 */
class Layout{
    private $template;

    public function __construct($template = "src/views/layout.html")
    {
        $this->template = $template;
    }

    /**
     * Affichage du contenue dans le template.
     *
     * @param string $content Contenu HTMLà inserer dans le layout
     * @return void
     */
    public function render($content){
        if (file_exists($this->template)) {
            $layout = file_get_contents($this->template);
            echo str_replace("{{content}}", $content, $layout);
        } else {
            echo "<p>Erreur : Impossible de charger le layout.</p>";
        }
    }
}