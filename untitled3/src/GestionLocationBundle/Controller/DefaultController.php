<?php

namespace GestionLocationBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('GestionLocationBundle:Default:index.html.twig');
    }
}
