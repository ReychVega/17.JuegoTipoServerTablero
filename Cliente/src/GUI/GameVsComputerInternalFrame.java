package GUI;

import Domain.Damas;
import Domain.ServerRequest;
import Domain.User;
import static GUI.MainJFrame.clientSocket;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author reych
 */
public class GameVsComputerInternalFrame extends javax.swing.JInternalFrame implements ActionListener, Runnable {
   private final InicioJInternalFrame mainInternalFrame; 
    private String user;
   //atributos necesarios para el tablero
    private JPanel p;
    private JButton[] b;
    public Damas juego;
     //atributos necesarios para el hilo
    private boolean start;
    private long espera;
    private Thread thread;
    //atributos necesarios para el movimiento
    private boolean permiteMover;
    private int lastSelectedButton = -1;
    private int firstSelectedBtn=-1;
    
    /**
     * Creates new form GameVsComputerInternalFrame
     */
    public GameVsComputerInternalFrame(InicioJInternalFrame mainInternalFrame, String user) {
        this.user=user;
        this.permiteMover = true;
        this.start = true;
        this.mainInternalFrame = mainInternalFrame; // Inicializa la referencia a MainJFrame
        initComponents();
        addComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTurno = new javax.swing.JLabel();

        setClosable(true);
        setMaximumSize(new java.awt.Dimension(680, 900));
        setPreferredSize(new java.awt.Dimension(680, 900));

        lblTurno.setBackground(new java.awt.Color(0, 51, 51));
        lblTurno.setFont(new java.awt.Font("Segoe Print", 0, 16)); // NOI18N
        lblTurno.setForeground(new java.awt.Color(255, 0, 0));
        lblTurno.setText("Turno");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(lblTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(265, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTurno)
                .addContainerGap(588, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void addComponents(){
         
        lblTurno.setBackground(new java.awt.Color(0, 51, 51));
        lblTurno.setFont(new java.awt.Font("Segoe Print", 0, 16)); // NOI18N
        lblTurno.setForeground(new java.awt.Color(255, 0, 0));
        lblTurno.setText("It's your turn");
        lblTurno.setBounds(40, 10, 200, 30); // Set the position of the label
        getContentPane().add(lblTurno);

        p = new JPanel();
        juego = new Damas();

        this.setLayout(null);

        p.setBackground(Color.white);
        p.setLayout(null);
        p.setBounds(40, 50, 600, 600);
        
        iniciamosTablero();
        
        this.add(p);
    }

    //actualizamos tablero
    public void actualizarTablero() {
        for (int i = 0; i < b.length; i++) {
            String ruta = getPath(juego.getJuego()[i / 8][i % 8]);
            b[i].setIcon(new ImageIcon(getClass().getResource(ruta)));
        }
    }

   //iniciamos el tablero
    private void iniciamosTablero() {
    p.removeAll();
    p.setLayout(new GridLayout(8, 8));

    b = new JButton[64];
    for (int i = 0; i < b.length; i++) {
        String ruta = getPath(juego.getJuego()[i / 8][i % 8]);
        b[i] = new JButton();
        b[i].setBackground(Color.black);
        b[i].setActionCommand("" + i);
        b[i].setIcon(new ImageIcon(getClass().getResource(ruta)));

        if (juego.getJuego()[i / 8][i % 8] == 0) {
            b[i].setEnabled(false);
        }

        b[i].addActionListener(this);
        p.add(b[i]);
    }

    p.revalidate();
    p.repaint();
}
   
     //define ruta de imagen para cada boton, conforme el caso
    private String getPath(int num) {

        String s = "";
        switch (num) {
            case 0:
                s = "/images/tablero/fichaNula.jpg";
                break;
            case 1:
                s = "/images/tablero/blueCircle.png";
                break;
            case 2:
                s = "/images/tablero/fichaEnemiga.png";
                break;
            case 3:
                s = "/images/tablero/fichaValida.jpg";
                break;
            case 11:
                s = "/images/tablero/rey.png";
                break;
            case 22:
                s = "/images/tablero/reyEnemigo.png";
                break;
        }
        return s;
    }

    @Override
    public void dispose() {
           mainInternalFrame.getMenuBar().setVisible(true);
                //apagamos hilo
                this.start=false;
           
                // Remove game from the JDesktopPane.
                this.mainInternalFrame.getjDesktopPane2().remove(mainInternalFrame.getGameVsComputer());
                //mostramos el usuario de nuevo
                this.mainInternalFrame.getLblUser().setVisible(true);
                          
                // Hide the game component after removing and disposing of it.
                this.setVisible(false);
                
                // Set the game reference to null.
                mainInternalFrame.setGameVsComputer(null);

                //iniamos el hilo en el internal frame padre
                mainInternalFrame.setStart(true);
                mainInternalFrame.setThread(new Thread(mainInternalFrame));
                mainInternalFrame.getThread().start();

                JOptionPane.showMessageDialog(this, "Game finished", "Process Status", JOptionPane.INFORMATION_MESSAGE);
          
    }
    
    //movimientos del usuario
    public boolean realizarMovimiento(int fromIndex, int toIndex) {
        int rowFrom = fromIndex / 8;
        int colFrom = fromIndex % 8;
        int rowTo = toIndex / 8;
        int colTo = toIndex % 8;

        boolean isValidMove = this.juego.validaMovimiento(rowFrom, colFrom, rowTo, colTo);
        if (isValidMove) {
            this.juego.movimiento(rowFrom, colFrom, rowTo, colTo);   
            actualizarTablero();
            this.permiteMover=false;
            this.lblTurno.setText("Computer's turn");
        }
        return isValidMove;
    }

    //hilo para manejar los movimientos de la computadora o finalizacion
    @Override
    public void run() {
        long started;
        long elapse;
        this.espera = 5000; // 5 segundos en milisegundos

        while (this.start) {
            repaint();
            started = System.nanoTime();

            elapse = System.nanoTime() - started;
            // ...   
            verifyMovement();
            verifyCloseOperation();
            //...
            if (this.espera < 0) {
                this.espera = 10000;
                /// System.out.println("Tiempo de espera ajustado: " + this.espera + " ms");
            } else {
                try {
                    Thread.sleep(espera);
                } catch (InterruptedException ex) {
                    System.out.println("Error en hilo en lacle internalFrame");
                }
            }

        }//while
    }//run

    @Override
    public void addNotify() {
        super.addNotify();
        if (this.thread == null) {
            this.thread = new Thread(this);
            this.thread.start();
        }
    }//addNotify
    
    //solicitud de datos de juego
    private void verifyMovement(){    
        //Caso 1. Movimiento de la computadora
          if (this.permiteMover==false) {
              this.juego.moverComputadora();
              this.actualizarTablero();
              this.permiteMover=true;
              this.lblTurno.setText("It's your turn");
          }
     
    }     
        
    //verifica cierre
    private void verifyCloseOperation(){
        if (this.juego.contadorFichasAzules()==true || this.juego.contadorFichasRojas()==true) {
            this.dispose();
        }  
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
  String command = e.getActionCommand();
        int selectedButton = Integer.parseInt(command);
        if (this.permiteMover==true) {
            if (lastSelectedButton == -1) {
                lastSelectedButton = selectedButton;
                firstSelectedBtn=this.juego.getJuego()[lastSelectedButton / 8][lastSelectedButton % 8];
                if (firstSelectedBtn==3 || firstSelectedBtn==2 || firstSelectedBtn==0) {
                    lastSelectedButton = -1;
                    firstSelectedBtn = -1;
                }
            } else {
     //         System.out.println(" num "+gui.juego.getJuego()[lastSelectedButton / 8][lastSelectedButton % 8]);
                if (firstSelectedBtn == 1 || firstSelectedBtn==11) {
                    realizarMovimiento(lastSelectedButton, selectedButton);
                    lastSelectedButton = -1;
                    firstSelectedBtn = -1;
                }
            }
        }

    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblTurno;
    // End of variables declaration//GEN-END:variables
}
