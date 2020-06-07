<?php

namespace GestionLocationBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class LocationControllerTest extends WebTestCase
{
    public function testAddlocation()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/addLocation');
    }

}
