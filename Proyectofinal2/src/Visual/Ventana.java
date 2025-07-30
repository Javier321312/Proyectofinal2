package Visual;

import javax.swing.*;

import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Conector.ConexionDB;
import Conector.TrabajadorDAO;
import Logica.Cliente;
import Logica.ProyectoSoftware;

import java.sql.DriverManager;
import Conector.ClienteDAO;
import Conector.ProyectoDAO;

public class Ventana extends JFrame {
    private JTabbedPane tabbedPane;

    public Ventana() {
        setTitle("Proyect Software Management");
        ImageIcon icono = new ImageIcon(getClass().getResource("/recursos/icono.png"));
        setIconImage(icono.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 800);
        setLocationRelativeTo(null);
        setResizable(false);


        JMenuBar menuBar = new JMenuBar();
        JMenu menuTrabajadores = new JMenu("Gestion de Trabajadores");
        menuTrabajadores.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        menuTrabajadores.setForeground(new Color(30, 30, 30)); 
        menuTrabajadores.setOpaque(false);
        menuTrabajadores.setForeground(Color.BLACK);
        
        JMenuItem itemAgregarTrabajador = new JMenuItem("Agregar Trabajador");
        itemAgregarTrabajador.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        itemAgregarTrabajador.setBackground(new Color(245, 245, 245));
        itemAgregarTrabajador.setOpaque(false);
        itemAgregarTrabajador.setBorder(new EmptyBorder(5, 10, 5, 10)); 
        itemAgregarTrabajador.addActionListener(e -> {
            FormularioAgregarTrabajador dialogo = new FormularioAgregarTrabajador(this);
            dialogo.setVisible(true);
            setResizable(false);
        });

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
        menuClientes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        menuClientes.setForeground(new Color(30, 30, 30)); 
        menuClientes.setOpaque(false);
        menuClientes.setForeground(Color.BLACK);
        JMenuItem itemAgregarCliente = new JMenuItem("Agregar Cliente");
        itemAgregarCliente.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        itemAgregarCliente.setBackground(new Color(245, 245, 245));
        itemAgregarCliente.setOpaque(false); 
        itemAgregarCliente.setBorder(new EmptyBorder(5, 10, 5, 10)); 
        itemAgregarCliente.addActionListener(e -> {
            new FormularioAgregarCliente().setVisible(true);
        });

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
        
        menuClientes.add(itemAgregarCliente);
        
        JMenu menuProyectos = new JMenu("Contratos");
        menuProyectos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        menuProyectos.setForeground(new Color(30, 30, 30)); 
        menuProyectos.setOpaque(false);
        menuProyectos.setForeground(Color.BLACK);
        JMenuItem itemAsignarProyectos = new JMenuItem("Asignar Proyectos");
        itemAsignarProyectos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        itemAsignarProyectos.setBackground(new Color(245, 245, 245));
        itemAsignarProyectos.setOpaque(false); 
        itemAsignarProyectos.setBorder(new EmptyBorder(5, 10, 5, 10));
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
    
        menuProyectos.add(itemAsignarProyectos);

        menuBar.add(menuTrabajadores);
        menuBar.add(menuClientes);
        menuBar.add(menuProyectos);

        setJMenuBar(menuBar);

        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabbedPane.setForeground(Color.WHITE);
        tabbedPane.setBackground(new Color(30, 30, 30));

        UIManager.put("TabbedPane.selected", new Color(45, 45, 45));  // Color de fondo del tab activo
        UIManager.put("TabbedPane.contentAreaColor", new Color(60, 60, 60));
        tabbedPane.setUI(new javax.swing.plaf.metal.MetalTabbedPaneUI() {
        	@Override
        	protected void installDefaults() {
        super.installDefaults();
        tabAreaBackground = new Color(40, 40, 40);
        selectedTabPadInsets = new Insets(5, 10, 5, 10);
        tabInsets = new Insets(5, 10, 5, 10);
        	}

        	@Override
        	protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        		g.setColor(isSelected ? new Color(70, 130, 180) : new Color(90, 90, 90));
        		g.fillRoundRect(x, y, w, h, 15, 15);
        	}

        	@Override
        	protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects,
                 int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected) {
    }
});
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
                            tabbedPane.setBackgroundAt(i, new Color(200, 220, 255));
                        } else {
                            tabbedPane.setBackgroundAt(i, null); 
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
        panel.setBackground(Color.WHITE);
        ImageIcon logoMenu = new ImageIcon(getClass().getResource("/recursos/logoMenu.png"));
        Image imagen = logoMenu.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagen);

        JLabel labelConFade = new ImagenConFade(iconoEscalado, 1000); // 1000ms (1s) de fade
        panel.add(labelConFade, BorderLayout.CENTER);
        return panel;
    }


    private JPanel crearPanelTrabajadores() {
        JPanel panel = new JPanel(new BorderLayout());
        Connection conn = ConexionDB.obtenerConexion();
        
        String[] columnas = {"ID", "Nombre", "Dirección", "Sexo", "Edad", "Salario", "Proyecto"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        panel.add(scroll, BorderLayout.CENTER);

        try {
            TrabajadorDAO dao = new TrabajadorDAO(conn);
            ResultSet rs = dao.obtenerTodosTrabajadores();

            while (rs.next()) {
                Object[] fila = {
                    rs.getString("id_trabajador"),
                    rs.getString("nombre"),
                    rs.getString("direccion"),
                    rs.getString("sexo"),
                    rs.getInt("edad"),
                    rs.getDouble("salario"),
                    rs.getString("nombre_proyecto")
                };
                modelo.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(panel, "Error al cargar trabajadores: " + e.getMessage());
        }

        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setRowHeight(24);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        panel.add(scroll, BorderLayout.CENTER);
        
        JButton btnEliminar = new JButton("Eliminar trabajador");
        btnEliminar.setBackground(new Color(220, 53, 69));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        btnEliminar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnEliminar.setBackground(new Color(180, 35, 42));
            }

            public void mouseExited(MouseEvent e) {
                btnEliminar.setBackground(new Color(220, 53, 69)); 
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

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(btnEliminar);
        panel.add(panelBoton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelClientes() {
        JPanel panel = new JPanel(new BorderLayout());
        Connection conn = ConexionDB.obtenerConexion();

        String[] columnas = {"ID", "Nombre", "Dirección"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);

        panel.add(scroll, BorderLayout.CENTER);

        try {
            ClienteDAO dao = new ClienteDAO(conn);
            ArrayList<Cliente> clientes = dao.obtenerClientes();

            for (Cliente cliente : clientes) {
                Object[] fila = {
                    cliente.getId(),
                    cliente.getNombre(),
                    cliente.getDireccion()
                };
                modelo.addRow(fila);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(panel, "Error al cargar clientes: " + e.getMessage());
            e.printStackTrace();
        }

        
        JButton btnEliminar = new JButton("Eliminar Cliente");
        btnEliminar.setBackground(new Color(220, 53, 69)); 
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        btnEliminar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnEliminar.setBackground(new Color(180, 35, 42)); 
            }

            public void mouseExited(MouseEvent e) {
                btnEliminar.setBackground(new Color(220, 53, 69));
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

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(btnEliminar);
        panel.add(panelBoton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelContratos() {
        JPanel panel = new JPanel(new BorderLayout());
        Connection conn = ConexionDB.obtenerConexion();

        String[] columnas = {"Nombre del Proyecto"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        panel.add(scroll, BorderLayout.CENTER);

        try {
            ProyectoDAO dao = new ProyectoDAO(conn);
            List<ProyectoSoftware> proyectos = dao.obtenerTodosProyectos();
            for (ProyectoSoftware p : proyectos) {
                Object[] fila = {p.getNombre()};
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panel, "Error al cargar proyectos: " + e.getMessage());
        }
        
        
        JButton btnEliminar = new JButton("Eliminar Proyecto");
        btnEliminar.setBackground(new Color(220, 53, 69)); 
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        btnEliminar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnEliminar.setBackground(new Color(180, 35, 42)); 
            }

            public void mouseExited(MouseEvent e) {
                btnEliminar.setBackground(new Color(220, 53, 69));
            }
        });


        btnEliminar.addActionListener(e -> {
            int filaSeleccionada = tabla.getSelectedRow();
            if (filaSeleccionada != -1) {
                int confirmar = JOptionPane.showConfirmDialog(
                    panel,
                    "¿Seguro que deseas eliminar este Proyecto?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
                );

                if (confirmar == JOptionPane.YES_OPTION) {
                    modelo.removeRow(filaSeleccionada);
                    JOptionPane.showMessageDialog(panel, "Proyecto eliminado.");
                }
            } else {
                JOptionPane.showMessageDialog(panel, "Selecciona un Proyecto primero.");
            }
        });

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(btnEliminar);
        panel.add(panelBoton, BorderLayout.SOUTH);

        return panel;
    } 
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            Ventana ventana = new Ventana();
            ventana.setVisible(true);
        });
    }
    

    private class ImagenConFade extends JLabel {
        private float alpha = 0f;
        private Timer timer;

        public ImagenConFade(ImageIcon icono, int duracionMs) {
            setIcon(icono);
            setHorizontalAlignment(SwingConstants.CENTER);

            timer = new Timer(40, e -> {
                alpha += 0.05f;
                if (alpha >= 1f) {
                    alpha = 1f;
                    timer.stop();
                }
                repaint();
            });
            timer.setInitialDelay(300);
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            super.paintComponent(g2d);
            g2d.dispose();
        }
    }
}
