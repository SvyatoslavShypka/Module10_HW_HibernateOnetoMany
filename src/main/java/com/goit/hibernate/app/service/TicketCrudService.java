package com.goit.hibernate.app.service;

import com.goit.hibernate.app.configuration.Environment;
import com.goit.hibernate.app.configuration.hibernate.Datasource;
import com.goit.hibernate.app.entity.Client;
import com.goit.hibernate.app.entity.Ticket;
import com.goit.hibernate.app.repository.ClientEntityRepository;
import com.goit.hibernate.app.repository.IncorrectDataException;
import com.goit.hibernate.app.repository.TicketEntityRepository;

import java.util.List;

public class TicketCrudService {

    private final Environment environment;
    private final TicketEntityRepository ticketEntityRepository;

    public TicketCrudService() {
        environment = Environment.load();
        ticketEntityRepository = new TicketEntityRepository(new Datasource(environment));
    }

    public TicketCrudService(Environment environment, TicketEntityRepository ticketEntityRepository) {
        this.environment = environment;
        this.ticketEntityRepository = ticketEntityRepository;
    }

    public List<Ticket> findAll() {
        return ticketEntityRepository.findAll();
    }

    public Ticket findById(Long id) {
        return ticketEntityRepository.findById(id);
    }

    public Ticket save(Ticket entity) throws IncorrectDataException {
        return ticketEntityRepository.save(entity);
    }

    public Ticket update(Ticket entity) throws IncorrectDataException {
        return ticketEntityRepository.update(entity);
    }

    public int delete(Ticket entity) {
        return ticketEntityRepository.delete(entity);
    }

    public int deleteById(Long id) {
        return ticketEntityRepository.deleteById(id);
    }
}
