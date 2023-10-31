package edu.umg.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import edu.umg.EstudiantesClass;

public class EstudiantesDao {

    private final SessionFactory sessionFactory;

    public EstudiantesDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public EstudiantesClass getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(EstudiantesClass.class, id);
        }
    }

    public void save(EstudiantesClass estudiante) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(estudiante); // Guardar un nuevo estudiante sin preocuparse por duplicados
            session.getTransaction().commit();
        }
    }


    public void delete(EstudiantesClass estudiante) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(estudiante);
            session.getTransaction().commit();
        }
    }

    // Otros métodos para realizar operaciones específicas, como obtener todos los estudiantes, etc.

}
