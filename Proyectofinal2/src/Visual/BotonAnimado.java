package Visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BotonAnimado extends JButton {
    private Color colorNormal = new Color(70, 130, 180);
    private Color colorHover = new Color(100, 149, 237);

    public BotonAnimado(String texto) {
        super(texto);
        setBackground(colorNormal);
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setBorderPainted(false);
        setFont(new Font("Arial", Font.BOLD, 14));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Escuchador del mouse para cambiar color y tamaño
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(colorHover);
                setFont(getFont().deriveFont(16f)); // Agranda la fuente
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(colorNormal);
                setFont(getFont().deriveFont(14f)); // Tamaño normal
            }
        });
    }
}

