package edu.umg.Interfaz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrConsulta extends JFrame {
    public FrConsulta()
    {
        setTitle("Consulta");
        setSize(420, 500);
        setLocationRelativeTo(null);


        // Crear un JPanel para agregar componentes
        JPanel panel = new JPanel();
        panel.setLayout(null);

        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\regin\\IdeaProjects\\Proyeto Final Inscripcion Omega\\src\\main\\java\\Imagenes\\ConsultaImagen.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setPreferredSize(new Dimension(getWidth(), getHeight()));
        setContentPane(backgroundLabel);

        JButton EstudiantesButton = new JButton("");
        EstudiantesButton.setBounds(20, 155, 370, 135);
        EstudiantesButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        EstudiantesButton.setOpaque(false); // Establece la opacidad en cero
        EstudiantesButton.setContentAreaFilled(false); // No pinta el área del contenido
        EstudiantesButton.setBorderPainted(false); // No pinta el borde
        panel.add(EstudiantesButton);

        EstudiantesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrEstudiantes consultaEstudiantes = new FrEstudiantes();
                consultaEstudiantes.setVisible(true);
            }
                // Lógica para abrir la ventana de consulta de estudiantes
            }
        );

        JButton ProfesoresButton = new JButton("");
        ProfesoresButton.setBounds(20, 300, 370, 135);
        ProfesoresButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        ProfesoresButton.setOpaque(false); // Establece la opacidad en cero
        ProfesoresButton.setContentAreaFilled(false); // No pinta el área del contenido
        ProfesoresButton.setBorderPainted(false); // No pinta el borde
        panel.add(ProfesoresButton);

        ProfesoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrProfesores consultaProfesores = new FrProfesores();
                consultaProfesores.setVisible(true);
            }


        });


        add(EstudiantesButton);
        add(ProfesoresButton);


    }

    public static void main(String[] args)
    {
        FrConsulta frame = new FrConsulta();
        frame.setVisible(true);
    }

}
