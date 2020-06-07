<?php

namespace GestionUser\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Session\Session;
use Symfony\Component\HttpFoundation\Request;
use GestionUser\Repository\UserRepository;
use FOS\UserBundle\Doctrine\UserManager;

class SecurityController extends Controller
{
    public function addAction()
    {

    }

    public function redirectAction(Request $request)
    {

            $authChecker = $this->container->get('security.authorization_checker');
            $usermanager = $this->container->get('fos_user.user_manager');
            $currentUser = $usermanager->findUserByUsername($this->getUser()->getUsername());
            $session = $request->getSession();
            $session->set('user', $currentUser);
            if($authChecker->isGranted('ROLE_ADMIN'))
            {
                return $this->render('@GestionUser/Security/admin_home.html.twig',array(
                    'user1' =>$session->get('user'),
                    'session' =>$session,
                    'currentuser' =>$currentUser
                ));
            }
            else if ($authChecker->isGranted('ROLE_USER'))
            {
                return $this->render('@GestionUser/Security/user_home.html.twig');
            }
            else
            {
                return $this->render('@FOSUser/Security/login.html.twig');
            }

    }

}
