package Visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import Conector.ClienteDAO;
import Logica.Cliente;
import Conector.ConexionDB;

public class FormularioAgregarCliente extends JDialog {

    public FormularioAgregarCliente() {
        setTitle("Agregar Cliente");
        ImageIcon icono = new ImageIcon(getClass().getResource("/recursos/logoLapiz.jpg"));
        setIconImage(icono.getImage());
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblID = new JLabel("Identidad:");
        JTextField txtID = new JTextField(20);

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField(20);

        JLabel lblDireccion = new JLabel("Dirección:");
        JTextField txtDireccion = new JTextField(20);

        JLabel lblCantidadProyectos = new JLabel("Cantidad de proyectos:");
        JSpinner spinnerProyectos = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));

        JButton btnGuardar = new BotonAnimado("Guardar Cliente");
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblID, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        add(txtID, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblNombre, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblDireccion, gbc);
        gbc.gridx = 1;
        add(txtDireccion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblCantidadProyectos, gbc);
        gbc.gridx = 1;
        add(spinnerProyectos, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnGuardar, gbc);

        btnGuardar.addActionListener(e -> {
            String id = txtID.getText().trim();
            String nombre = txtNombre.getText().trim();
            String direccion = txtDireccion.getText().trim();
            int cantidadProyectos = (Integer) spinnerProyectos.getValue();

            if (id.isEmpty() || nombre.isEmpty() || direccion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.");
                return;
            }

            if (id.length() != 11 || !id.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "La identidad debe tener 11 dígitos numéricos.");
                return;
            }

            Cliente cliente = new Cliente(nombre, id, direccion);

            try {
                Connection conn = ConexionDB.obtenerConexion();
                ClienteDAO dao = new ClienteDAO(conn);
                boolean exito = dao.insertarCliente(cliente);

                if (exito) {
                    JOptionPane.showMessageDialog(this, "Cliente guardado exitosamente.");
                    txtID.setText("");
                    txtNombre.setText("");
                    txtDireccion.setText("");
                    spinnerProyectos.setValue(1);
                } else {
                    JOptionPane.showMessageDialog(this, "Error al guardar el cliente.");
                }

                conn.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error de conexión: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

    }
}

