package edu.umg.Interfaz;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import edu.umg.EstudiantesClass;

import java.awt.*;
import java.util.List;

public class FrEstudiantes extends JFrame {
    private DefaultTableModel tableModel;

    public FrEstudiantes() {
        setTitle("Estudiantes");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // Crear un DefaultTableModel y JTable
        tableModel = new DefaultTableModel();
        JTable studentsTable = new JTable(tableModel);

        // Agregar la JTable a un JScrollPane para la visualización de la tabla
        JScrollPane scrollPane = new JScrollPane(studentsTable);
        add(scrollPane);

        // Conectar a la base de datos y obtener datos iniciales
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();

        // Agregar las columnas a la tabla
        tableModel.addColumn("ID");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Apellido");
        tableModel.addColumn("Email");

        // Cargar datos iniciales desde la base de datos
        List<EstudiantesClass> studentsList = session.createQuery("FROM EstudiantesClass").list();

        for (EstudiantesClass student : studentsList) {
            tableModel.addRow(new Object[]{student.getIdEstudiante(), student.getNombre(), student.getApellido(), student.getEmail()});
        }

        session.close();

        // Crear un temporizador para actualizar la tabla cada cierto tiempo
        Timer timer = new Timer(5000, e -> {
            Session newSession = sessionFactory.openSession();
            List<EstudiantesClass> updatedStudentsList = newSession.createQuery("FROM EstudiantesClass").list();

            // Borrar todos los datos de la tabla
            for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
                tableModel.removeRow(i);
            }

            // Agregar los datos actualizados
            for (EstudiantesClass student : updatedStudentsList) {
                tableModel.addRow(new Object[]{student.getIdEstudiante(), student.getNombre(), student.getApellido(), student.getEmail()});
            }

            newSession.close();
        });

        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrEstudiantes frame = new FrEstudiantes();
            frame.setVisible(true);
        });
    }
}
