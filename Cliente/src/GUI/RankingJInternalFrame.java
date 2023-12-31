
package GUI;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import Client.Client;
import Domain.User;
import Domain.ServerRequest;
import static GUI.MainJFrame.clientSocket;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.Collections;
import java.util.Comparator;

public class RankingJInternalFrame extends javax.swing.JInternalFrame {
    
    //private final MainJFrame mainFrame; // Agrega este atributo
    DefaultTableModel mdlTabla;
    Vector vCabeceras = new Vector();
    private ServerRequest newRequest;
    private ArrayList<User> userList;
    private final MainJFrame mainFrame; // Agrega este atributo
    
    public RankingJInternalFrame(MainJFrame mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
        vCabeceras.addElement("USER");
        vCabeceras.addElement("POINTS");
        mdlTabla = new DefaultTableModel(vCabeceras, 0);
        jTable1.setModel(mdlTabla);
        
        try {
        
        newRequest = new ServerRequest("", "Get DB User List");
            if (clientSocket == null) {
                connectToServer();
            }
            //Enviamos el obj. request al servidor a través del socket
            clientSocket.sendRequestToServer(newRequest);
            Object receivedObject = clientSocket.getEntrada().readObject();
            newRequest = (ServerRequest) receivedObject;
           // puntaje();
            
            if (newRequest.getDbUsers()!= null) {
                this.userList = newRequest.getDbUsers();
                //System.out.println(newRequest.getDbUsers().size());
                puntaje();
            }
        } catch (IOException | ClassNotFoundException ex) {

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane2 = new javax.swing.JDesktopPane();
        lblTittle = new javax.swing.JLabel();
        btnComeBack = new java.awt.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setTitle("Ranking");

        jDesktopPane2.setBackground(new java.awt.Color(255, 255, 255));
        jDesktopPane2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTittle.setFont(new java.awt.Font("Castellar", 1, 18)); // NOI18N
        lblTittle.setForeground(new java.awt.Color(0, 153, 153));
        lblTittle.setText("Ranking");
        jDesktopPane2.add(lblTittle, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 120, 40));

        btnComeBack.setActionCommand("back");
        btnComeBack.setBackground(new java.awt.Color(51, 0, 51));
        btnComeBack.setFont(new java.awt.Font("Cascadia Mono", 0, 12)); // NOI18N
        btnComeBack.setForeground(new java.awt.Color(255, 255, 255));
        btnComeBack.setLabel("Back");
        btnComeBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComeBackActionPerformed(evt);
            }
        });
        jDesktopPane2.add(btnComeBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 480, 100, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jDesktopPane2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, -1, 410));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jDesktopPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jDesktopPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnComeBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComeBackActionPerformed
        if (clientSocket!=null) {

        }
        if (this.mainFrame!=null) {
            mainFrame.enableComponents(); // Llama al método en MainJFrame para mostrar los componentes
        }
        dispose();
    }//GEN-LAST:event_btnComeBackActionPerformed

    private void puntaje() {
        Collections.sort(userList, new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                return Integer.compare(user2.getPuntaje(), user1.getPuntaje());
            }
        });
        //for (User user : userList) { System.out.println("Usuario: " + user.getUser() + ", Puntaje: " + user.getPuntaje()); }
        mdlTabla.setRowCount(0);
        for (User user : userList) { Object[] rowData = { user.getUser(), user.getPuntaje() }; mdlTabla.addRow(rowData); }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnComeBack;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblTittle;
    // End of variables declaration//GEN-END:variables
}
