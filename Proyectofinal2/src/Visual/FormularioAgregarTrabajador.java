package Visual;

import javax.swing.*;
import java.awt.*;

public class FormularioAgregarTrabajador extends JDialog {

    public FormularioAgregarTrabajador(JFrame parent) {
        super(parent, "Añadir Nuevo Trabajador", true);
        setSize(500, 500);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(12, 2, 5, 5));

        JTextField campoID = new JTextField();
        JTextField campoNombre = new JTextField();
        JTextField campoDireccion = new JTextField();
        JTextField campoEdad = new JTextField();
        JTextField campoProyecto = new JTextField();
        JTextField campoLenguaje = new JTextField();
        JTextField campoCantidad = new JTextField();
        JTextField campoFrecuencia = new JTextField();      
        JLabel labelLenguaje = new JLabel("Lenguaje:");
        JLabel labelCantidad = new JLabel("Cantidad Trabajadores:");
        JLabel labelFrecuencia = new JLabel("Frecuencia (días):");

        JComboBox<String> comboSexo = new JComboBox<>(new String[]{"Masculino", "Femenino"});
        JComboBox<String> comboRol = new JComboBox<>(new String[]{"Diseñador", "Programador", "Jefe de Proyecto", "Planificador"});

        JButton botonGuardar = new BotonAnimado("Guardar");

        add(new JLabel("ID:"));
        add(campoID);

        add(new JLabel("Nombre:"));
        add(campoNombre);

        add(new JLabel("Dirección:"));
        add(campoDireccion);

        add(new JLabel("Edad:"));
        add(campoEdad);

        add(new JLabel("Sexo:"));
        add(comboSexo);

        add(new JLabel("Rol:"));
        add(comboRol);
       
        add(new JLabel("Proyecto:"));
        add(campoProyecto);
        
        add(labelLenguaje); 
        add(campoLenguaje);
        add(labelCantidad);   
        add(campoCantidad);
        add(labelFrecuencia); 
        add(campoFrecuencia);


        add(new JLabel(""));
        add(botonGuardar);
        
        ocultarCamposEspeciales(labelLenguaje, campoLenguaje, labelCantidad, campoCantidad, labelFrecuencia, campoFrecuencia);
        
        comboRol.addActionListener(e -> {
            ocultarCamposEspeciales(labelLenguaje, campoLenguaje, labelCantidad, campoCantidad, labelFrecuencia, campoFrecuencia);
            String rolSeleccionado = (String) comboRol.getSelectedItem();

            if (rolSeleccionado.equals("Programador")) {
                labelLenguaje.setVisible(true);
                campoLenguaje.setVisible(true);
            } else if (rolSeleccionado.equals("Jefe de Proyecto")) {
                labelCantidad.setVisible(true);
                campoCantidad.setVisible(true);
            } else if (rolSeleccionado.equals("Planificador")) {
                labelFrecuencia.setVisible(true);
                campoFrecuencia.setVisible(true);
            }
        });
        
        botonGuardar.addActionListener(e -> {
            String nombre = campoNombre.getText();
            JOptionPane.showMessageDialog(this, "Trabajador '" + nombre + "' registrado (simulado).");
            dispose(); 
        });
    }
    private void ocultarCamposEspeciales(
            JLabel l1, JTextField f1,
            JLabel l2, JTextField f2,
            JLabel l3, JTextField f3
        ) {
            l1.setVisible(false);
            f1.setVisible(false);
            l2.setVisible(false);
            f2.setVisible(false);
            l3.setVisible(false);
            f3.setVisible(false);
    }
}

