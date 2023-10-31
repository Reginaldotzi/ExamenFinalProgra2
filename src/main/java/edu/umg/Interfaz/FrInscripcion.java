package edu.umg.Interfaz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import edu.umg.EstudiantesClass;
import edu.umg.dao.EstudiantesDao;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FrInscripcion extends JFrame {

    private SessionFactory sessionFactory;
    private DefaultListModel<EstudiantesClass> estudiantesListModel;
    private JList<EstudiantesClass> estudiantesList;

    public FrInscripcion(SessionFactory sessionFactory, DefaultListModel<EstudiantesClass> estudiantesListModel) {
        this.sessionFactory = sessionFactory;
        this.estudiantesListModel = estudiantesListModel;

        // Inicialización y configuración de la ventana...
        setTitle("Inscripcion");
        setSize(420, 680);
        setLocationRelativeTo(null);

        // Crear un JPanel para agregar componentes
        JPanel panel = new JPanel();
        panel.setLayout(null);

        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\regin\\IdeaProjects\\Proyeto Final Inscripcion Omega\\src\\main\\java\\Imagenes\\InscripcionImagen.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setPreferredSize(new Dimension(getWidth(), getHeight()));
        setContentPane(backgroundLabel);

        JTextField nombreField = new JTextField();
        nombreField.setBounds(50, 250, 300, 30);
        panel.add(nombreField);

        JTextField apellidoField = new JTextField();
        apellidoField.setBounds(50, 400, 300, 30);
        panel.add(apellidoField);

        JTextField emailField = new JTextField();
        emailField.setBounds(50, 550, 300, 30);
        panel.add(emailField);

        // Botón de inscripción
        JButton inscribirButton = new JButton("");
        inscribirButton.setBounds(48, 600, 150, 40);
        inscribirButton.setBounds(48, 600, 150, 40);
        inscribirButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        inscribirButton.setOpaque(false); // Establece la opacidad en cero
        inscribirButton.setContentAreaFilled(false); // No pinta el área del contenido
        inscribirButton.setBorderPainted(false); // No pinta el borde
        panel.add(inscribirButton);

        inscribirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Manejar el evento de inscripción
                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();
                String email = emailField.getText();

                // Validar los datos ingresados si es necesario

                // Crear un objeto EstudiantesClass con los datos ingresados
                EstudiantesClass estudiante = new EstudiantesClass();
                estudiante.setIdEstudiante(5);
                estudiante.setNombre(nombre);
                estudiante.setApellido(apellido);
                estudiante.setEmail(email);

                // Guardar el estudiante en la base de datos
                EstudiantesDao estudiantesDao = new EstudiantesDao(FrInscripcion.this.sessionFactory);
                estudiantesDao.save(estudiante);

                // Agregar el estudiante a la lista
                estudiantesListModel.addElement(estudiante);

                // Limpiar los campos de texto
                nombreField.setText("");
                apellidoField.setText("");
                emailField.setText("");

                // Presentar un mensaje de confirmación
                JOptionPane.showMessageDialog(FrInscripcion.this, "Inscripción exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Botón de cancelar
        JButton cancelarButton = new JButton("");
        cancelarButton.setBounds(220, 600, 150, 40);
        cancelarButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        cancelarButton.setOpaque(false); // Establece la opacidad en cero
        cancelarButton.setContentAreaFilled(false); // No pinta el área del contenido
        cancelarButton.setBorderPainted(false); // No pinta el borde
        panel.add(cancelarButton);

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Manejar el evento de cancelar
                dispose(); // Cierra la ventana de inscripción
            }
        });

        // Crear una lista de estudiantes
        estudiantesList = new JList<>(estudiantesListModel);
        estudiantesList.setBounds(50, 50, 300, 150);
        panel.add(estudiantesList);

        add(panel);
        setLayout(null);
        add(inscribirButton);
        add(cancelarButton);
        add(nombreField);
        add(apellidoField);
        add(emailField);
    }

    public FrInscripcion(SessionFactory sessionFactory) {
    }

    public static void main(String[] args) {
        // Configura el SessionFactory de Hibernate
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        DefaultListModel<EstudiantesClass> estudiantesListModel = new DefaultListModel<>();
        FrInscripcion frame = new FrInscripcion(sessionFactory, estudiantesListModel);
        frame.setVisible(true);
    }



}
