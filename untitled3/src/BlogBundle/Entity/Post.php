<?php

namespace BlogBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Post
 *
 * @ORM\Table(name="post")
 * @ORM\Entity(repositoryClass="BlogBundle\Repository\PostRepository")
 */
class Post
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
     * @var string
     *
     * @ORM\Column(name="titre", type="string", length=255)
     */
    private $titre;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=2000)
     */
    private $description;

    /**
     * @var string
     *
     * @ORM\Column(name="Image", type="string", length=255)
     */
    private $image;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_actualite", type="datetime")
     */
    private $dateActualite;

    /**
     * @var int
     *
     * @ORM\Column(name="nbre_vus", type="integer")
     */
    private $nbreVus;

    /**
     * @var int
     *
     * @ORM\Column(name="nbre_jaime", type="integer")
     */
    private $nbreJaime;

    /**
     * @var string
     *
     * @ORM\Column(name="categorie", type="string", length=255)
     */
    private $categorie;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    public function setId($id)
    {
        $this->id = $id;
    }

    /**
     * Set titre
     *
     * @param string $titre
     *
     * @return Post
     */
    public function setTitre($titre)
    {
        $this->titre = $titre;

        return $this;
    }

    /**
     * Get titre
     *
     * @return string
     */
    public function getTitre()
    {
        return $this->titre;
    }

    /**
     * Set description
     *
     * @param string $description
     *
     * @return Post
     */
    public function setDescription($description)
    {
        $this->description = $description;

        return $this;
    }

    /**
     * Get description
     *
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * Set image
     *
     * @param string $image
     *
     * @return Post
     */
    public function setImage($image)
    {
        $this->image = $image;

        return $this;
    }

    /**
     * @ORM\ManyToOne(targetEntity="GestionUser\Entity\User")
     */
    private $idUser;

    /**
     * Get image
     *
     * @return string
     */
    public function getImage()
    {
        return $this->image;
    }

    /**
     * Set dateActualite
     *
     * @param \DateTime $dateActualite
     *
     * @return Post
     */
    public function setDateActualite($dateActualite)
    {
        $this->dateActualite = $dateActualite;

        return $this;
    }

    /**
     * Get dateActualite
     *
     * @return \DateTime
     */
    public function getDateActualite()
    {
        return $this->dateActualite;
    }

    /**
     * Set nbreVus
     *
     * @param integer $nbreVus
     *
     * @return Post
     */
    public function setNbreVus($nbreVus)
    {
        $this->nbreVus = $nbreVus;

        return $this;
    }

    /**
     * Get nbreVus
     *
     * @return int
     */
    public function getNbreVus()
    {
        return $this->nbreVus;
    }

    /**
     * Set nbreJaime
     *
     * @param integer $nbreJaime
     *
     * @return Post
     */
    public function setNbreJaime($nbreJaime)
    {
        $this->nbreJaime = $nbreJaime;

        return $this;
    }

    /**
     * Get nbreJaime
     *
     * @return int
     */
    public function getNbreJaime()
    {
        return $this->nbreJaime;
    }

    /**
     * Set categorie
     *
     * @param string $categorie
     *
     * @return Post
     */
    public function setCategorie($categorie)
    {
        $this->categorie = $categorie;

        return $this;
    }

    /**
     * Get categorie
     *
     * @return string
     */
    public function getCategorie()
    {
        return $this->categorie;
    }
    /**
     * Set idUser
     *
     * @param integer $idUser
     *
     * @return Post
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
}
