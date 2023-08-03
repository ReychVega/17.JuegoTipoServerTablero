package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class juegoListener implements ActionListener {

    private GameJInternalFrame gui;
    private int lastSelectedButton = -1;
    private int firstSelectedBtn=-1;
    public juegoListener(GameJInternalFrame gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        int selectedButton = Integer.parseInt(command);
        if (gui.getPermisoTurno()) {
            if (lastSelectedButton == -1) {
                lastSelectedButton = selectedButton;
                firstSelectedBtn=gui.juego.getJuego()[lastSelectedButton / 8][lastSelectedButton % 8];
                if (firstSelectedBtn==3 || firstSelectedBtn==2 || firstSelectedBtn==0) {
                    lastSelectedButton = -1;
                    firstSelectedBtn = -1;
                }
            } else {
     //         System.out.println(" num "+gui.juego.getJuego()[lastSelectedButton / 8][lastSelectedButton % 8]);
                if (firstSelectedBtn == 1 || firstSelectedBtn==11) {
                    gui.realizarMovimiento(lastSelectedButton, selectedButton);
                    lastSelectedButton = -1;
                    firstSelectedBtn = -1;
                }
            }
        }
    }
}
