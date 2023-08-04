package GUI;

import Client.Client;
import Domain.Damas;
import Domain.ServerRequest;
import Domain.User;
import static GUI.MainJFrame.clientSocket;
import java.awt.Color;
import java.awt.GridLayout;
import java.beans.PropertyVetoException;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author reych
 * @author ronny
 */
public class GameJInternalFrame extends JInternalFrame implements Runnable{
    private final InicioJInternalFrame mainInternalFrame; 
    private ServerRequest newRequest;
    private String user;
    //atributos necesarios para el hilo
    private boolean start;
    private long espera;
    private Thread thread;
   //atributos nedcesarios para el tablero
    private JPanel p;
    private JButton[] b;
    public Damas juego;
    private juegoListener pL;
    private int lastSelectedButton = -1;
    //atributos necesarios para la comunicacion con el server
    private boolean permiteMover;
    
    /**
     * Creates new form GameJInternalFrame
     */
    public GameJInternalFrame(InicioJInternalFrame mainInternalFrame, String user) {
        this.user=user;
        this.start = true;
        this.permiteMover = false;
        this.mainInternalFrame = mainInternalFrame; // Inicializa la referencia a MainJFrame
        initComponents();
        addComponents();
            
    }

    public JButton getButton(int index) {
        return b[index];
    }

    public boolean getPermisoTurno(){
        return this.permiteMover;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblEnemyName = new javax.swing.JLabel();
        lblTurno = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Game");
        setMaximumSize(new java.awt.Dimension(700, 900));
        setPreferredSize(new java.awt.Dimension(700, 900));

        lblEnemyName.setBackground(new java.awt.Color(0, 51, 51));
        lblEnemyName.setFont(new java.awt.Font("Segoe Print", 0, 16)); // NOI18N
        lblEnemyName.setForeground(new java.awt.Color(0, 51, 51));
        lblEnemyName.setText("Oponente");

        lblTurno.setBackground(new java.awt.Color(0, 51, 51));
        lblTurno.setFont(new java.awt.Font("Segoe Print", 0, 16)); // NOI18N
        lblTurno.setForeground(new java.awt.Color(255, 0, 0));
        lblTurno.setText("Turno");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(lblTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addComponent(lblEnemyName, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTurno)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEnemyName)
                .addContainerGap(829, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //agregamos los componentes necesarios dell juego, de forma manual
    private void addComponents(){
         
        lblEnemyName.setBackground(new java.awt.Color(0, 51, 51));
        lblEnemyName.setFont(new java.awt.Font("Segoe Print", 0, 16)); // NOI18N
        lblEnemyName.setForeground(new java.awt.Color(0, 51, 51));
        lblEnemyName.setText("Oponente");
        lblEnemyName.setBounds(500, 10, 100, 30); // Set the position of the label

        lblTurno.setBackground(new java.awt.Color(0, 51, 51));
        lblTurno.setFont(new java.awt.Font("Segoe Print", 0, 16)); // NOI18N
        lblTurno.setForeground(new java.awt.Color(255, 0, 0));
        lblTurno.setText("Turno");
        lblTurno.setBounds(40, 10, 200, 30); // Set the position of the label

        getContentPane().add(lblEnemyName);
        getContentPane().add(lblTurno);

        p = new JPanel();
        juego = new Damas();
        pL = new juegoListener(this);

        this.setLayout(null);

        p.setBackground(Color.white);
        p.setLayout(null);
        p.setBounds(40, 50, 600, 600);
        
        iniciamosTablero();
        
        this.add(p);
    }
    
     @Override
    public void run() {
        long started;
        long elapse;
        this.espera = 2000; // 2 segundos en milisegundos

        while (this.start) {
            repaint();
            started = System.nanoTime();

            elapse = System.nanoTime() - started;
            // ...   
            getCompleteData();

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
    private void getCompleteData(){    
        try {
            verifyGameState();     
        } catch (PropertyVetoException ex) {
           // Logger.getLogger(GameJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //verifica salida del juego
    private void verifyGameState() throws PropertyVetoException {
        try {
            //validamos inicio de juego
            newRequest = new ServerRequest(user, "getGameValidation");
            if (clientSocket == null) {
                connectToServer();
            }
            //Enviamos el obj. request al servidor a través del socket
            clientSocket.sendRequestToServer(newRequest);
            Object receivedObject = clientSocket.getEntrada().readObject();
            newRequest = (ServerRequest) receivedObject;
            
            if (newRequest.getEnemy() == null && newRequest.isGameState() == false) {
                mainInternalFrame.getMenuBar().setVisible(true);
                //paramos el hilo
                this.start = false;
                
                // Remove game from the JDesktopPane.
                this.mainInternalFrame.getjDesktopPane2().remove(mainInternalFrame.getGame());
                //mostramos el usuario de nuevo
                this.mainInternalFrame.getLblUser().setVisible(true);
                // Dispose of the game's internal resources.
                this.setClosed(true);
                this.dispose();
                
                // Hide the game component after removing and disposing of it.
                this.setVisible(false);
                
                // Set the game reference to null.
                mainInternalFrame.setGame(null);

                //iniamos el hilo en el internal frame padre
                mainInternalFrame.setStart(true);
                mainInternalFrame.setThread(new Thread(mainInternalFrame));
                mainInternalFrame.getThread().start();

                JOptionPane.showMessageDialog(this, "Game finished", "Process Status", JOptionPane.INFORMATION_MESSAGE);
            }else{
                
                //validamos datos para la fisica del juego
                this.lblEnemyName.setText(newRequest.getEnemy().getUser());
                
                this.permiteMover=newRequest.isTurno();         
                if (permiteMover==true) {
                    this.lblTurno.setText("It's your turn");
                }else{
                    this.lblTurno.setText("Enemy's turn");
                }
                
                if (newRequest.getJuego()!=null) {
                   // System.out.println("es diferente de nulo");
                    invertimosTablero(newRequest.getJuego());
                    newRequest.setJuego(null);
                }
                
            }
        } catch (IOException | ClassNotFoundException ex) {

        }
    }

     // Método para conectarse al servidor.
    private void connectToServer() {
        try {
            clientSocket = new Client("localhost", 5025);
        } catch (IOException ex) {
            System.out.println("*");
        }
    }

    // Método para actualizar las imágenes de los botones según el estado del juego
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

        b[i].addActionListener(pL);
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
    
    public boolean realizarMovimiento(int fromIndex, int toIndex) {
        int rowFrom = fromIndex / 8;
        int colFrom = fromIndex % 8;
        int rowTo = toIndex / 8;
        int colTo = toIndex % 8;

        boolean isValidMove = this.juego.validaMovimiento(rowFrom, colFrom, rowTo, colTo);
        if (isValidMove) {
            this.juego.movimiento(rowFrom, colFrom, rowTo, colTo);
            //comunica con el server para enviar los datos al otro cliente

            //validamos inicio de juego
            newRequest = new ServerRequest(user, "GameMove");
            newRequest.setJuego(guardarTablero());
            newRequest.setEnemy(new User(this.lblEnemyName.getText(), ""));

            if (clientSocket == null) {
                connectToServer();
            }
            //Enviamos el obj. request al servidor a través del socket
            clientSocket.sendRequestToServer(newRequest);

             //System.out.println("Cambio de ficha\n"+
             //       this.juego.toString());
            //System.out.println("envia=\n"+mostrarArray(newRequest.getJuego()));
            
            actualizarTablero();
        }
        return isValidMove;
    }

    private int [][] guardarTablero(){
    int [][] tablero=new int [8][8];
    int count=0;
        for (int i = 0; i < this.juego.getJuego().length; i++) {
            for (int j = 0; j < this.juego.getJuego()[0].length; j++) {
                tablero[count/8][count%8]=this.juego.getJuego()[i][j];
                count+=1;
            }
        }
        return tablero;
    }

    public String mostrarArray(int[][] juego) {
        String s="";
        for (int i = 0; i < juego.length; i++) {
            for (int j = 0; j < juego[0].length; j++) {
                s+=juego[i][j]+" ";
            }
            s+="\n";
        }
        return s;
    }
    
    public void invertimosTablero(int[][] tablero) {
        String s = "";
        int contador = 0;
        for (int k = tablero.length - 1; k >= 0; k--) {
            for (int l = tablero[0].length - 1; l >= 0; l--) {
                //tableroInvertido[contador/8][contador%8]=tablero[k][l];

                if (tablero[k][l] == 1) {
                    juego.getJuego()[contador / 8][contador % 8] = 2;
                }
                if (tablero[k][l] == 22) {
                    juego.getJuego()[contador / 8][contador % 8] = 11;
                }
                if (tablero[k][l] == 11) {
                    juego.getJuego()[contador / 8][contador % 8] = 22;
                }
                if (tablero[k][l] == 2) {
                    juego.getJuego()[contador / 8][contador % 8] = 1;
                }
                if (tablero[k][l] == 3) {
                    juego.getJuego()[contador / 8][contador % 8] = 3;
                }
                if (tablero[k][l] == 0) {
                    juego.getJuego()[contador / 8][contador % 8] = 0;
                }

                s += juego.getJuego()[contador / 8][contador % 8] + ",";
                contador+=1;
            }
            s += "\n";
        }
        // System.out.println(s);
         this.actualizarTablero();
         iniciamosTablero();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblEnemyName;
    private javax.swing.JLabel lblTurno;
    // End of variables declaration//GEN-END:variables
}
