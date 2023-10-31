package edu.umg.dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import edu.umg.CursosClass;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CursosDao {

    private final SessionFactory sessionFactory;

    public CursosDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public CursosClass getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(CursosClass.class, id);
        }
    }

    public void saveOrUpdate(CursosClass curso) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(curso);
            session.getTransaction().commit();
        }
    }

    public void delete(CursosClass curso) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(curso);
            session.getTransaction().commit();
        }
    }

    public void addCurso(String nombreCurso, String profesor) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            // Consultar el ID más alto actual
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Integer> query = builder.createQuery(Integer.class);
            Root<CursosClass> root = query.from(CursosClass.class);
            query.select(builder.max(root.get("idCurso")));
            Integer maxId = session.createQuery(query).uniqueResult();

            int newId = (maxId != null) ? maxId + 1 : 1; // Incrementar el ID

            CursosClass nuevoCurso = new CursosClass();
            nuevoCurso.setIdCurso(newId); // Establecer el ID
            nuevoCurso.setNombreCurso(nombreCurso);
            nuevoCurso.setProfesor(profesor);

            session.save(nuevoCurso);
            session.getTransaction().commit();
        }
    }


    public boolean existsCurso(int idCurso) {
        try (Session session = sessionFactory.openSession()) {
            CursosClass curso = session.get(CursosClass.class, idCurso);
            return curso != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void addCurso(CursosClass curso1) {
    }


    // Otros métodos para realizar operaciones específicas, como obtener todos los cursos, etc.

}
