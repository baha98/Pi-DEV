<?php

namespace BlogBundle\Controller;

use BlogBundle\Entity\Liked;
use BlogBundle\Entity\Post;
use BlogBundle\Form\PostType;
use GestionUser\Entity\User;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;

class PostController extends Controller
{

    public function createPostAction(Request $request)
    {
        $post = new Post();
        $em = $this->getDoctrine()->getManager();
        $form = $this->createForm(PostType::class,$post);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid())
        {

            $file = $post->getImage();
            $fileName = uniqid().'.'.$file->guessExtension();
            $file->move($this->getParameter('upload_directory'),$fileName);
            $post->setImage($fileName);
            $post->setIdUser($this->getUser());
            $post->setNbreJaime(0);
            $post->setNbreVus(0);
            var_dump($post);
            $em->persist($post);
            $em->flush();
            return $this->redirectToRoute('blog_show_back');
        }
        return $this->render('BlogBundle:Post:create_post.html.twig', array(
            'form' => $form->createView()
        ));
    }

    public function addPostAction(Request $request)
    {

    }
    public function showPostAction()
    {
        $blogs = new Post();
        $blogs = $this->getDoctrine()->getRepository(Post::class)->findAll();
        return $this->render('BlogBundle:Post:show_post.html.twig', array(
            'Blogs' => $blogs
        ));
    }


    public function updatePostAction($id,Request $request)
    {
        $post1 = new Post();
        $post1 = $this->getDoctrine()->getRepository(Post::class)->find($id);
        $post = new Post();
        $em = $this->getDoctrine()->getManager();
        $form = $this->createForm(PostType::class,$post);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid())
        {
            $post->setId($id);
            $file = $post->getImage();
            $fileName = uniqid().'.'.$file->guessExtension();
            $file->move($this->getParameter('upload_directory'),$fileName);
            $post1->setImage($fileName);
            $post1->setIdUser($this->getUser());
            $post1->setCategorie($post->getCategorie());
            $post1->setDateActualite($post->getDateActualite());
            $post1->setDescription($post->getDescription());
            $post1->setTitre($post->getTitre());
            $em->flush();
            $this->forward('BlogBundle:Post:showPostBack');
        }
        return $this->render('@Blog/Post/update_post.html.twig', array(
            'form' => $form->createView(),
            'post' => $post
        ));
    }


    public function deletePostAction($id)
    {
        $post = new Post();
        $em = $this->getDoctrine()->getManager();
        $post = $em->getRepository(Post::class )->find($id);
        $em->remove($post);
        $em->flush();
        return $this->forward('BlogBundle:Post:showPostBack');
    }

    public function showDetailsAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $blog = new Post();
        $like = new Liked();
        $blog = $this->getDoctrine()->getRepository(Post::class)->find($id);
        $liked = new Liked();
        $liked = $this->getDoctrine()->getRepository(Liked::class)->findBy(array(
            'idUser'=> $this->getUser(),
            'idPost'=> $blog->getId()
        ),array(),100,0);
        if($liked)
        {

        }
        else
        {

            $like->setIdPost($blog);
            $like->setIdUser($this->getUser());
            $like->setLikes(0);
            $em->persist($like);
        }
        $blog->setNbreVus($blog->getNbreVus()+1);
        $em->flush();

        return $this->render('@Blog/Post/details_post.html.twig', array(
            'liked' => $liked,
            'Blog' => $blog
        ));
    }
    public function pasaimerpostAction($id)
    {
        $blog = new Post();
        $like = new Liked();
        $blog = $this->getDoctrine()->getRepository(Post::class)->find($id);
        $user = $this->getDoctrine()->getRepository(User::class)->find($this->getUser());
        $em = $this->getDoctrine()->getManager();
        $blog->setNbreJaime($blog->getNbreJaime()-1);
        $liked = $this->getDoctrine()->getRepository(Liked::class)->findBy(array(
            'idUser'=> $this->getUser(),
            'idPost'=> $blog->getId()
        ),array(),100,0);
        if($liked)
        {
            $liked[0]->setLikes(0);
        }
        $em->flush();
        return $this->render('@Blog/Post/details_post.html.twig', array(
            'liked' => $liked,
            'Blog' => $blog
        ));
    }
    public function aimerpostAction($id)
    {
        $blog = new Post();
        $like = new Liked();
        $blog = $this->getDoctrine()->getRepository(Post::class)->find($id);
        $user = $this->getDoctrine()->getRepository(User::class)->find($this->getUser());
        $em = $this->getDoctrine()->getManager();
        $blog->setNbreJaime($blog->getNbreJaime()+1);
        $liked = $this->getDoctrine()->getRepository(Liked::class)->findBy(array(
            'idUser'=> $this->getUser(),
            'idPost'=> $blog->getId()
        ),array(),100,0);
        if($liked)
        {
            $liked[0]->setLikes(1);
        }
        $em->flush();
        return $this->render('@Blog/Post/details_post.html.twig', array(
            'liked' => $liked,
            'Blog' => $blog
        ));
    }
    public function showPostBackAction(Request $request)
    {
        $session = $request->getSession();
        $post = $this->getDoctrine()->getManager()->getRepository( Post::class )->findAll();
        $paginator=$this->get('knp_paginator');
        $result=$paginator->paginate($post,
            $request->query->getInt('page',1),
            $request->query->getInt('limit',5));
        return $this->render('@Blog/Post/show_post_back.html.twig', array(
            'user1' => $session->get('user'),
            'session' => $session,
            'Blogs' => $result
        ));
    }

}
