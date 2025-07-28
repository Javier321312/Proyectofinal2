package Visual;

import javax.swing.*;
import java.awt.*;
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
        JMenuItem itemListar = new JMenuItem("Listar Trabajadores");
        itemListar.addActionListener(e -> {
            tabbedPane.setSelectedIndex(1);
        });
        menuTrabajadores.add(itemListar);
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
        menuClientes.add(new JMenuItem("Ver Contratos"));

        JMenu menuProyectos = new JMenu("Proyectos");
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

        JTable tabla = new JTable(datos, columnas);
        tabla.setFillsViewportHeight(true);

        // Mejorar aspecto visual
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setRowHeight(24);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scroll = new JScrollPane(tabla);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelClientes() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Gestion de clientes...", SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelContratos() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Panel de contratos...", SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);
        return panel;
    } 

}

