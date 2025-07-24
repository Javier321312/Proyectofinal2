package Visual;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    private JTabbedPane tabbedPane;

    public Ventana() {
        setTitle("Prueba Visual de Proyecto Final de la Muchachada");
        ImageIcon icono = new ImageIcon(getClass().getResource("/recursos/icono.png"));
        setIconImage(icono.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);


        JMenuBar menuBar = new JMenuBar();

        JMenu menuTrabajadores = new JMenu("Gestion de Trabajadores");
        menuTrabajadores.add(new JMenuItem("Agregar Trabajador"));
        menuTrabajadores.add(new JMenuItem("Listar Trabajadores"));

        JMenu menuClientes = new JMenu("Gesti�n de Clientes");
        menuClientes.add(new JMenuItem("Agregar Cliente"));
        menuClientes.add(new JMenuItem("Ver Contratos"));

        JMenu menuProyectos = new JMenu("Proyectos");
        menuProyectos.add(new JMenuItem("Ver Proyectos"));
        menuProyectos.add(new JMenuItem("Asignar Proyecto"));

        menuBar.add(menuTrabajadores);
        menuBar.add(menuClientes);
        menuBar.add(menuProyectos);

        setJMenuBar(menuBar);

        tabbedPane = new JTabbedPane();
        tabbedPane.add("Trabajadores", crearPanelTrabajadores());
        tabbedPane.add("Clientes", crearPanelClientes());
        tabbedPane.add("Contratos", crearPanelContratos());

        add(tabbedPane);
    }

    private JPanel crearPanelTrabajadores() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Listado de trabajadores aqui...", SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);
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

