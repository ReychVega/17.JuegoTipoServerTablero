package Main;

import GUI.MainJFrame;

/**
 *
 * @author reych
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Create and display the main form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainJFrame().setVisible(true);
                          
            }
        });
    

    }
    
}
