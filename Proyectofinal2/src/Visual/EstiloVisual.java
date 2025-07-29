package Visual;

import javax.swing.*;
import java.awt.*;

public class EstiloVisual {

    public static final Color AZUL_CLARO = new Color(220, 235, 250);
    public static final Color AZUL_MEDIO = new Color(100, 149, 237);
    public static final Color AZUL_HOVER = new Color(180, 210, 240);
    public static final Color GRIS_CLARO = new Color(245, 245, 245);
    public static final Color TEXTO_OSCURO = new Color(33, 37, 41);
    public static final Color ROJO = new Color(220, 53, 69);
    public static final Color ROJO_OSCURO = new Color(180, 35, 42);

    public static final Font FUENTE_NORMAL = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD, 16);

    public static void aplicarEstiloGlobal(JFrame ventana) {
        UIManager.put("Button.font", FUENTE_NORMAL);
        UIManager.put("Label.font", FUENTE_NORMAL);
        UIManager.put("Table.font", FUENTE_NORMAL);
        UIManager.put("TableHeader.font", FUENTE_TITULO);
        UIManager.put("TabbedPane.font", FUENTE_NORMAL);
        UIManager.put("OptionPane.messageFont", FUENTE_NORMAL);
        UIManager.put("OptionPane.buttonFont", FUENTE_NORMAL);

        ventana.getContentPane().setBackground(GRIS_CLARO);
    }

    public static void estilizarTabla(JTable tabla) {
        tabla.setFont(FUENTE_NORMAL);
        tabla.getTableHeader().setFont(FUENTE_TITULO);
        tabla.setRowHeight(24);
        tabla.setGridColor(Color.LIGHT_GRAY);
        tabla.setSelectionBackground(AZUL_HOVER);
        tabla.setSelectionForeground(Color.BLACK);
        tabla.setShowHorizontalLines(true);
        tabla.setShowVerticalLines(false);
    }

    public static void estilizarBotonEliminar(JButton boton) {
        boton.setBackground(ROJO);
        boton.setForeground(Color.WHITE);
        boton.setFont(FUENTE_TITULO);
        boton.setFocusPainted(false);
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                boton.setBackground(ROJO_OSCURO);
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                boton.setBackground(ROJO);
            }
        });
    }

    public static void estilizarMenu(JMenu menu) {
        menu.setOpaque(true);
        menu.setBackground(Color.WHITE);
        menu.setForeground(TEXTO_OSCURO);

        menu.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menu.setBackground(AZUL_HOVER);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menu.setBackground(Color.WHITE);
            }
        });
    }
}

