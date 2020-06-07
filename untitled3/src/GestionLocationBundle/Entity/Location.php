<?php

namespace GestionLocationBundle\Entity;

use DateTime;
use Doctrine\ORM\Mapping as ORM;

/**
 * Location
 *
 * @ORM\Table(name="location")
 * @ORM\Entity(repositoryClass="GestionLocationBundle\Repository\LocationRepository")
 */
class Location
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $idLocation;

    /**
     * @ORM\ManyToOne(targetEntity="GestionUser\Entity\User")
     */
    private $idUser;

    /**
     * @ORM\ManyToOne(targetEntity="GestionProduitBundle\Entity\Velo")
     */
    private $idVelo;

    /**
     * @var DateTime
     *
     * @ORM\Column(name="date_debut", type="date")
     */
    private $dateDebut;

    /**
     * @var DateTime
     *
     * @ORM\Column(name="date_fin", type="date")
     */
    private $dateFin;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->idLocation;
    }

    /**
     * Set idUser
     *
     * @param integer $idUser
     *
     * @return Location
     */
    public function setIdUser($idUser)
    {
        $this->idUser = $idUser;

        return $this;
    }

    /**
     * Get idUser
     *
     * @return int
     */
    public function getIdUser()
    {
        return $this->idUser;
    }

    /**
     * Set idVelo
     *
     * @param integer $idVelo
     *
     * @return Location
     */
    public function setIdVelo($idVelo)
    {
        $this->idVelo = $idVelo;

        return $this;
    }

    /**
     * Get idVelo
     *
     * @return int
     */
    public function getIdVelo()
    {
        return $this->idVelo;
    }

    /**
     * Set dateDebut
     *
     * @param Date $dateDebut
     *
     * @return Location
     */
    public function setDateDebut($dateDebut)
    {
        $this->dateDebut = $dateDebut;

        return $this;
    }

    /**
     * Get dateDebut
     *
     * @return Date
     */
    public function getDateDebut()
    {
        return $this->dateDebut;
    }

    /**
     * Set dateFin
     *
     * @param DateTime $dateFin
     *
     * @return Location
     */
    public function setDateFin($dateFin)
    {
        $this->dateFin = $dateFin;

        return $this;
    }

    /**
     * Get dateFin
     *
     * @return DateTime
     */
    public function getDateFin()
    {
        return $this->dateFin;
    }

    public function getAllDayEvent()
    {
        return false;
    }
}

