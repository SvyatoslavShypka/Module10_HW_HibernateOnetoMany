package com.goit.hibernate.app.service;

import com.goit.hibernate.app.HibernateApplicationTest;
import com.goit.hibernate.app.entity.Client;
import com.goit.hibernate.app.entity.Planet;
import com.goit.hibernate.app.entity.Ticket;
import com.goit.hibernate.app.repository.IncorrectDataException;
import com.goit.hibernate.app.test.utils.TestUtils;
import org.junit.jupiter.api.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class TicketCrudServiceTest extends HibernateApplicationTest {

    @Test
    void save() throws IncorrectDataException {
        //Given
        Ticket testTicket = TestUtils.createTestTicket();
        List<Client> allClient = clientCrudService.findAll();
        testTicket.setClient(allClient.get(0));
        List<Planet> allPlanet = planetCrudService.findAll();
        testTicket.setPlanetFrom(allPlanet.get(0));
        testTicket.setPlanetTo(allPlanet.get(1));

        //When
        Ticket savedTicket = ticketCrudService.save(testTicket);
        Ticket ticketById = ticketCrudService.findById(savedTicket.getId());

        //Then
        assertNotNull(savedTicket);
        assertEquals(testTicket.getId(), ticketById.getId());
    }
}
