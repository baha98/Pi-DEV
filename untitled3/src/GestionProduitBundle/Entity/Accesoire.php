<?php

namespace GestionProduitBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Accesoire
 *
 * @ORM\Table(name="accesoire")
 * @ORM\Entity(repositoryClass="GestionProduitBundle\Repository\AccesoireRepository")
 */
class Accesoire extends Produit
{

    /**
     * @var string
     *
     * @ORM\Column(name="categorieAc", type="string", length=255)
     */
    private $categorieAc;


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
     * Set categorieAc
     *
     * @param string $categorieAc
     *
     * @return Accesoire
     */
    public function setCategorieAc($categorieAc)
    {
        $this->categorieAc = $categorieAc;

        return $this;
    }

    /**
     * Get categorieAc
     *
     * @return string
     */
    public function getCategorieAc()
    {
        return $this->categorieAc;
    }
}

