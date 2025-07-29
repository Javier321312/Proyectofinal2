package Visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {
    private JPanel contentPane;
    private JPasswordField passwordField;

    private final String CONTRASENA_CORRECTA = "Vortex"; 

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Login frame = new Login();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Login() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null); 

        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(30, 30, 100, 14);
        contentPane.add(lblContrasena);

        passwordField = new JPasswordField();
        passwordField.setBounds(30, 50, 260, 25);
        contentPane.add(passwordField);

        JButton btnLogin = new JButton("Ingresar");
        btnLogin.setBounds(110, 100, 100, 25);
        contentPane.add(btnLogin);

        
        Action ingresarAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pass = new String(passwordField.getPassword());
                if (pass.equals(CONTRASENA_CORRECTA)) {
                    Ventana ventana = new Ventana(); 
                    ventana.setVisible(true);
                    dispose(); 
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        btnLogin.addActionListener(ingresarAction);
        passwordField.addActionListener(ingresarAction); 
        
        getRootPane().setDefaultButton(btnLogin);
    }
}