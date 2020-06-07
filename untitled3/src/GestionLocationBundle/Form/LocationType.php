<?php

namespace GestionLocationBundle\Form;

use GestionProduitBundle\Entity\Velo;
use Symfony\Component\Form\Extension\Core\Type\CollectionType;
use GestionUser\Entity\User;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class LocationType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('dateDebut', DateType::class,array(
                    'label' => 'Date de Debut',
                    'translation_domain' => 'FOSUserBundle',
                    'widget' => 'single_text',
                    // this is actually the default format for single_text
                    'format' => 'yyyy-MM-dd',
                ))
                ->add('dateFin', DateType::class,array(
                    'label' => 'Date de Fin',
                    'translation_domain' => 'FOSUserBundle',
                    'widget' => 'single_text',
                    // this is actually the default format for single_text
                    'format' => 'yyyy-MM-dd',
                ));

    }
    /**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'GestionLocationBundle\Entity\Location'
        ));
    }
    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'gestionlocationbundle_location';
    }


}
