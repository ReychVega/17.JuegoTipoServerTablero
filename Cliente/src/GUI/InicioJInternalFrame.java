package GUI;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import Client.Client;
import Domain.FriendRequest;
import Domain.Request;
import Domain.User;


/*
 * La clase RegistrationJInternalFrame representa una interfaz gráfica para el registro de usuarios.
 * Esta clase extiende javax.swing.JInternalFrame para crear la ventana de registro.
 * Contiene atributos como el usuario (User) y una instancia de Client (clientSocket) para manejar la comunicación con el servidor.
 * @author reych
 */
public class InicioJInternalFrame extends javax.swing.JInternalFrame implements Runnable {

    private User user;
    private Request newRequest;
    private Client clientSocket;
    private final MainJFrame mainFrame; // Agrega este atributo
    private ArrayList<User> onlineFriends;
    private boolean search;
    private boolean start;
    private long espera;
    private Thread thread;
    private boolean foundUsers;
    private boolean online;
    private boolean viewRequests;

    /**
     * Creates new form RegistrationJInternalFrame
     *
     * @param mainFrame
     * @param userName
     */
    public InicioJInternalFrame(MainJFrame mainFrame, String userName) {
        initComponents();
        disableComponents();
        this.getUserData(userName);

        this.search = false;
        this.foundUsers = false;
        this.online = false;
        this.viewRequests=false;
        this.start = true;
        this.mainFrame = mainFrame; // Inicializa la referencia a MainJFrame
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane2 = new javax.swing.JDesktopPane();
        searchBtn = new java.awt.Button();
        jLabel1 = new javax.swing.JLabel();
        userName = new javax.swing.JLabel();
        copas1 = new javax.swing.JLabel();
        tittle = new javax.swing.JLabel();
        instruccion = new javax.swing.JLabel();
        textToSearch = new javax.swing.JTextField();
        comeBackbtn = new java.awt.Button();
        list = new javax.swing.JList<>();
        instruccion1 = new javax.swing.JLabel();
        list1 = new javax.swing.JList<>();
        jMenuBar2 = new javax.swing.JMenuBar();
        inicioUserName = new javax.swing.JMenu();
        friendsOptions = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        friendRequestBtn = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setBackground(new java.awt.Color(0, 0, 0));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        setTitle("Profile");
        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setMaximumSize(new java.awt.Dimension(700, 500));
        setMinimumSize(new java.awt.Dimension(700, 500));
        setNormalBounds(new java.awt.Rectangle(0, 0, 700, 500));
        setPreferredSize(new java.awt.Dimension(700, 500));

        jDesktopPane2.setBackground(new java.awt.Color(0, 0, 0));

        searchBtn.setActionCommand("back");
        searchBtn.setBackground(new java.awt.Color(51, 0, 51));
        searchBtn.setFont(new java.awt.Font("Cascadia Mono", 0, 12)); // NOI18N
        searchBtn.setForeground(new java.awt.Color(255, 255, 255));
        searchBtn.setLabel("Search");
        searchBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Castellar", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/copas.png"))); // NOI18N

        userName.setFont(new java.awt.Font("Segoe Print", 1, 16)); // NOI18N
        userName.setForeground(new java.awt.Color(255, 255, 255));
        userName.setText("User");

        copas1.setFont(new java.awt.Font("Segoe Print", 1, 16)); // NOI18N
        copas1.setForeground(new java.awt.Color(255, 255, 255));
        copas1.setText("Copas");

        tittle.setFont(new java.awt.Font("Segoe Print", 1, 16)); // NOI18N
        tittle.setForeground(new java.awt.Color(255, 255, 255));
        tittle.setText("Online Friends");

        instruccion.setFont(new java.awt.Font("Segoe Print", 1, 16)); // NOI18N
        instruccion.setForeground(new java.awt.Color(255, 255, 255));
        instruccion.setText("Select a user to send a friend request.");

        textToSearch.setBackground(new java.awt.Color(204, 204, 204));

        comeBackbtn.setActionCommand("back");
        comeBackbtn.setBackground(new java.awt.Color(51, 0, 51));
        comeBackbtn.setFont(new java.awt.Font("Cascadia Mono", 0, 12)); // NOI18N
        comeBackbtn.setForeground(new java.awt.Color(255, 255, 255));
        comeBackbtn.setLabel("Log out");
        comeBackbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logOutMouseClicked(evt);
            }
        });

        list.setBackground(new java.awt.Color(204, 204, 204));
        list.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 102, 102), new java.awt.Color(0, 204, 204), null, null));
        list.setFont(new java.awt.Font("Segoe Print", 0, 16)); // NOI18N
        list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listMouseClicked(evt);
            }
        });

        instruccion1.setFont(new java.awt.Font("Segoe Print", 1, 16)); // NOI18N
        instruccion1.setForeground(new java.awt.Color(255, 255, 255));
        instruccion1.setText("Select a user to send a friend request.");

        list1.setBackground(new java.awt.Color(204, 204, 204));
        list1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 102, 102), new java.awt.Color(0, 204, 204), null, null));
        list1.setFont(new java.awt.Font("Segoe Print", 0, 16)); // NOI18N

        jDesktopPane2.setLayer(searchBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(userName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(copas1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(tittle, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(instruccion, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(textToSearch, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(comeBackbtn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(list, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(instruccion1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(list1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(userName)
                        .addGap(515, 515, 515)
                        .addComponent(comeBackbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(copas1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(90, 90, 90)
                        .addComponent(tittle))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(instruccion, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addComponent(textToSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(list, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(instruccion1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10))
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userName)
                    .addComponent(comeBackbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, 0)
                        .addComponent(copas1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(tittle)
                        .addGap(20, 20, 20)
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textToSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(instruccion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(list, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(instruccion1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jLabel1.getAccessibleContext().setAccessibleName("user");

        inicioUserName.setText("user");
        inicioUserName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inicioUserNameMouseClicked(evt);
            }
        });
        jMenuBar2.add(inicioUserName);

        friendsOptions.setText("Friends");

        jMenu1.setText("Requests");

        friendRequestBtn.setFont(new java.awt.Font("Segoe Print", 0, 16)); // NOI18N
        friendRequestBtn.setText("Friend request");
        friendRequestBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                friendRequestBtnActionPerformed(evt);
            }
        });
        jMenu1.add(friendRequestBtn);

        jMenuItem2.setFont(new java.awt.Font("Segoe Print", 0, 16)); // NOI18N
        jMenuItem2.setText("Game requests");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gameRequestActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setFont(new java.awt.Font("Segoe Print", 0, 16)); // NOI18N
        jMenuItem1.setText("Accept/delete requests");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AceptDeleteRequestesActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        friendsOptions.add(jMenu1);

        jMenuBar2.add(friendsOptions);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jDesktopPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMouseClicked
        this.search = true;
    }//GEN-LAST:event_searchMouseClicked

    private void logOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logOutMouseClicked
        if (this.mainFrame != null) {
            mainFrame.enableComponents(); // Llama al método en MainJFrame para mostrar los componentes
        }
        this.dispose();
    }//GEN-LAST:event_logOutMouseClicked

    private void inicioUserNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inicioUserNameMouseClicked
        this.disableComponents();
    }//GEN-LAST:event_inicioUserNameMouseClicked

    //
    private void gameRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gameRequestActionPerformed
        activeComponents(0);
        this.tittle.setText("Online friends");
        this.instruccion.setText("Select a friend to send a game request.");
        this.online = true;
    }//GEN-LAST:event_gameRequestActionPerformed

    //enviamos
    private void friendRequestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_friendRequestBtnActionPerformed
        activeComponents(1);
        this.tittle.setText("Friend requests");
        this.instruccion.setText("Select a user to send a friend request.");
        this.foundUsers = true;
    }//GEN-LAST:event_friendRequestBtnActionPerformed

    //visualizamos las solicitudes y aceptamos o borramos
    private void AceptDeleteRequestesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AceptDeleteRequestesActionPerformed
        activeComponents(2);
        this.tittle.setText("Friend requests");
        this.instruccion.setText("Received requests:");
        this.instruccion1.setText("Sent requests:");
        this.viewRequests=true;
    }//GEN-LAST:event_AceptDeleteRequestesActionPerformed

    //control de envio, aceptacion y eliminacion de solicitudes de amistad.
    private void listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listMouseClicked
        String selectedValue = list.getSelectedValue();
        //Caso 1. send a Friend request
        if (selectedValue != null & foundUsers==true) {

            //verificamos que el usuario este seguro
            int response = JOptionPane.showConfirmDialog(this, "Send request to "+selectedValue+"?",
            "Confirmation", 
            JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
            
                if (clientSocket == null) {
                    connectToServer();
                }
                
                Request request =new Request(user.getUser(), "requestSent");
                request.setRequest(new FriendRequest(user,new User(selectedValue,"")));
                 System.out.println(request.getRequest().showData(1));
                //Enviamos el obj. request al servidor a través del socket
                clientSocket.sendRequestToServer(request);
                String message = clientSocket.receiveMessageFromServer();
                JOptionPane.showMessageDialog(this, message,
                        "Process Status", JOptionPane.INFORMATION_MESSAGE );
            
            }

        //Caso 2. view data 
        } else if (selectedValue != null & viewRequests==true) {
                  //verificamos decision
            Object[] opciones = {"Accept", "Delete", "Cancel"}; 
            String [] aux=selectedValue.split(":");
            int seleccion = JOptionPane.showOptionDialog(null, "Choose an option.", "Friend Request", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            switch(seleccion){
              case 0:
                // Aceptar
                    if (clientSocket == null) {
                    connectToServer();
                }
                
                Request request =new Request(user.getUser(), "AcceptRequest");
                request.setRequest(new FriendRequest(user,new User(aux[1].trim(),"")));
                //Enviamos el obj. request al servidor a través del socket
                clientSocket.sendRequestToServer(request);
                  
                break;
            case 1:
                // Borrar
                break;
             }            
            
        }           
    }//GEN-LAST:event_listMouseClicked
 
    private void activeComponents(int caso) {
         disableComponents();
        if (caso == 1) {
            this.textToSearch.setVisible(true);
            this.searchBtn.setVisible(true);
        }
        if (caso == 2) {
            this.list1.setVisible(true);
            this.instruccion1.setVisible(true);
        }
        this.list.setVisible(true);
        this.tittle.setVisible(true);
        this.instruccion.setVisible(true);
        online = false;
        foundUsers = false;
    }

    private void disableComponents() {

        this.list.setVisible(false);
        this.list1.setVisible(false);
        this.tittle.setVisible(false);
        this.textToSearch.setVisible(false);
        this.instruccion.setVisible(false);
        this.instruccion1.setVisible(false);
        this.searchBtn.setVisible(false);
        online = false;
        foundUsers = false;
        viewRequests=false;
    }

    /*
     *solicita cada 10 seg para refrescar pantalla
     */

    /**
     *
     */

    @Override
    public void run() {
        long start;
        long elapse;
        this.espera = 10000; // 10 segundos en milisegundos

        while (this.start) {
            repaint();
            start = System.nanoTime();

            elapse = System.nanoTime() - start;
            // ...   
            getCompleteData();
            // System.out.println("***\n");

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

    private void getCompleteData() {
        //actualizamos
        this.getUserData(user.getUser());
        
        //Caso 1. 
        if (search == true && foundUsers==true ) {
            getFoundUsers();
            search = false;
        }
        //Caso 2.
        if (viewRequests==true) {
            getRequestData();
            //actualiza cada 10 seg en el hilo
        }
       /* //if (friends == true) {
            getFriendsData();
       // }
        if (online == true) {
            getOnlineFriendsData();
            online=false;
        }
        */ 
     
        
    }

    //solicitamos y actualizamos data del usuario
    private void getUserData(String user) {

        try {

            //solicitamos los datos de usuario
            newRequest = new Request(user, "Get user data");
            if (clientSocket == null) {
                connectToServer();
            }

            //Enviamos el obj. request al servidor a través del socket
            clientSocket.sendRequestToServer(newRequest);

            //recibimos el objeto del usuario
            Object receivedObject = clientSocket.getEntrada().readObject();
            this.user = (User) receivedObject;

            //mostramos
            showData(this.user);

        } catch (IOException | ClassNotFoundException ex) {

        }

    }

    //mostramos la info del usuario
    private void showData(User user) {
        userName.setText(user.getUser());
        this.copas1.setText(user.getCopas() + "");
        this.inicioUserName.setText("Main page");
    }

    //rellenamos la lista con amigos del usuario
    private void getFriendsData() {
        DefaultListModel<String> friendList = new DefaultListModel<>();
        for (int i = 0; i < user.getFriends().size(); i++) {
            friendList.addElement(user.getFriends().get(i).toString());
        }
        this.list.setModel(friendList);
    }

    //rellenamos la lista con amigos en linea del usuario    
    private void getOnlineFriendsData() {
        try {
            //solicitamos los datos de usuario
            newRequest = new Request(user.getUser(), "Get online users");
            if (clientSocket == null) {
                connectToServer();
            }
            //Enviamos el obj. request al servidor a través del socket
            clientSocket.sendRequestToServer(newRequest);

            //recibimos el objeto del usuario
            Object receivedObject = clientSocket.getEntrada().readObject();

            Request request = (Request) receivedObject;

            DefaultListModel<String> onlinefriendList = new DefaultListModel<>();

            for (int i = 0; i < request.getOnlineUsers().size(); i++) {
                if (request.getOnlineUsers().get(i).getFriends() != null) {
                    onlinefriendList.addElement(request.getOnlineUsers().get(i).getUser());
                }
            }
            this.list.setModel(onlinefriendList);

        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error getOnlineFriendsData() de InicioJInternalFrame..");

        }

    }

    //rellenamos la lista con usuarios encontrados de la busqueda
    private void getFoundUsers() {
          
        String userToSearch = textToSearch.getText();
        if (!userToSearch.isEmpty()) {

            try {
                //solicitamos los datos de usuario
                if (clientSocket == null) {
                    connectToServer();
                }

                //Enviamos el obj. request al servidor a través del socket
                clientSocket.sendRequestToServer(new Request(userToSearch, "search users"));

                //recibimos el objeto del usuario
                Object receivedObject = clientSocket.getEntrada().readObject();

                Request request = (Request) receivedObject;
                DefaultListModel<String> foundUsersList = new DefaultListModel<>();

                for (int i = 0; i < request.getFoundUsers().size(); i++) {
                    if (!request.getFoundUsers().get(i).getUser().equalsIgnoreCase(user.getUser())) {
                        foundUsersList.addElement(request.getFoundUsers().get(i).getUser());
                    }
                }                         
                list.setModel(foundUsersList);
                //System.out.println(list.getComponents().toString());           
               
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("error btn search " + ex.getMessage());
            }

        }
    }

    //rellenamos con la lista de solicitdes enviadas y recibidas
    private void getRequestData() {
        DefaultListModel<String> requestList;
        if (user.getRequestSent() != null) {
            //lista 1.
            requestList = new DefaultListModel<>();
            for (int i = 0; i < user.getRequestSent().size(); i++) {
                requestList.addElement(user.getRequestSent().get(i).showData(2));
            }
            this.list.setModel(requestList);
        }
        if (user.getRequestRecieved() != null) {
            //lista 2    
            requestList = new DefaultListModel<>();
            for (int i = 0; i < user.getRequestRecieved().size(); i++) {
                requestList.addElement(user.getRequestRecieved().get(i).showData(1));
            }
            this.list1.setModel(requestList);
        }
    }
    
    // Método para conectarse al servidor.
    private void connectToServer() {
        try {
            clientSocket = new Client("localhost", 5025);
        } catch (IOException ex) {

        }
    }

    //cierre seguro
    @Override
    public void dispose() {
        this.start=false;
        if (clientSocket != null) {
            // Envia el mensaje al servidor indicando que el usuario se está desconectando
            clientSocket.sendRequestToServer(new Request(this.user.getUser(), "log out"));
        }

        if (this.mainFrame != null) {
            mainFrame.enableComponents();
        }

        super.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button comeBackbtn;
    private javax.swing.JLabel copas1;
    private javax.swing.JMenuItem friendRequestBtn;
    private javax.swing.JMenu friendsOptions;
    private javax.swing.JMenu inicioUserName;
    private javax.swing.JLabel instruccion;
    private javax.swing.JLabel instruccion1;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JList<String> list;
    private javax.swing.JList<String> list1;
    private java.awt.Button searchBtn;
    private javax.swing.JTextField textToSearch;
    private javax.swing.JLabel tittle;
    private javax.swing.JLabel userName;
    // End of variables declaration//GEN-END:variables

}
