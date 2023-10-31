package edu.umg.Interfaz;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FrLobby extends JFrame {


    public FrLobby() {
        setTitle("Lobby");
        setSize(550, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\regin\\IdeaProjects\\Proyeto Final Inscripcion Omega\\src\\main\\java\\Imagenes\\LobbyImagen.png");

        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setPreferredSize(new Dimension(getWidth(), getHeight()));
        setContentPane(backgroundLabel);

        JButton btagregar = new JButton("");
        btagregar.setBounds(53, 145, 160, 250);
        btagregar.setBorder(new EmptyBorder(0, 0, 0, 0));
        btagregar.setOpaque(false); // Establece la opacidad en cero
        btagregar.setContentAreaFilled(false); // No pinta el área del contenido
        btagregar.setBorderPainted(false); // No pinta el borde


        JButton btconsultar = new JButton("");
        btconsultar.setBounds(340, 145, 160, 250);
        btconsultar.setBorder(new EmptyBorder(0, 0, 0, 0));
        btconsultar.setOpaque(false); // Establece la opacidad en cero
        btconsultar.setContentAreaFilled(false); // No pinta el área del contenido
        btconsultar.setBorderPainted(false); // No pinta el borde


        btagregar.addActionListener(e -> {
            SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

            // Crea una instancia de FrInscripcion y pasa el SessionFactory
            FrInscripcion inscripcion = new FrInscripcion(sessionFactory);

            // Mostrar la ventana de inscripción

            sessionFactory = null;
            FrInscripcion frame = new FrInscripcion(sessionFactory);
            frame.setVisible(true);
        });

        btconsultar.addActionListener(e -> {
            FrConsulta frame = new FrConsulta();
            frame.setVisible(true);
        });

        add(btagregar);
        add(btconsultar);
    }

    public static void main(String[] args)
    {
        FrLobby frame = new FrLobby();
        frame.setVisible(true);
    }

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
// Utiliza sessionFactory para realizar operaciones de la base de datos


}
