<?php

namespace GestionUser\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use AppBundle\AppBundle;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('base.html.twig');
    }
}
