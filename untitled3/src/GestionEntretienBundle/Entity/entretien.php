<?php

namespace GestionEntretienBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * entretien
 *
 * @ORM\Table(name="entretien")
 * @ORM\Entity(repositoryClass="GestionEntretienBundle\Repository\entretienRepository")
 */
class entretien
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @ORM\ManyToOne(targetEntity="GestionUser\Entity\User")
     */
    private $cycliste;

    /**
     * @ORM\ManyToOne(targetEntity="GestionProduitBundle\Entity\Velo")
     */
    private $velo;

    /**
     * @ORM\ManyToOne(targetEntity="GestionUser\Entity\User")
     */
    private $user;

    /**
     * @var string
     *
     * @ORM\Column(name="etat", type="string", length=255)
     */
    private $etat;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set cycliste
     *
     * @param string $cycliste
     *
     * @return entretien
     */
    public function setCycliste($cycliste)
    {
        $this->cycliste = $cycliste;

        return $this;
    }

    /**
     * Get cycliste
     *
     * @return string
     */
    public function getCycliste()
    {
        return $this->cycliste;
    }

    /**
     * Set velo
     *
     * @param string $velo
     *
     * @return entretien
     */
    public function setVelo($velo)
    {
        $this->velo = $velo;

        return $this;
    }

    /**
     * Get velo
     *
     * @return string
     */
    public function getVelo()
    {
        return $this->velo;
    }

    /**
     * Set user
     *
     * @param string $user
     *
     * @return entretien
     */
    public function setUser($user)
    {
        $this->user = $user;

        return $this;
    }

    /**
     * Get user
     *
     * @return string
     */
    public function getUser()
    {
        return $this->user;
    }

    /**
     * Set etat
     *
     * @param string $etat
     *
     * @return entretien
     */
    public function setEtat($etat)
    {
        $this->etat = $etat;

        return $this;
    }

    /**
     * Get etat
     *
     * @return string
     */
    public function getEtat()
    {
        return $this->etat;
    }
}

