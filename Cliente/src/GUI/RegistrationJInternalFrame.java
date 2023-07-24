package GUI;

import Client.Client;
import Domain.User;
import static GUI.MainJFrame.clientSocket;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * La clase RegistrationJInternalFrame representa una interfaz gráfica para el registro de usuarios.
 * Esta clase extiende javax.swing.JInternalFrame para crear la ventana de registro.
 * Contiene atributos como el usuario (User) y una instancia de Client (clientSocket) para manejar la comunicación con el servidor.
 * @author reych
 */
public class RegistrationJInternalFrame extends javax.swing.JInternalFrame {
 private User user;
        private final MainJFrame mainFrame; // Agrega este atributo

    /**
     * Creates new form RegistrationJInternalFrame
     * @param mainFrame
     */
    public RegistrationJInternalFrame(MainJFrame mainFrame) {
        initComponents();
        this.mainFrame = mainFrame; // Inicializa la referencia a MainJFrame
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane2 = new javax.swing.JDesktopPane();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        alert = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        button1 = new java.awt.Button();
        button2 = new java.awt.Button();
        jLabel7 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        setTitle("Registration");
        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setMaximumSize(new java.awt.Dimension(700, 380));
        setMinimumSize(new java.awt.Dimension(700, 380));
        setNormalBounds(new java.awt.Rectangle(0, 0, 690, 380));
        setPreferredSize(new java.awt.Dimension(700, 380));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDesktopPane2.setBackground(new java.awt.Color(0, 0, 0));
        jDesktopPane2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Castellar", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("User:");
        jDesktopPane2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, -1, -1));

        jLabel3.setFont(new java.awt.Font("Castellar", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password");
        jDesktopPane2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 170, -1, -1));

        alert.setFont(new java.awt.Font("Cascadia Code", 0, 16)); // NOI18N
        alert.setForeground(new java.awt.Color(255, 0, 0));
        jDesktopPane2.add(alert, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 300, 210, 20));

        jTextField1.setToolTipText("");
        jDesktopPane2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 120, 120, -1));
        jDesktopPane2.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, 120, -1));
        jDesktopPane2.add(jPasswordField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 160, 120, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/initBackground.jpg"))); // NOI18N
        jLabel5.setText(" ;");
        jDesktopPane2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 700, 470));

        jLabel6.setFont(new java.awt.Font("Castellar", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 153));
        jLabel6.setText("Registration");
        jDesktopPane2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 221, 40));

        button1.setBackground(new java.awt.Color(0, 102, 102));
        button1.setFont(new java.awt.Font("Cascadia Mono", 0, 12)); // NOI18N
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setLabel("Register");
        button1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button1registerMouseClicked(evt);
            }
        });
        jDesktopPane2.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 250, 120, 30));

        button2.setActionCommand("back");
        button2.setBackground(new java.awt.Color(51, 0, 51));
        button2.setFont(new java.awt.Font("Cascadia Mono", 0, 12)); // NOI18N
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setLabel("Back");
        button2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button2backMouseClicked(evt);
            }
        });
        jDesktopPane2.add(button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 100, 30));

        jLabel7.setFont(new java.awt.Font("Castellar", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Confirm Password");
        jDesktopPane2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, -1, -1));

        getContentPane().add(jDesktopPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 680, 517));

        pack();
    }// </editor-fold>//GEN-END:initComponents

     /*
     * Evento que se dispara cuando se hace clic en el botón de registro.
     * Recolecta los datos ingresados por el usuario, crea una instancia de User y la envía al servidor a través del cliente.
     * Luego, muestra el mensaje de respuesta del servidor en la interfaz gráfica.
     */
    private void button1registerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button1registerMouseClicked
        this.button1.setEnabled(false);
        this.button2.setEnabled(false);
        
        
        // Caso 1. Que todos los campos estén llenos
        if (!jTextField1.getText().isEmpty() && !jPasswordField1.getText().isEmpty()
            && !jPasswordField2.getText().isEmpty()) {

            String patron = ".*[:].*";
            Pattern pattern = Pattern.compile(patron);
            Matcher matcher = pattern.matcher(jTextField1.getText());
            boolean contieneCaracterEspecial = matcher.matches();
            //Caso1.1
            if(!contieneCaracterEspecial){
            
            //1.2 Revisamos que las contraseñas coincidan
            if (jPasswordField1.getText().equals(jPasswordField2.getText())) {

                // Creamos una nueva instancia de User con los datos del formulario
                user = new User(jTextField1.getText(), jPasswordField1.getText());
                user.setAction("registration");
                
                if (clientSocket == null) {
                    connectToServer();
                }

                // Enviamos el objeto User al servidor a través del socket
                clientSocket.sendUserToServer(user);

                // Obtenemos la respuesta del servidor y la mostramos en la interfaz gráfica
                String message = clientSocket.receiveMessageFromServer();
                this.alert.setText(message);

            } else {// Indicamos que las contraseñas no coinciden
                alert.setText("Passwords do not match");
            }
            
            }else{
                alert.setText("Do not use special characters");
            }
            
        } else {  //Caso 2. Indicamos que hay datos incompletos
            alert.setText("Incomplete data");
        }
        
        this.button1.setEnabled(true);
        this.button2.setEnabled(true);
        jTextField1.setText("");
        jPasswordField1.setText("");
        jPasswordField2.setText("");
        
    }//GEN-LAST:event_button1registerMouseClicked

    private void button2backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button2backMouseClicked
        if (clientSocket!=null) {

        }
        if (this.mainFrame!=null) {
            mainFrame.enableComponents(); // Llama al método en MainJFrame para mostrar los componentes
        }
        dispose();
    }//GEN-LAST:event_button2backMouseClicked

        
     /*
     * Método para conectarse al servidor.
     * Crea una instancia de Client para establecer la conexión con el servidor.
     * Si ocurre algún error, se mostrará un mensaje en la interfaz gráfica.
     */
    private void connectToServer() {
        try {
            clientSocket = new Client("localhost", 5025);
        } catch (IOException ex) {
            alert.setText("Try again later");
        }
    }    
    
 
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alert;
    private java.awt.Button button1;
    private java.awt.Button button2;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
