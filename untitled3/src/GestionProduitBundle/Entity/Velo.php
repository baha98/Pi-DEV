<?php

namespace GestionProduitBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Velo
 *
 * @ORM\Table(name="velo")
 * @ORM\Entity(repositoryClass="GestionProduitBundle\Repository\VeloRepository")
 */
class Velo extends Produit
{
    /**
     * @var string
     *
     * @ORM\Column(name="Etat", type="string", length=255)
     */
    private $etat;

    /**
     * @ORM\Column(type="string" ,length=255)
     */
    protected $CategorieVelo;


    /**
     * Set etat
     *
     * @param string $etat
     *
     * @return Velo
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

    /**
     * @return mixed
     */
    public function getCategorieVelo()
    {
        return $this->CategorieVelo;
    }

    /**
     * @param mixed $CategorieVelo
     */
    public function setCategorieVelo($CategorieVelo): void
    {
        $this->CategorieVelo = $CategorieVelo;
    }


}

