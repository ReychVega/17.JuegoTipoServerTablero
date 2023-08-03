package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class juegoListener implements ActionListener {

    private GameJInternalFrame gui;
    private int lastSelectedButton = -1;

    public juegoListener(GameJInternalFrame gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         String command = e.getActionCommand();
            int selectedButton = Integer.parseInt(command);
          // System.out.print(" "+selectedButton);
        if (gui.getPermisoTurno() 
                //&& gui.juego.getJuego()[selectedButton/8][selectedButton%8]==1
                ) {
            if (lastSelectedButton == -1) {
                lastSelectedButton = selectedButton;
            } else {
                gui.realizarMovimiento(lastSelectedButton, selectedButton);
                lastSelectedButton = -1;
            }
        }
    }
}
