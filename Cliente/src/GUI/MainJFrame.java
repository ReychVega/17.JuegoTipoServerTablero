package GUI;

import Client.Client;
import Domain.ServerRequest;
import Domain.User;
import java.awt.Component;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author reych
 */
public class MainJFrame extends javax.swing.JFrame {

    private User user;
    public static Client clientSocket;
    public static String USERCONTROL;

    public MainJFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        //Cierre
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                String btnCierre[] = {"Close", "Cancel"};
                int eleccion = JOptionPane.showOptionDialog(null, "Do you want to close the application?",
                        "Close program.", 0, 0, null, btnCierre, this);
                if (eleccion == JOptionPane.YES_OPTION) {
                    if (clientSocket != null) {
                        // Envia el mensaje al servidor indicando que el usuario se está desconectando
                        clientSocket.sendRequestToServer(new ServerRequest(USERCONTROL, "log out"));
                    }
                    System.exit(0);
                }
            }
        }
        );
    }

    /*
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPanel = new javax.swing.JDesktopPane();
        lblGame = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        btnLogin = new java.awt.Button();
        txtUser = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        lblUser = new javax.swing.JLabel();
        btnRegister = new java.awt.Button();
        btnRanking = new java.awt.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Game");
        setLocation(new java.awt.Point(0, 0));
        setMaximumSize(new java.awt.Dimension(700, 900));
        setPreferredSize(new java.awt.Dimension(700, 900));
        setResizable(false);

        jDesktopPanel.setBackground(new java.awt.Color(255, 255, 255));
        jDesktopPanel.setMaximumSize(new java.awt.Dimension(700, 900));
        jDesktopPanel.setPreferredSize(new java.awt.Dimension(700, 900));

        lblGame.setFont(new java.awt.Font("Castellar", 1, 18)); // NOI18N
        lblGame.setForeground(new java.awt.Color(0, 153, 153));
        lblGame.setText("GAME");
        jDesktopPanel.add(lblGame);
        lblGame.setBounds(20, 20, 221, 40);

        lblPassword.setFont(new java.awt.Font("Castellar", 1, 18)); // NOI18N
        lblPassword.setText("Password:");
        jDesktopPanel.add(lblPassword);
        lblPassword.setBounds(120, 170, 150, 40);

        btnLogin.setBackground(new java.awt.Color(0, 102, 102));
        btnLogin.setFont(new java.awt.Font("Cascadia Mono", 0, 12)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setLabel("Log in");
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logInMouseClicked(evt);
            }
        });
        jDesktopPanel.add(btnLogin);
        btnLogin.setBounds(290, 220, 190, 30);

        txtUser.setFont(new java.awt.Font("Segoe Print", 0, 16)); // NOI18N
        jDesktopPanel.add(txtUser);
        txtUser.setBounds(290, 120, 190, 30);

        txtPassword.setFont(new java.awt.Font("Segoe Print", 0, 16)); // NOI18N
        jDesktopPanel.add(txtPassword);
        txtPassword.setBounds(290, 170, 190, 30);

        lblUser.setFont(new java.awt.Font("Castellar", 1, 18)); // NOI18N
        lblUser.setText("User:");
        jDesktopPanel.add(lblUser);
        lblUser.setBounds(130, 110, 70, 40);

        btnRegister.setBackground(new java.awt.Color(51, 0, 51));
        btnRegister.setFont(new java.awt.Font("Cascadia Mono", 0, 12)); // NOI18N
        btnRegister.setForeground(new java.awt.Color(255, 255, 255));
        btnRegister.setLabel("Create new account");
        btnRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newAccountMouseClicked(evt);
            }
        });
        jDesktopPanel.add(btnRegister);
        btnRegister.setBounds(290, 270, 190, 30);

        btnRanking.setActionCommand("Ranking");
        btnRanking.setBackground(new java.awt.Color(0, 102, 102));
        btnRanking.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnRanking.setForeground(new java.awt.Color(255, 255, 255));
        btnRanking.setLabel("Ranking");
        btnRanking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRankingActionPerformed(evt);
            }
        });
        jDesktopPanel.add(btnRanking);
        btnRanking.setBounds(290, 320, 190, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("Class Royale.");
        getAccessibleContext().setAccessibleDescription("Game.");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logInMouseClicked
        this.btnLogin.setEnabled(false);

        //Case 1. Verificamos la informacion
        if (!this.txtPassword.getText().isEmpty() & !this.txtUser.getText().isEmpty()) {

            user = new User(this.txtUser.getText(), txtPassword.getText());
            user.setAction("loggin");

            if (clientSocket == null) {
                connectToServer();
            }

            // Enviamos el objeto User al servidor a través del socket
            clientSocket.sendUserToServer(user);

            // Obtenemos la respuesta del servidor y la mostramos en la interfaz gráfica
            String message = clientSocket.receiveMessageFromServer();
            if (!message.equalsIgnoreCase("init")) {
                JOptionPane.showMessageDialog(this, message, "Status", JOptionPane.INFORMATION_MESSAGE);
            }

            //Cargamos el inicio
            if (message.equalsIgnoreCase("init")) {
                disableComponents();
                USERCONTROL = user.getUser();
                InicioJInternalFrame inicio;
                try {
                    inicio = new InicioJInternalFrame(this, user.getUser()); 
                    inicio.setVisible(true);
                    this.jDesktopPanel.add(inicio);
                disableInternalFrameMove(inicio);
                } catch (IOException ex) {
                    Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }

        } else {
            JOptionPane.showMessageDialog(this, "Incomplete data", "Status", JOptionPane.INFORMATION_MESSAGE);
        }
        this.btnLogin.setEnabled(true);
    }//GEN-LAST:event_logInMouseClicked

    //visualizar el JFrameInternal para el registro
    private void newAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newAccountMouseClicked
        /* Create and display the form */
        disableComponents();
        RegistrationJInternalFrame registration = new RegistrationJInternalFrame(this);
        registration.setVisible(true);
        disableInternalFrameMove(registration);
        this.jDesktopPanel.add(registration);

    }//GEN-LAST:event_newAccountMouseClicked

    private void btnRankingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRankingActionPerformed
        /* Create and display the form */
        disableComponents();
        RankingJInternalFrame ranking = new RankingJInternalFrame(this);
        ranking.setVisible(true);
        disableInternalFrameMove(ranking);
        this.jDesktopPanel.add(ranking);
    }//GEN-LAST:event_btnRankingActionPerformed

    private void disableInternalFrameMove(JInternalFrame frame) {
        // Deshabilitar el comportamiento de arrastrar y mover para el RegistrationJInternalFrame
        frame.setBorder(null);
        BasicInternalFrameUI ui = (BasicInternalFrameUI) frame.getUI();
        Component northPane = ui.getNorthPane();
        MouseMotionListener[] motionListeners = (MouseMotionListener[]) northPane.getListeners(MouseMotionListener.class);
        for (MouseMotionListener listener : motionListeners) {
            northPane.removeMouseMotionListener(listener);
        }

    }

    /*
     * Método para conectarse al servidor.
     * Crea una instancia de Client para establecer la conexión con el servidor.
     * Si ocurre algún error, se mostrará un mensaje en la interfaz gráfica.
     */
    private void connectToServer() {
        try {
            clientSocket = new Client("localhost", 5025);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Check the internet connection", "Status", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //desabilitar los componentes
    private void disableComponents() {
        // Ocultar los componentes que deseas
        lblGame.setVisible(false);
        lblPassword.setVisible(false);
        btnLogin.setVisible(false);
        txtUser.setVisible(false);
        txtPassword.setVisible(false);
        lblUser.setVisible(false);
        btnRegister.setVisible(false);
        btnRanking.setVisible(false);

    }

    // Método para mostrar los componentes ocultos
    public void enableComponents() {
        lblGame.setVisible(true);
        lblPassword.setVisible(true);
        btnLogin.setVisible(true);
        txtUser.setText("");
        txtUser.setVisible(true);
        txtPassword.setText("");
        txtPassword.setVisible(true);
        lblUser.setVisible(true);
        btnRegister.setVisible(true);     
        btnRanking.setVisible(true);

        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnLogin;
    private java.awt.Button btnRanking;
    private java.awt.Button btnRegister;
    private javax.swing.JDesktopPane jDesktopPanel;
    private javax.swing.JLabel lblGame;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUser;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
