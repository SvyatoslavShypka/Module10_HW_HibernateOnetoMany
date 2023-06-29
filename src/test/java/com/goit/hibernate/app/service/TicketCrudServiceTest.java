package com.goit.hibernate.app.service;

import com.goit.hibernate.app.HibernateApplicationTest;
import com.goit.hibernate.app.entity.Client;
import com.goit.hibernate.app.entity.Planet;
import com.goit.hibernate.app.entity.Ticket;
import com.goit.hibernate.app.repository.IncorrectDataException;
import com.goit.hibernate.app.test.utils.TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TicketCrudServiceTest extends HibernateApplicationTest {

//    private TicketCrudService ticketCrudService;
//    private ClientCrudService clientCrudService;
//    private PlanetCrudService planetCrudService;


    @BeforeEach
    public void init() {
        log.info("BeforeEachInit");
//        ticketCrudService = new TicketCrudService(environment,
//                new TicketEntityRepository(datasource));
//        clientCrudService = new ClientCrudService(environment, new ClientEntityRepository(datasource));
//        planetCrudService = new PlanetCrudService(environment, new PlanetEntityRepository(datasource));
    }

    @Test
    @Order(1)
    void findAll() {
        //Given
        final int expectedSize = 3;

        //When
//        List<Client> allClient = clientCrudService.findAll();
//        List<Planet> allPlanet = planetCrudService.findAll();
        List<Ticket> all = ticketCrudService.findAll();

        //Then
        assertFalse(all.isEmpty());
        assertEquals(expectedSize, all.size());
    }

    @Test
    @Order(2)
    void findById() {
        //Given
        final long testId;

        List<Ticket> all = ticketCrudService.findAll();
        testId = all.get(0).getId();

        //When
        Ticket ticket = ticketCrudService.findById(testId);

        //Then
        assertNotNull(ticket);
        assertEquals(testId, ticket.getId());
    }

    @Test
    @Order(3)
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

    @Test
    @Order(4)
    void saveNull_Unknown_PlanetClient() {
        //Given
        Ticket testTicketNullClient = TestUtils.createTestTicket();
        Ticket testTicketUnknownClient = TestUtils.createTestTicket();
        Ticket testTicketNullPlanet = TestUtils.createTestTicket();
        Ticket testTicketUnknownPlanet = TestUtils.createTestTicket();

        List<Client> allClient = clientCrudService.findAll();
        List<Planet> allPlanet = planetCrudService.findAll();
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

    @Test
    @Order(5)
    void updateNewTicket() throws IncorrectDataException {
        //Given
        Ticket testTicket = TestUtils.createTestTicket();
        List<Client> allClient = clientCrudService.findAll();
        testTicket.setClient(allClient.get(0));
        List<Planet> allPlanet = planetCrudService.findAll();
        testTicket.setPlanetFrom(allPlanet.get(0));
        testTicket.setPlanetTo(allPlanet.get(1));

        //When
        Ticket savedTicket = ticketCrudService.update(testTicket);
        Ticket ticketById = ticketCrudService.findById(savedTicket.getId());

        //Then
        assertNotNull(savedTicket);
        assertEquals(testTicket.getId(), ticketById.getId());
    }

    @Test
    @Order(6)
    void updateExistedTicket() throws IncorrectDataException {
        //Given
        List<Ticket> allTickets = ticketCrudService.findAll();
        Ticket testTicket = allTickets.get(0);
        testTicket.setCreatedAt(Timestamp.valueOf("2024-01-01 00:00:01"));

        //When
        Ticket updatedTicket = ticketCrudService.update(testTicket);
        Long expectedId = updatedTicket.getId();
        Ticket ticketById = ticketCrudService.findById(updatedTicket.getId());

        //Then
        assertNotNull(updatedTicket);
        assertEquals(expectedId, ticketById.getId());
    }

    @Test
    @Order(7)
    void delete() {
        //Given
        int expectedDeletedCount = 1;

        //When
        List<Ticket> all = ticketCrudService.findAll();
        Ticket ticketToDelete = all.get(0);
        int deleteCount = ticketCrudService.delete(ticketToDelete);

        //Then
        assertEquals(expectedDeletedCount, deleteCount);
    }

    @Test
    @Order(8)
    void deleteById() {
        //Given
        int expectedDeletedCount = 1;

        //When
        List<Ticket> all = ticketCrudService.findAll();
        Long idToDelete = all.get(0).getId();
        int deleteCount = ticketCrudService.deleteById(idToDelete);

        //Then
        assertEquals(expectedDeletedCount, deleteCount);
        assertNull(ticketCrudService.findById(idToDelete));
    }
}
