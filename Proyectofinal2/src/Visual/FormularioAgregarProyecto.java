package Visual;

import javax.swing.*;
import java.awt.*;
import Conector.ProyectoDAO;
import Conector.ConexionDB;
import Logica.ProyectoSoftware;
import java.sql.Connection;

public class FormularioAgregarProyecto extends JDialog {

    public FormularioAgregarProyecto() {
        setTitle("Agregar Proyecto");
        ImageIcon icono = new ImageIcon(getClass().getResource("/recursos/logoLapiz.jpg"));
        setIconImage(icono.getImage());
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblNombre = new JLabel("Nombre del Proyecto:");
        JTextField txtNombre = new JTextField(20);
        JButton btnGuardar = new BotonAnimado("Guardar Proyecto");

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblNombre, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnGuardar, gbc);

        btnGuardar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();

            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, escribe el nombre del proyecto.");
                return;
            }

            ProyectoSoftware proyecto = new ProyectoSoftware(nombre);

            try {
                Connection conn = ConexionDB.obtenerConexion();
                ProyectoDAO dao = new ProyectoDAO(conn);
                boolean exito = dao.insertarProyecto(proyecto);

                if (exito) {
                    JOptionPane.showMessageDialog(this, "Proyecto guardado exitosamente.");
                    txtNombre.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo guardar el proyecto.");
                }

                conn.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al conectar: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
    }
}
