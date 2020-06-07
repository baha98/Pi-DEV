<?php
// src/AppBundle/Form/RegistrationType.php

namespace GestionUser\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\Form\Extension\Core\Type\DateType;


class ProfileEditType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('nom_membre',null,array('label' => 'Nom membre', 'translation_domain' => 'FOSUserBundle'))
            ->add('prenom_membre',null,array('label' => 'Prénom membre', 'translation_domain' => 'FOSUserBundle'))
            ->add('dateNais_membre', DateType::class,array(
                'label' => 'Date de naissance',
                'translation_domain' => 'FOSUserBundle',
                'widget' => 'single_text',
                // this is actually the default format for single_text
                'format' => 'yyyy-MM-dd',
            ))
            ->add('NumTel_Membre',null,array('label' => 'Numéro de telphone', 'translation_domain' => 'FOSUserBundle'))
            ->add('Adresse_membre',null,array('label' => 'Adresse', 'translation_domain' => 'FOSUserBundle'));
    }

    public function getParent()
    {
        return 'FOS\UserBundle\Form\Type\ProfileFormType';
    }

    public function getBlockPrefix()
    {
        return 'app_user_profile';
    }

    // For Symfony 2.x
    public function getName()
    {
        return $this->getBlockPrefix();
    }
}