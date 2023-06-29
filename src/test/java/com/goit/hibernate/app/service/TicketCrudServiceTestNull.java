package com.goit.hibernate.app.service;

import com.goit.hibernate.app.HibernateApplicationTest;
import com.goit.hibernate.app.entity.Client;
import com.goit.hibernate.app.entity.Planet;
import com.goit.hibernate.app.entity.Ticket;
import com.goit.hibernate.app.repository.IncorrectDataException;
import com.goit.hibernate.app.test.utils.TestUtils;
import org.junit.jupiter.api.*;
import java.util.List;

class TicketCrudServiceTestNull extends HibernateApplicationTest {

    @Test
    void saveNull_Unknown_PlanetClient() {
        //Given
        Ticket testTicketNullClient = TestUtils.createTestTicket();
        Ticket testTicketUnknownClient = TestUtils.createTestTicket();
        Ticket testTicketNullPlanet = TestUtils.createTestTicket();
        Ticket testTicketUnknownPlanet = TestUtils.createTestTicket();

        List<Ticket> allTicket = ticketCrudService.findAll();
        Ticket exampleTicket = allTicket.get(0);
        exampleTicket.setId(null);
        testTicketNullPlanet.setClient(exampleTicket.getClient());
        testTicketNullPlanet.setCreatedAt(exampleTicket.getCreatedAt());
        testTicketNullPlanet.setPlanetFrom(exampleTicket.getPlanetFrom());
        testTicketNullPlanet.setPlanetTo(null);

        testTicketNullClient.setClient(null);
        testTicketNullClient.setCreatedAt(exampleTicket.getCreatedAt());
        testTicketNullClient.setPlanetFrom(exampleTicket.getPlanetFrom());
        testTicketNullClient.setPlanetTo(exampleTicket.getPlanetTo());

        testTicketUnknownClient.setClient(new Client());
        testTicketUnknownClient.setCreatedAt(exampleTicket.getCreatedAt());
        testTicketUnknownClient.setPlanetFrom(exampleTicket.getPlanetFrom());
        testTicketUnknownClient.setPlanetTo(exampleTicket.getPlanetTo());

        testTicketUnknownPlanet.setClient(exampleTicket.getClient());
        testTicketUnknownPlanet.setCreatedAt(exampleTicket.getCreatedAt());
        testTicketUnknownPlanet.setPlanetFrom(exampleTicket.getPlanetFrom());
        testTicketUnknownPlanet.setPlanetTo(new Planet());

        //When

        //Then
        Assertions.assertThrows(IncorrectDataException.class,() -> {
            ticketCrudService.save(testTicketNullPlanet);
        });
        Assertions.assertThrows(IncorrectDataException.class,() -> {
            ticketCrudService.save(testTicketNullClient);
        });
        Assertions.assertThrows(IncorrectDataException.class,() -> {
            ticketCrudService.save(testTicketUnknownClient);
        });
        Assertions.assertThrows(IncorrectDataException.class,() -> {
            ticketCrudService.save(testTicketUnknownPlanet);
        });
    }
}
