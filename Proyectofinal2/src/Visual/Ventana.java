package Visual;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class Ventana extends JFrame {
    private JTabbedPane tabbedPane;

    public Ventana() {
        setTitle("Prueba Visual de Proyecto Final de la Muchachada");
        ImageIcon icono = new ImageIcon(getClass().getResource("/recursos/icono.png"));
        setIconImage(icono.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 800);
        setLocationRelativeTo(null);
        setResizable(false);



        JMenuBar menuBar = new JMenuBar();

        JMenu menuTrabajadores = new JMenu("Gestion de Trabajadores");
        JMenuItem itemAgregarTrabajador = new JMenuItem("Agregar Trabajador");
        itemAgregarTrabajador.addActionListener(e -> {
            FormularioAgregarTrabajador dialogo = new FormularioAgregarTrabajador(this);
            dialogo.setVisible(true);
            setResizable(false);
        });

        menuTrabajadores.setOpaque(true);
        menuTrabajadores.setBackground(Color.WHITE);
        menuTrabajadores.setForeground(Color.BLACK);

        menuTrabajadores.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	menuTrabajadores.setBackground(new Color(200, 220, 255));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	menuTrabajadores.setBackground(Color.WHITE);
            }
        });
        menuTrabajadores.add(itemAgregarTrabajador);

        JMenu menuClientes = new JMenu("Gestion de Clientes");
        menuClientes.setOpaque(true);
        menuClientes.setBackground(Color.WHITE);
        menuClientes.setForeground(Color.BLACK);

        menuClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	menuClientes.setBackground(new Color(200, 220, 255));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	menuClientes.setBackground(Color.WHITE);
            }
        });
        menuClientes.add(new JMenuItem("Agregar Cliente"));
        JMenu menuProyectos = new JMenu("Contratos");
        menuProyectos.setOpaque(true);
        menuProyectos.setBackground(Color.WHITE);
        menuProyectos.setForeground(Color.BLACK);

        menuProyectos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	menuProyectos.setBackground(new Color(200, 220, 255));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	menuProyectos.setBackground(Color.WHITE);
            }
        });
        menuProyectos.add(new JMenuItem("Ver Proyectos"));
        menuProyectos.add(new JMenuItem("Asignar Proyecto"));

        menuBar.add(menuTrabajadores);
        menuBar.add(menuClientes);
        menuBar.add(menuProyectos);

        setJMenuBar(menuBar);

        tabbedPane = new JTabbedPane();
        tabbedPane.add("Menu", crearMenu());
        tabbedPane.add("Trabajadores", crearPanelTrabajadores());
        tabbedPane.add("Clientes", crearPanelClientes());
        tabbedPane.add("Contratos", crearPanelContratos());
        
        tabbedPane.addMouseMotionListener(new MouseMotionAdapter() {
            int hoveredIndex = -1;

            @Override
            public void mouseMoved(MouseEvent e) {
                int index = tabbedPane.indexAtLocation(e.getX(), e.getY());
                if (index != hoveredIndex) {
                    hoveredIndex = index;
                    for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                        if (i == hoveredIndex) {
                            tabbedPane.setBackgroundAt(i, new Color(200, 220, 255)); // Hover color
                        } else {
                            tabbedPane.setBackgroundAt(i, null); // Reset
                        }
                    }
                }
            }
        });

        tabbedPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                    tabbedPane.setBackgroundAt(i, null);
                }
            }
        });


        add(tabbedPane);
    }
    
    private JPanel crearMenu() {
        JPanel panel = new JPanel(new BorderLayout());
        ImageIcon logoMenu = new ImageIcon(getClass().getResource("/recursos/logoMenu.png"));
        Image imagen = logoMenu.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(imagen), SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }


    private JPanel crearPanelTrabajadores() {
        JPanel panel = new JPanel(new BorderLayout());
 
        String[] columnas = {
            "ID", "Nombre", "Dirección", "Sexo", "Edad", "Salario", "Proyecto", "Rol"
        };

        // Datos de ejemplo (wey aqui deben que conectar los trabajadores que estan en la base de datos)
        Object[][] datos = {
            {"00123456789", "Laura Pérez", "Calle 10", "F", 30, 2500.0, "App Mobile", "Programador"},
            {"00112233445", "José Gómez", "Av. Central", "M", 45, 3000.0, "Sistema ERP", "Jefe de Proyecto"}
        };

        DefaultTableModel modelo = new DefaultTableModel(datos, columnas);
        JTable tabla = new JTable(modelo);
        tabla.setFillsViewportHeight(true);

        // Mejorar aspecto visual
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setRowHeight(24);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scroll = new JScrollPane(tabla);
        panel.add(scroll, BorderLayout.CENTER);
        
        JButton btnEliminar = new JButton("Eliminar trabajador");
        btnEliminar.setBackground(new Color(220, 53, 69)); // rojo claro
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        btnEliminar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnEliminar.setBackground(new Color(180, 35, 42)); // más oscuro
            }

            public void mouseExited(MouseEvent e) {
                btnEliminar.setBackground(new Color(220, 53, 69)); // original
            }
        });


        btnEliminar.addActionListener(e -> {
            int filaSeleccionada = tabla.getSelectedRow();
            if (filaSeleccionada != -1) {
                int confirmar = JOptionPane.showConfirmDialog(
                    panel,
                    "¿Seguro que deseas eliminar este trabajador?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
                );

                if (confirmar == JOptionPane.YES_OPTION) {
                    modelo.removeRow(filaSeleccionada);
                    JOptionPane.showMessageDialog(panel, "Trabajador eliminado.");
                }
            } else {
                JOptionPane.showMessageDialog(panel, "Selecciona un trabajador primero.");
            }
        });

        // Añadir botón en la parte inferior
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(btnEliminar);
        panel.add(panelBoton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelClientes() {
        JPanel panel = new JPanel(new BorderLayout());
        
        String[] columnas = {
            "ID", "Nombre", "Dirección", "Cantidad de Proyectos Relacionados",
        };

        // Datos de ejemplo (wey aqui deben que conectar los trabajadores que estan en la base de datos)
        Object[][] datos = {
            {"00123419397", "Fefita La Grande", "Calle 10", 3},
            {"00120481289", "Pepito", "Av. Central", 2}
        };

        DefaultTableModel modelo = new DefaultTableModel(datos, columnas);
        JTable tabla = new JTable(modelo);
        tabla.setFillsViewportHeight(true);

        // Mejorar aspecto visual
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setRowHeight(24);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scroll = new JScrollPane(tabla);
        panel.add(scroll, BorderLayout.CENTER);
        
        JButton btnEliminar = new JButton("Eliminar Cliente");
        btnEliminar.setBackground(new Color(220, 53, 69)); // rojo claro
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        btnEliminar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnEliminar.setBackground(new Color(180, 35, 42)); // más oscuro
            }

            public void mouseExited(MouseEvent e) {
                btnEliminar.setBackground(new Color(220, 53, 69)); // original
            }
        });


        btnEliminar.addActionListener(e -> {
            int filaSeleccionada = tabla.getSelectedRow();
            if (filaSeleccionada != -1) {
                int confirmar = JOptionPane.showConfirmDialog(
                    panel,
                    "¿Seguro que deseas eliminar este Cliente?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
                );

                if (confirmar == JOptionPane.YES_OPTION) {
                    modelo.removeRow(filaSeleccionada);
                    JOptionPane.showMessageDialog(panel, "Cliente eliminado.");
                }
            } else {
                JOptionPane.showMessageDialog(panel, "Selecciona un Cliente primero.");
            }
        });

        // Añadir botón en la parte inferior
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(btnEliminar);
        panel.add(panelBoton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelContratos() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Panel de contratos...", SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);
        return panel;
    } 
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            Ventana ventana = new Ventana();
            ventana.setVisible(true);
        });
    }
}

