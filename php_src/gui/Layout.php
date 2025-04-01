<?php
namespace gui;

class Layout{
    private $template;

    public function __construct($template)
    {
        $this->template = $template;
    }

    public function render($content){
        ob_start();
        include $this->template;
        $layout = ob_get_clean();
        echo str_replace("{{content}}", $content, $layout);
    }
}