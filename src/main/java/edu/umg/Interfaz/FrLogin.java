package edu.umg.Interfaz;

import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrLogin extends JFrame {
    public FrLogin() {
        setTitle("Login");
        setSize(450, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\regin\\IdeaProjects\\Proyeto Final Inscripcion Omega\\src\\main\\java\\Imagenes\\ImagenLogin.png");

        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setPreferredSize(new Dimension(getWidth(), getHeight()));
        setContentPane(backgroundLabel);

        // Crea y coloca elementos en coordenadas específicas

        JButton btlogin = new JButton("Login");
        btlogin.setBounds(145, 650, 150, 35);

        final JTextField txtUsername = new JTextField();
        txtUsername.setBounds(40, 335, 325, 30);
        txtUsername.setForeground(Color.RED);
        txtUsername.setBorder(null);

        final JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBounds(40, 560, 325, 30);
        txtPassword.setForeground(Color.RED);
        txtPassword.setBorder(null);

        // Agregar acción al botón de login
        btlogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                char[] passwordChars = txtPassword.getPassword();
                String password = new String(passwordChars);

                // Aquí debes realizar la autenticación.
                // Por ejemplo, podrías comparar el username y la contraseña con un valor predeterminado.

                if (username.equals("Reggie") && password.equals("regi34")) {
                    dispose();
                    FrLobby frame = new FrLobby();
                    frame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(FrLogin.this, "No eres el admin");
                }
            }
        });

        // Asegúrate de que el layout del JFrame esté configurado como nulo
        setLayout(null);

        // Agrega los botones al JFrame
        add(btlogin);
        add(txtUsername);
        add(txtPassword);
    }

    public static void main(String[] args) {
        FrLogin frame = new FrLogin();
        frame.setVisible(true);
    }

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
// Utiliza sessionFactory para realizar operaciones de la base de datos



}
