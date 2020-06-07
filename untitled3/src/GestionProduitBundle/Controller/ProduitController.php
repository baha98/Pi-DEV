<?php

namespace GestionProduitBundle\Controller;

use GestionProduitBundle\Entity\Velo;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Response;
use GestionProduitBundle\Entity\Accessoire;
use Symfony\Component\HttpFoundation\Request;

class ProduitController extends Controller
{
    public function ajouterveloAction() {

        //ecrire ici les attribits de velo
        //faire deux controllers
        $velo = new Velo();
        $velo->setLibelle($_POST['libelle']);
        $velo->setCouleur($_POST['couleur']);
        $velo->setDescription($_POST['description']);
        $velo->setcategorieVelo($_POST['categorie']);
        $velo->setPrix($_POST['prix']);
        $velo->setMarque($_POST['marque']);
        $velo->setMembre(1);
        $velo->setImage("img");
        $velo->setEtat("etat");
        $velo->setQte($_POST['quantity']);
        $em = $this->getDoctrine()->getManager();
        $em->persist($velo);
        $em->flush();

        return $this->render('@GestionProduit/Produit/show.html.twig');
    }

    public function affichemodifAction(Request $request,$id) {

        $velo = $this->getDoctrine()->getManager()->getRepository( Velo::class)->find($id);
        return $this->render('@GestionProduit/Produit/modifevelo.html.twig', array('Velo'  => $velo));
    }
    public function modifieveloAction() {
        $em = $this->getDoctrine()->getManager();
        $Velo = $em->getRepository( Velo::class)->find($_POST['IdProduit']);
        $Velo->setLibelle($_POST['libelle']);
        $Velo->setMarque($_POST['marque']);
        $Velo->setPrix($_POST['prix']);
        $Velo->setQte($_POST['quantity']);
        $Velo->setCouleur($_POST['couleur']);
        $Velo->setDescription($_POST['description']);
        $Velo->setImage($_POST['file']);
        $Velo->setCouleur($_POST['couleur']);
        $Velo->setCategorieVelo($_POST['categorie']);
        $Velo->setEtat($_POST['etat']);
        $Velo->setMembre(1);
        $em->flush();
        return $this->redirectToRoute('gestvelo');
    }
    public function villeAction()
    {
        $Velos = $this->getDoctrine()->getRepository(Velo::class)->findAll();
        $data = array();
        $i = 0;
        foreach ($Velos as $ac) {
            if ($ac->getCategorieVelo() == 'VELOS DE VILLE') {
                $data[$i] = $ac;
                $i++;
            }
        }
        return $this->render('default/educentervelo.html.twig', array('Velos' => $data));
    }
    public function routeAction()
    {
        $Velos = $this->getDoctrine()->getRepository(Velo::class)->findAll();
        $data = array();
        $i = 0;
        foreach ($Velos as $ac) {
            if ($ac->getCategorieVelo() == 'VELOS DE ROUTE') {
                $data[$i] = $ac;
                $i++;
            }
        }
        return $this->render('default/educentervelo.html.twig', array('Velos' => $data));
    }
    public function trekkingAction()
    {
        $Velos = $this->getDoctrine()->getRepository(Velo::class)->findAll();
        $data = array();
        $i = 0;
        foreach ($Velos as $ac) {
            if ($ac->getCategorieVelo() == 'VELOS DE TREKKING') {
                $data[$i] = $ac;
                $i++;
            }
        }
        return $this->render('default/educentervelo.html.twig', array('Velos' => $data));
    }
    public function electriqueAction()
    {
        $Velos = $this->getDoctrine()->getRepository(Velo::class)->findAll();
        $data = array();
        $i = 0;
        foreach ($Velos as $ac) {
            if ($ac->getCategorieVelo() == 'VELOS ELECTRIQUE') {
                $data[$i] = $ac;
                $i++;
            }
        }
        return $this->render('default/educentervelo.html.twig', array('Velos' => $data));
    }
    public function enfantadosAction()
    {
        $Velos = $this->getDoctrine()->getRepository(Velo::class)->findAll();
        $data = array();
        $i = 0;
        foreach ($Velos as $ac) {
            if ($ac->getCategorieVelo() == 'VELOS ENFANT & ADOS') {
                $data[$i] = $ac;
                $i++;
            }
        }
        return $this->render('default/educentervelo.html.twig', array('Velos' => $data));
    }
    public function gestveloAction()
    {
        $Velos = $this->getDoctrine()->getManager()->getRepository(Velo::class)->findAll();
        return $this->render('@GestionProduit/Produit/gestvelo.html.twig', array('Velos' => $Velos));

    }

    public function deleteveloAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $Velo= $em->getRepository(Velo::class)->find($id);
        $em->remove($Velo);
        $em->flush();
        echo "<script>alert('suppression avec succes')</script>";
        return $this->redirectToRoute('gestvelo');

    }
    public function educenterveloAction()
    {
        $Velos = $this->getDoctrine()->getManager()->getRepository(Velo::class)->findAll();
        return $this->render('default/educentervelo.html.twig', array('Velos' => $Velos));
    }

}
