<?php

class Layout{
    private $template;

    public function __construct($template = "src/views/layout.html")
    {
        $this->template = $template;
    }

    public function render($content){
        if (file_exists($this->template)) {
            $layout = file_get_contents($this->template);
            echo str_replace("{{content}}", $content, $layout);
        } else {
            echo "<p>Erreur : Impossible de charger le layout.</p>";
        }
    }
}