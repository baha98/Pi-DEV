<?php

namespace EvenementBundle\Controller;

use EvenementBundle\Entity\participation;
use EvenementBundle\Entity\evenement;
use UserBundle\Entity\User;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class participationController extends Controller
{
    public function ListparticipationAction()
    {


        $m = $this->getDoctrine()->getManager();
        $participation = $m->getRepository("EvenementBundle:participation")->findAll();


        return $this->render('EvenementBundle:participation:Afficheparticipation.html.twig', array(
            'participation' => $participation
        ));
    }
    public function supprimeparticipationAction($id)
    {

        $em = $this->getDoctrine()->getManager();

        $participation = $em->getRepository("EvenementBundle:participation")->find($id);
        $em->remove($participation);
        $em->flush();

        return $this->redirectToRoute('participation_Affiche');
    }
    public function editparticipationAction(Request $request, $id)
    {
        $em = $this->getDoctrine()->getManager();

        $participation = $em->getRepository('EvenementBundle:participation')->find($id);

           $editForm = $this->createForm('EvenementBundle\Form\participationType', $participation);

           $editForm->handleRequest($request);

           if ($editForm->isSubmitted() && $editForm->isValid()) {

               $em->persist($participation);
               $em->flush();

               return $this->redirectToRoute('participation_Affiche');
           }
           $em = $this->getDoctrine()->getManager();

        return $this->render('EvenementBundle:participation:editparticipation.html.twig', array(
            'participation' => $participation,
            'form' => $editForm->createView(),
        ));
    }
    public function AddparticipationAction($id)
    {

        $em = $this->getDoctrine()->getManager();
        $participation = new participation();
        $user = $em->getRepository(User::class)->find(3);
        $event = $em->getRepository(evenement::class)->find($id);

        $participation->setIdEvent($event);
        $participation->setIdMembre($user);
        $participation->setRanking(0);
        $participation->setRecord("00:00:00");


            $em->persist($participation);
            $em->flush();

            return $this->redirectToRoute('evenement_Front_Affiche');


    }

}
