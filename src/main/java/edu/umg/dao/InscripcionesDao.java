package edu.umg.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import edu.umg.InscripcionesClass;

public class InscripcionesDao {

    private final SessionFactory sessionFactory;

    public InscripcionesDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public InscripcionesClass getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(InscripcionesClass.class, id);
        }
    }

    public void saveOrUpdate(InscripcionesClass inscripcion) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(inscripcion);
            session.getTransaction().commit();
        }
    }

    public void delete(InscripcionesClass inscripcion) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(inscripcion);
            session.getTransaction().commit();
        }
    }

    // Otros métodos para realizar operaciones específicas, como obtener todas las inscripciones, etc.

}
