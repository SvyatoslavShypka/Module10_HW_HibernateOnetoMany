package com.goit.hibernate.app;

import com.goit.hibernate.app.configuration.Environment;
import com.goit.hibernate.app.configuration.LoggingConfiguration;
import com.goit.hibernate.app.configuration.hibernate.Datasource;
import com.goit.hibernate.app.repository.ClientEntityRepository;
import com.goit.hibernate.app.repository.PlanetEntityRepository;
import com.goit.hibernate.app.repository.TicketEntityRepository;
import com.goit.hibernate.app.service.ClientCrudService;
import com.goit.hibernate.app.service.PlanetCrudService;
import com.goit.hibernate.app.service.TicketCrudService;
import jakarta.persistence.Entity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
@Slf4j
public class HibernateApplicationTest {

    protected Environment environment = Environment.load();;
    protected Datasource datasource = new Datasource(environment);
//    protected ClientCrudService clientCrudService;
//    protected PlanetCrudService planetCrudService;
//    protected TicketCrudService ticketCrudService;
    protected ClientCrudService clientCrudService = new ClientCrudService(environment,
        new ClientEntityRepository(datasource));
    protected PlanetCrudService planetCrudService = new PlanetCrudService(environment,
            new PlanetEntityRepository(datasource));
    protected TicketCrudService ticketCrudService = new TicketCrudService(environment,
            new TicketEntityRepository(datasource));


    @BeforeEach
    public void beforeEach() {
//        environment = Environment.load();
//        datasource = new Datasource(environment);
//        LoggingConfiguration.setup(environment);
        log.info("BeforeEachMain");

    }

    @AfterEach
    void afterEach() {
        log.info("afterEachMain");

    }
}
