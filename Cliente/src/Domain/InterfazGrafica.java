package Domain;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Shuperman23
 */
public class InterfazGrafica extends JPanel implements ActionListener {


    private JButton[][] botones;
    private Damas damas;
    private int tamanio = 60;

    public InterfazGrafica() {
        damas = new Damas();
        damas.poner_fichas();
        botones = new JButton[8][8];
        setLayout(new GridLayout(8, 8));

        for (int i = 0; i < botones.length; i++) {
            for (int j = 0; j < botones[0].length; j++) {
                botones[i][j] = new JButton();
                botones[i][j].setPreferredSize(new Dimension(tamanio, tamanio));
                botones[i][j].setBorderPainted(false);
                botones[i][j].setContentAreaFilled(false);
                botones[i][j].setOpaque(true);
                botones[i][j].addActionListener(this);
                actualizarBoton(i, j);
                add(botones[i][j]);
            }
        }
    }

private void actualizarBoton(int i, int j) {
    int valor = damas.verdamas(i, j);
    Color colorFondo = (i + j) % 2 == 0 ? Color.WHITE : Color.BLACK;

    if (valor == damas.getNegras()) {
        botones[i][j].setIcon(new ImageIcon(getClass().getResource("/images/frameIcons/fichaNn.jpg")));
    } else if (valor == damas.getRojas()) {
        botones[i][j].setIcon(new ImageIcon(getClass().getResource("/images/frameIcons/fichaRn.jpg")));
    } else if (valor == damas.getReinaR()) {
        botones[i][j].setIcon(new ImageIcon(getClass().getResource("/images/frameIcons/fichaRRn.jpg")));
    } else if (valor == damas.getReinaN()) {
        botones[i][j].setIcon(new ImageIcon(getClass().getResource("/images/frameIcons/fichaRNn.jpg")));
    } else if (valor == damas.getRelleno()) {
        botones[i][j].setIcon(null);
    }

    botones[i][j].setBackground(colorFondo);
}

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < botones.length; i++) {
            for (int j = 0; j < botones[0].length; j++) {
                if (e.getSource() == botones[i][j]) {
                    // Aquí puedes implementar la lógica para realizar los movimientos
                    // y cambios en el tablero utilizando el método damas.
                    // Por ejemplo, para cambiar el turno y actualizar la interfaz gráfica:
                    damas.CambioDeTurno();
                    actualizarBoton(i, j);
                }
            }
        }
    }
}