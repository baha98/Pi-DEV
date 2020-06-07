<?php

namespace GestionLocationBundle\Controller;

use BlogBundle\Form\PostType;
use GestionEntretienBundle\Entity\entretien;
use GestionLocationBundle\Entity\Location;
use GestionLocationBundle\Form\LocationType;
use GestionProduitBundle\Entity\Velo;
use GestionUser\Entity\User;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Session\Session;
use Twilio\Rest\Client;
use Symfony\Component\HttpFoundation\Response;



class LocationController extends Controller
{
    public function showaddLocationAction($id)
    {

        $location = new Location();
        $velo = $this->getDoctrine()->getRepository(Velo::class)->find($id);
        $form = $this->createForm(LocationType::class,$location);
        return $this->render('@GestionLocation/Location/add_location.html.twig', array(
            'Velo' => $velo,
            'form' => $form->createView()
        ));

    }
    public function calendarAction()
    {
        return $this->render('@GestionLocation/Location/calendrier.html.twig');
    }

    public function pdfAction(Request $request, $id)
    {
        $em = $this->getDoctrine()->getManager();
        $location = $em->getRepository(Location::class)->find($id);
        $snappy = $this->get('knp_snappy.pdf');

        $html = $this->renderView('@GestionLocation/Location/pdf.html.twig', array(
            'location' => $location,
            "title" => "participation"
        ));
        $filename = 'Location';
        return new Response(
            $snappy->getOutputFromHtml($html),
            200,
            array(
                'Content-Type' => 'application/pdf',
                'Content-Disposition' => 'inline; filename="' . $filename . '.pdf"'
            )
        );
    }

    public function deletelocationAction($id)
    {
        $Location = new Location();
        $em = $this->getDoctrine()->getManager ();
        $Location= $em->getRepository(Location::class )->find($id);
        $em->remove($Location);
        $em->flush();
        $entretien = new entretien();
        $entretien->setEtat("DIAGNOSTIC");
        $entretien->setCycliste($this->getUser());
        $entretien->setUser($Location->getidUser());
        $entretien->setVelo($Location->getIdVelo());
        $em->persist($entretien);
        $em->flush();
        return $this->forward('GestionLocationBundle:Location:showLocation');
    }
    public function updatelocationAction($id)
    {
        $location = $this->getDoctrine()->getRepository(Location::class)->find($id);
        return $this->render('@GestionLocation/Location/update_location.html.twig', array(
            'Location' => $location
        ));
    }
    public function updatesubmitlocationAction(Request $request,$id)
    {

        $em = $this->getDoctrine()->getManager();
        $Location = $em->getRepository(Location::class)->find($id);
        $Location->setDateDebut(new \DateTime($_POST['datedebut']));
        $Location->setDatefin(new \DateTime($_POST['datefin']));
        $em->flush();
       return $this->forward('GestionLocationBundle:Location:showLocation');

    }
    public function showLocationAction(Request $request)
    {
        $session = $request->getSession();
        $Location = $this->getDoctrine()->getManager()->getRepository( Location::class )->findAll();
        $paginator=$this->get('knp_paginator');
        $result=$paginator->paginate($Location,
            $request->query->getInt('page',1),
            $request->query->getInt('limit',5));
        return $this->render('@GestionLocation/Location/show_location.html.twig', array(
            'user1' => $session->get('user'),
            'session' => $session,
            'Locations' => $result
        ));
    }
    public function frontshowLocationAction()
    {
        $velos = new Velo();
        $velos = $this->getDoctrine()->getRepository(Velo::class)->findBy(array(
            'etat'=>'A LOUER'
        ),array(),100,0);
        return $this->render('@GestionLocation/Location/front_show_location.html.twig', array(
            'Velos' => $velos
        ));
    }
    public function sendmail(Location $location,\Swift_Mailer $mailer)
    {
        $message = (new \Swift_Message('Location de Velo'))
            ->setFrom('larouetourne370@gmail.com')
            ->setTo('ahmedkhalil.slama@esprit.tn')
            ->setBody(
                $this->renderView(
                // app/Resources/views/Emails/registration.html.twig
                    'GestionLocationBundle:Location:pdf.html.twig',
                    ['location' => $location]
                ),
                'text/html'
            );

        /* // you can remove the following code if you don't define a text version for your emails
         ->addPart(
             $this->renderView(
                 'Emails/registration.txt.twig',
                 ['name' => $name]
             ),
             'text/plain'
         )
     ;*/
        $mailer->send($message);
    }
    public function addLocationAction($idvelo,Request $request)
    {
            $location = new Location();
            $form = $this->createForm(LocationType::class,$location);
            $form->handleRequest($request);
            $velo = $this->getDoctrine()->getRepository(Velo::class)->find($idvelo);
            $location->setIdVelo($velo);
            $location->setIdUser($this->getUser());
            $em = $this->getDoctrine()->getManager();
            $em->persist($location);
            $em->flush();
            $datetime1 = $location->getDateDebut();
            $datetime2 = $location->getDateFin();
            $interval = $datetime1->diff($datetime2);
            $price = ($velo->getPrix() * 0.2) * $interval->d;

            $client = new Client('AC0eef5ca660041b1c19dab0d6f5abbcab', '56759139e0fc7edfb06695cead72d23e');
            $client->messages->create(
            // Where to send a text message (your cell phone?)
                '+21650462520',
                array(
                    'from' => '+18644818482',
                    'body' => 'Vous avez fait la location du Velo :' . $velo->getLibelle() . ' de Marque ' . $velo->getMarque() . ' pour le prix de : ' . $price
                )
            );

            $transport = (new \Swift_SmtpTransport('smtp.gmail.com', 465, 'ssl'))
                ->setUsername('Larouetourne370@gmail.com')
                ->setPassword('tbygcfktbptzzwgg');
            $this->sendmail($location, new \Swift_Mailer($transport));


        return $this->forward('GestionLocationBundle:Location:frontshowLocation');
    }

}
