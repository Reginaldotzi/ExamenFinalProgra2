package edu.umg.Interfaz;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import edu.umg.CursosClass;

import java.awt.*;
import java.util.List;

public class FrProfesores extends JFrame {
    private DefaultTableModel tableModel;

    public FrProfesores() {
        setTitle("Profesores");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear un DefaultTableModel y JTable
        tableModel = new DefaultTableModel();
        JTable cursosTable = new JTable(tableModel);

        // Agregar la JTable a un JScrollPane para la visualización de la tabla
        JScrollPane scrollPane = new JScrollPane(cursosTable);
        add(scrollPane);

        // Conectar a la base de datos y obtener datos iniciales
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();

        // Agregar las columnas a la tabla
        tableModel.addColumn("ID");
        tableModel.addColumn("Nombre del Curso");
        tableModel.addColumn("Profesor");

        // Cargar datos iniciales desde la base de datos
        List<CursosClass> cursosList = session.createQuery("FROM CursosClass").list();

        for (CursosClass curso : cursosList) {
            tableModel.addRow(new Object[]{curso.getIdCurso(), curso.getNombreCurso(), curso.getProfesor()});
        }

        session.close();

        // Crear un temporizador para actualizar la tabla cada cierto tiempo
        Timer timer = new Timer(5000, e -> {
            Session newSession = sessionFactory.openSession();
            List<CursosClass> updatedCursosList = newSession.createQuery("FROM CursosClass").list();

            // Borrar todos los datos de la tabla
            for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
                tableModel.removeRow(i);
            }

            // Agregar los datos actualizados
            for (CursosClass curso : updatedCursosList) {
                tableModel.addRow(new Object[]{curso.getIdCurso(), curso.getNombreCurso(), curso.getProfesor()});
            }

            newSession.close();
        });

        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrProfesores frame = new FrProfesores();
            frame.setVisible(true);
        });
    }
}
