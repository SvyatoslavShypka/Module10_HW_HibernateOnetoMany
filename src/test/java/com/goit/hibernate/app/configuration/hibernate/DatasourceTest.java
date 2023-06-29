package com.goit.hibernate.app.configuration.hibernate;

import com.goit.hibernate.app.HibernateApplicationTest;
import jakarta.persistence.Entity;
import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DatasourceTest extends HibernateApplicationTest {

    @Test
    void openSession() {
        //Given
        Session session = datasource.openSession();

        //When

        //Then
        Assertions.assertTrue(session.isOpen());

        session.close();
    }
}
