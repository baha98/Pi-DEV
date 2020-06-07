<?php

namespace GestionEntretienBundle\Controller;

use GestionEntretienBundle\Entity\entretien;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class EntretienController extends Controller
{
    public function showentretienAction()
    {
        $entretien = $this->getDoctrine()->getRepository(entretien::class)->findAll();
       return  $this->render('@GestionEntretien/Entretien/show_entretien.html.twig',array(
          'Entretiens' => $entretien
       ));
    }

    public function showentretiendetailsAction($id)
    {
        $entretien = $this->getDoctrine()->getRepository(entretien::class)->find($id);
        return $this->render('@GestionEntretien/Entretien/show_details_entretien.html.twig',array(
           'Entretien' => $entretien
        ));
    }

    public function deleteentretienAction($id)
    {
        $Entretien = new Entretien();
        $em = $this->getDoctrine()->getManager();
        $Entretien= $em->getRepository(Entretien::class )->find($id);
        $em->remove($Entretien);
        $em->flush();
        return $this->forward('GestionEntretienBundle:Entretien:showentretien');
    }

    public function updateentretienAction($id)
    {
        $Entretien = new entretien();
        $em = $this->getDoctrine()->getManager();
        $Entretien = $em->getRepository(Entretien::class)->find($id);
        $Entretien->setEtat($_POST['Etat']);
        $em->flush();
        return $this->forward('GestionEntretienBundle:Entretien:showentretien');
    }
}
