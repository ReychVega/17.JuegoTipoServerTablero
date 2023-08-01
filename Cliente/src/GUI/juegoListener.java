package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

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

        if (lastSelectedButton == -1) {
            lastSelectedButton = selectedButton;
        } else {
           boolean result = gui.realizarMovimiento(lastSelectedButton, selectedButton);
            if (result) {
                JButton lastButton = gui.getButton(lastSelectedButton);
                JButton currentButton = gui.getButton(selectedButton);                
            }
            lastSelectedButton = -1;
        }
      //  System.out.println("*"+lastSelectedButton);
        
    }
}
