package Visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {
    private JPanel contentPane;
    private JPasswordField passwordField;
    private JTextField userField; 
    private final String CONTRASENA_CORRECTA = "Vortex"; 
    private final String USUARIO1 = "Javier";  
    private final String USUARIO2 = "Narciso"; 

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
        setResizable(false);
        setLocationRelativeTo(null); 

        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(30, 30, 100, 14);
        contentPane.add(lblUsuario);

        userField = new JTextField();
        userField.setBounds(30, 50, 260, 25);
        contentPane.add(userField);
        userField.setColumns(10);

         JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(30, 80, 100, 14);
        contentPane.add(lblContrasena);

        passwordField = new JPasswordField();
        passwordField.setBounds(30, 100, 260, 25);
        contentPane.add(passwordField);

        JButton btnLogin = new JButton("Ingresar");
        btnLogin.setBounds(110, 140, 100, 25);
        contentPane.add(btnLogin);

        Action ingresarAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = userField.getText();                   String pass = new String(passwordField.getPassword());   

                if ((usuario.equals(USUARIO1) || usuario.equals(USUARIO2)) && pass.equals(CONTRASENA_CORRECTA)) {
                    Ventana ventana = new Ventana(); 
                    ventana.setVisible(true);
                    dispose();  
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        btnLogin.addActionListener(ingresarAction);
        passwordField.addActionListener(ingresarAction); 
        userField.addActionListener(ingresarAction); ¡
        getRootPane().setDefaultButton(btnLogin);   
    }
}
