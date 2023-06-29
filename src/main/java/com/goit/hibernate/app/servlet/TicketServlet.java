package com.goit.hibernate.app.servlet;

import com.goit.hibernate.app.configuration.Environment;
import com.goit.hibernate.app.configuration.hibernate.Datasource;
import com.goit.hibernate.app.dto.PlanetDto;
import com.goit.hibernate.app.dto.TicketDto;
import com.goit.hibernate.app.entity.Planet;
import com.goit.hibernate.app.entity.Ticket;
import com.goit.hibernate.app.mapper.PlanetDtoMapper;
import com.goit.hibernate.app.mapper.PlanetEntityMapper;
import com.goit.hibernate.app.mapper.TicketDtoMapper;
import com.goit.hibernate.app.repository.PlanetEntityRepository;
import com.goit.hibernate.app.repository.TicketEntityRepository;
import com.goit.hibernate.app.servlet.exception.HibernateAppBadRequestException;
import com.goit.hibernate.app.servlet.exception.HibernateAppNotFoundException;
import com.goit.hibernate.app.servlet.exception.handler.ServletExceptionHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

import static com.goit.hibernate.app.servlet.ServletUtils.*;
import static com.goit.hibernate.app.util.Constants.APP_ENV;
import static java.util.Objects.isNull;

public class TicketServlet extends HttpServlet {

    private TicketEntityRepository ticketEntityRepository;
    private TicketDtoMapper ticketDtoMapper;
    private Gson gson;

    @Override
    public void init(ServletConfig config) {
        gson = new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .create();
        Environment environment = (Environment) config.getServletContext().getAttribute(APP_ENV);
        ticketEntityRepository = new TicketEntityRepository(new Datasource(environment));
        ticketDtoMapper = TicketDtoMapper.instance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        ServletExceptionHandler.builder()
                .servletResponse(response)
                .action(() -> {
                    List<TicketDto> ticketDtos = resolveNumericPathVariable(request.getRequestURI())
                            .map(id -> {
                                Ticket entity = ticketEntityRepository.findById(id);
                                validateIsTicketExists(id, entity);
                                return List.of(ticketDtoMapper.mapEntityToDto(entity));
                            }).orElse(ticketDtoMapper.mapEntityToDto(ticketEntityRepository.findAll()));
                    sendJsonResponse(response, ticketDtos);
                })
                .build()
                .doAction();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
/*
        ServletExceptionHandler.builder()
                .servletResponse(response)
                .action(() -> {
                    String json = new String(request.getInputStream().readAllBytes());
                    PlanetDto planetDto = gson.fromJson(json, PlanetDto.class);
                    Planet entity = dtoMapper.map(planetDto);
                    Planet saved = planetEntityRepository.save(entity);
                    PlanetDto mapped = entityMapper.map(saved);
                    sendJsonResponse(response, mapped);
                })
                .build()
                .doAction();
*/
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
/*
        ServletExceptionHandler.builder()
                .servletResponse(response)
                .action(() -> {
                    String json = new String(request.getInputStream().readAllBytes());
                    PlanetDto planetDto = gson.fromJson(json, PlanetDto.class);
                    validatePlanet(planetDto);
                    Planet entity = dtoMapper.map(planetDto);
                    Planet saved = planetEntityRepository.save(entity);
                    PlanetDto mapped = entityMapper.map(saved);
                    sendJsonResponse(response, mapped);
                })
                .build()
                .doAction();
*/
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
/*
        ServletExceptionHandler.builder()
                .servletResponse(response)
                .action(() -> {
                    resolveNumericPathVariable(request.getRequestURI())
                            .map(id -> planetEntityRepository.deleteById(id.toString()));
                    response.setStatus(HttpCode.OK);
                })
                .build()
                .doAction();
*/
    }

    private static void validateTicket(TicketDto ticketDto) {
        if (isNull(ticketDto.getId())) {
            throw new HibernateAppBadRequestException("Ticket id is required");
        }
    }

    private static void validateIsTicketExists(Long id, Ticket entity) {
        if (isNull(entity)) {
            throw new HibernateAppNotFoundException("Ticket with id:" + id + " not found");
        }
    }
}
