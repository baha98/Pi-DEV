<?php

namespace BlogBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;

class PostType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('titre',null,array('label' => 'Titre'))
            ->add('description',null,array('label' => 'Description'))
            ->add('image',FileType::class,array(
                'label' => 'choisir une image'
            ))
            ->add('dateActualite', DateType::class,array(
                'label' => 'Date Actualité',
                'widget' => 'single_text',
                // this is actually the default format for single_text
                'format' => 'yyyy-MM-dd',
            ))
            ->add('save', SubmitType::class, [
            'attr' => ['class' => 'btn btn-theme'],
            ])
            ->add('categorie',ChoiceType::class,[
                'expanded' => true,
                'multiple' => false,
                'choice_attr' => function($choiceValue, $key, $value) {
                    // adds a class like attending_yes, attending_no, etc
                    return ['class' => 'checkbox form-control'];},
                'choices'  => [
                    'Route' => 'Route',
                    'Montagne' => 'Montagne',
                    'Championat' => 'Championat',
                ],
                'label' => 'Categorie']);
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'BlogBundle\Entity\Post'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'blogbundle_post';
    }


}
