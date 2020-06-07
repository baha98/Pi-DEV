<?php
// src/AppBundle/Entity/User.php

namespace GestionUser\Entity;

use FOS\UserBundle\Model\User as BaseUser;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass="GestionUser\Repository\UserRepository")
 * @ORM\Table(name="fos_user")
 */
class User extends BaseUser
{
    /**
     * @ORM\Id
     * @ORM\Column(type="integer")
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    protected $id;

    /**
     * @ORM\Column(type="string")
     */
    protected $nom_membre;

    /**
     * @ORM\Column(type="string")
     */
    protected $prenom_membre;
    /**
     * @ORM\Column(type="date")
     */
    protected $dateNais_membre;
    /**
     * @ORM\Column(type="string")
     */
    protected $NumTel_Membre;
    /**
     * @ORM\Column(type="string")
     */
    protected $Adresse_membre;
    /**
     * @ORM\OnetoMany(targetEntity="")
     */

    /**
     * @return mixed
     */
    public function getDateNaisMembre()
    {
        return $this->dateNais_membre;
    }

    /**
     * @param mixed $dateNais_membre
     */
    public function setDateNaisMembre($dateNais_membre)
    {
        $this->dateNais_membre = $dateNais_membre;
    }

    /**
     * @return mixed
     */
    public function getNumTelMembre()
    {
        return $this->NumTel_Membre;
    }

    /**
     * @param mixed $NumTel_Membre
     */
    public function setNumTelMembre($NumTel_Membre)
    {
        $this->NumTel_Membre = $NumTel_Membre;
    }

    /**
     * @return mixed
     */
        public function getAdresseMembre()
    {
        return $this->Adresse_membre;
    }

    /**
     * @param mixed $Adresse_membre
     */
    public function setAdresseMembre($Adresse_membre)
    {
        $this->Adresse_membre = $Adresse_membre;
    }

    /**
     * @return mixed
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @param mixed $id
     */
    public function setId($id)
    {
        $this->id = $id;
    }

    /**
     * @return mixed
     */
    public function getNomMembre()
    {
        return $this->nom_membre;
    }

    /**
     * @param mixed $nom_membre
     */
    public function setNomMembre($nom_membre)
    {
        $this->nom_membre = $nom_membre;
    }

    /**
     * @return mixed
     */
    public function getPrenomMembre()
    {
        return $this->prenom_membre;
    }

    /**
     * @param mixed $prenom_membre
     */
    public function setPrenomMembre($prenom_membre)
    {
        $this->prenom_membre = $prenom_membre;
    }

    public function __construct()
    {
        parent::__construct();
        // your own logic
    }
}