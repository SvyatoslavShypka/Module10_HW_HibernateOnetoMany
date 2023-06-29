package com.goit.hibernate.app.test.utils;

import com.goit.hibernate.app.entity.Client;
import com.goit.hibernate.app.entity.Planet;
import com.goit.hibernate.app.entity.Ticket;

import java.sql.Timestamp;

public final class TestUtils {

    public static Client createTestClient() {
        Client client = new Client();
        client.setName("Test Client");
        return client;
    }

    public static Planet createTestPlanet() {
        Planet planet = new Planet();
        planet.setId("SOLAR100");
        planet.setName("Test Planet");
        return planet;
    }

    public static Ticket createTestTicket() {
        Ticket ticket = new Ticket();
        ticket.setCreatedAt(Timestamp.valueOf("2030-01-01 10:00:00"));


        return ticket;
    }
}
