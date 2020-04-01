<?php

namespace EvenementBundle\Controller;
use EvenementBundle\Entity\evenement;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;


class evenementController extends Controller
{

    public function AddevenementAction( \Symfony\Component\HttpFoundation\Request $request)
    {

        $em = $this->getDoctrine()->getManager();
        $evenement = new evenement();
        $form = $this->createForm('EvenementBundle\Form\evenementType', $evenement);
        $form->handleRequest($request);
        $now=new \DateTime('now');
        if ($form->isSubmitted() && $now<=$evenement->getDateevent()){
            $em->persist($evenement);
            $em->flush();




            return $this->redirectToRoute('evenement_Affiche');
        }

        return $this->render('EvenementBundle:evenement:ajoutevenement.html.twig', array(
            'evenement' => $evenement,
            'form' => $form->createView(),

        ));
    }
    public function ListevenementAction()
    {


        $m = $this->getDoctrine()->getManager();
        $evenement = $m->getRepository("EvenementBundle:evenement")->findAll();


        return $this->render('EvenementBundle:evenement:Afficheevenement.html.twig', array(
            'evenement' => $evenement
        ));
    }
    public function SupprimeAction($id)
    {

        $em = $this->getDoctrine()->getManager();

        $evenement = $em->getRepository("EvenementBundle:evenement")->find($id);
        $em->remove($evenement);
        $em->flush();
        $basic  = new \Nexmo\Client\Credentials\Basic('e3ddba5c', 'iXSxMlJU62xcQb5F');
        $client = new \Nexmo\Client($basic);

        $message = $client->message()->send([
            'to' => '21654958874',
            'from' => 'Velo.tn',
            'text' => 'Evenement suprimé',
        ]);


        return $this->redirectToRoute('evenement_Affiche');
    }
    public function editevenementAction(Request $request, $id)
    {
        $em = $this->getDoctrine()->getManager();

        $evenement = $em->getRepository('EvenementBundle:evenement')->find($id);
        $editForm = $this->createForm('EvenementBundle\Form\evenementType', $evenement);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {

            $em->persist($evenement);
            $em->flush();

            return $this->redirectToRoute('evenement_Affiche');
        }
        $em = $this->getDoctrine()->getManager();

        return $this->render('EvenementBundle:evenement:editevenement.html.twig', array(
            'evenement' => $evenement,
            'form' => $editForm->createView(),
        ));
    }
    public function ListeventFrontAction()
    {


        $m = $this->getDoctrine()->getManager();
        $evenement = $m->getRepository("EvenementBundle:evenement")->findAll();


        return $this->render('EvenementBundle:evenement:AfficheFrontevenement.html.twig', array(
            'evenement' => $evenement
        ));
    }
}
