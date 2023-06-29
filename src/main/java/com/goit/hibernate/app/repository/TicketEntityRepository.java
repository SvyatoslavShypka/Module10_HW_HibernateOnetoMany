package com.goit.hibernate.app.repository;

import com.goit.hibernate.app.configuration.hibernate.Datasource;
import com.goit.hibernate.app.entity.Ticket;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
public class TicketEntityRepository {

    private final Datasource datasource;

    public TicketEntityRepository(Datasource datasource) {
        this.datasource = datasource;
    }

    public List<Ticket> findAll() {
        return dbCall(session -> session
                .createQuery("select c from Ticket c", Ticket.class)
                .getResultList());
    }

    public Ticket findById(Long id) {
        return dbCall(session -> {
            String queryString = "select c from Ticket c where c.id=:id";
            Query<Ticket> query = session.createQuery(queryString, Ticket.class);
            query.setParameter("id", id);
            Ticket result;
            try {
                result = query.getSingleResult();
            } catch (Exception e) {
                log.warn("no results found", e);
                result = null;
            }
            return result;
        });
    }

    public Ticket save(Ticket entity) throws IncorrectDataException {
        dbVoidCall(session -> persist(entity, session));
        return entity;
    }

    public Ticket update(Ticket entity) throws IncorrectDataException {
        dbVoidCall(session -> merge(entity, session));
        return entity;
    }

    public int delete(Ticket entity) {
        Long id = entity.getId();
        return deleteById(id);
    }

    public int deleteById(Long id) {
        return dbCall(session -> {
            String queryString = "delete from Ticket c where c.id=:id";
            MutationQuery mutationQuery = session.createMutationQuery(queryString);
            mutationQuery.setParameter("id", id);
            return mutationQuery.executeUpdate();
        });
    }

    private Ticket persist(Ticket entity, Session session) {
        Ticket saved = session.merge(entity);
        entity.setId(saved.getId());
        return entity;
    }

    private Ticket merge(Ticket entity, Session session) {
        Ticket saved = session.merge(entity);
        entity.setId(saved.getId());
        return entity;
    }

    private <R> R dbCall(Function<Session, R> function) {
        try (Session session = datasource.openSession()) {
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        } catch (Exception e) {
            log.error("db execution failed", e);
            throw new RuntimeException(e);
        }
    }

    private void dbVoidCall(Consumer<Session> function) throws IncorrectDataException {
        try (Session session = datasource.openSession()) {
            Transaction transaction = session.beginTransaction();
            function.accept(session);
            transaction.commit();
        } catch (Exception e) {
            log.error("db execution failed", e);
            throw new IncorrectDataException("db execution failed");
        }
    }
}