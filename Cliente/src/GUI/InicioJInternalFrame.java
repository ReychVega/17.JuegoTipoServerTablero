package GUI;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import Client.Client;
import Domain.FriendRequest;
import Domain.Request;
import Domain.User;
import javax.swing.JScrollPane;


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
    private boolean friends;
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
        this.friends = false;
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
        instruccion1 = new javax.swing.JLabel();
        contenedor1 = new javax.swing.JScrollPane();
        list = new javax.swing.JList<>();
        contenedor2 = new javax.swing.JScrollPane();
        list1 = new javax.swing.JList<>();
        jMenuBar2 = new javax.swing.JMenuBar();
        inicioUserName = new javax.swing.JMenu();
        friendsOptions = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        friendRequestBtn = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setBackground(new java.awt.Color(0, 0, 0));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        setTitle("Profile");
        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setMaximumSize(new java.awt.Dimension(700, 500));
        setMinimumSize(new java.awt.Dimension(700, 500));
        setNormalBounds(new java.awt.Rectangle(0, 0, 700, 500));
        setPreferredSize(new java.awt.Dimension(700, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDesktopPane2.setBackground(new java.awt.Color(0, 0, 0));

        searchBtn.setActionCommand("back");
        searchBtn.setBackground(new java.awt.Color(51, 0, 51));
        searchBtn.setFont(new java.awt.Font("Segoe Print", 0, 12)); // NOI18N
        searchBtn.setForeground(new java.awt.Color(255, 255, 255));
        searchBtn.setLabel("Search");
        searchBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Castellar", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N

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

        instruccion1.setFont(new java.awt.Font("Segoe Print", 1, 16)); // NOI18N
        instruccion1.setForeground(new java.awt.Color(255, 255, 255));
        instruccion1.setText("Select a user to send a friend request.");

        contenedor1.setBackground(new java.awt.Color(0, 0, 0));

        list.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 102, 102), new java.awt.Color(0, 204, 204), null, null));
        list.setFont(new java.awt.Font("Segoe Print", 0, 16)); // NOI18N
        list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listMouseClicked(evt);
            }
        });
        contenedor1.setViewportView(list);

        contenedor2.setBackground(new java.awt.Color(0, 0, 0));

        list1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 102, 102), new java.awt.Color(0, 204, 204), null, null));
        list1.setFont(new java.awt.Font("Segoe Print", 0, 16)); // NOI18N
        list1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                list1MouseClicked(evt);
            }
        });
        contenedor2.setViewportView(list1);

        jDesktopPane2.setLayer(searchBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(userName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(copas1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(tittle, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(instruccion, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(textToSearch, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(instruccion1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(contenedor1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(contenedor2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(copas1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(userName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel1))))
                .addGap(64, 64, 64)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addComponent(tittle)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addComponent(textToSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(contenedor1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(contenedor2, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(instruccion, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(instruccion1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(66, Short.MAX_VALUE))))
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(copas1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userName))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(tittle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textToSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(instruccion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contenedor1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(instruccion1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contenedor2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jLabel1.getAccessibleContext().setAccessibleName("user");

        getContentPane().add(jDesktopPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 700, 450));

        inicioUserName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/frameIcons/user.png"))); // NOI18N
        inicioUserName.setText("user");
        inicioUserName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inicioUserNameMouseClicked(evt);
            }
        });
        jMenuBar2.add(inicioUserName);

        friendsOptions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/frameIcons/friends.png"))); // NOI18N
        friendsOptions.setText("Friends");

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/frameIcons/settings.png"))); // NOI18N
        jMenu1.setText("Requests");

        friendRequestBtn.setFont(new java.awt.Font("Segoe Print", 0, 16)); // NOI18N
        friendRequestBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/frameIcons/request.png"))); // NOI18N
        friendRequestBtn.setText("Friend request");
        friendRequestBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                friendRequestBtnActionPerformed(evt);
            }
        });
        jMenu1.add(friendRequestBtn);

        jMenuItem2.setFont(new java.awt.Font("Segoe Print", 0, 16)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/frameIcons/game-control.png"))); // NOI18N
        jMenuItem2.setText("Game requests");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gameRequestActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setFont(new java.awt.Font("Segoe Print", 0, 16)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/frameIcons/label.png"))); // NOI18N
        jMenuItem1.setText("Accept/delete friend requests");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AceptDeleteRequestesActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        friendsOptions.add(jMenu1);

        jMenuBar2.add(friendsOptions);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/frameIcons/logout.png"))); // NOI18N
        jMenu2.setText("Log out");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logOutMouseClicked(evt);
            }
        });
        jMenuBar2.add(jMenu2);

        setJMenuBar(jMenuBar2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inicioUserNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inicioUserNameMouseClicked
        this.disableComponents();
    }//GEN-LAST:event_inicioUserNameMouseClicked

    //visualizamos amigos en linea para enviar solicitudes de juego o eliminamos amigos
    private void gameRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gameRequestActionPerformed
        activeComponents(2);
        this.tittle.setText("Friends");
        this.instruccion.setText("Online friends. Select a friend to send a game request.");
        this.instruccion1.setText("Friends. Select a user to delete the friendship.");
        this.online = true;
    }//GEN-LAST:event_gameRequestActionPerformed

    //enviamos solicitud de amistad
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

    //eliminamos solicitudes de amistadenviadas, o amigos
    private void list1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_list1MouseClicked
        String selectedValue = list1.getSelectedValue();
        //eliminanos amigo
        if (selectedValue != null & online == true) {
            if (clientSocket == null) {
                connectToServer();
            }
            Request deleteFriend = new Request(user.getUser(), "DeleteFriend");
                deleteFriend.setFriend(selectedValue.trim());
                //Enviamos el obj. request al servidor a través del socket
                clientSocket.sendRequestToServer(deleteFriend);
                String sms = clientSocket.receiveMessageFromServer();
                JOptionPane.showMessageDialog(this, sms,
                    "Process Status", JOptionPane.INFORMATION_MESSAGE);
                
        }
        //solicitudes enviadas
        if (selectedValue != null & viewRequests == true) {
            String [] aux=selectedValue.split(":");
                if (clientSocket == null) {
                    connectToServer();
                }
                Request deleteRequest = new Request(user.getUser(), "DeleteRequest");
                deleteRequest.setRequest(new FriendRequest(user, new User(aux[1].trim(), "")));
                //Enviamos el obj. request al servidor a través del socket
                clientSocket.sendRequestToServer(deleteRequest);
                String sms = clientSocket.receiveMessageFromServer();
                JOptionPane.showMessageDialog(this, sms,
                    "Process Status", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_list1MouseClicked

    //control de envio, aceptacion y eliminacion de solicitudes de amistad recibidas.
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
                //System.out.println(request.getRequest().showData(1));
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
                Request acceptRequest = new Request(user.getUser(), "AcceptRequest");
                acceptRequest.setRequest(new FriendRequest(user, new User(aux[1].trim(), "")));
                //Enviamos el obj. request al servidor a través del socket
                clientSocket.sendRequestToServer(acceptRequest);
                String message = clientSocket.receiveMessageFromServer();
                JOptionPane.showMessageDialog(this, message,
                    "Process Status", JOptionPane.INFORMATION_MESSAGE);
                break;
                case 1:
                // Borrar
                if (clientSocket == null) {
                    connectToServer();
                }
                Request deleteRequest = new Request(user.getUser(), "DeleteRequest");
                deleteRequest.setRequest(new FriendRequest(user, new User(aux[1].trim(), "")));
                //Enviamos el obj. request al servidor a través del socket
                clientSocket.sendRequestToServer(deleteRequest);
                String sms = clientSocket.receiveMessageFromServer();
                JOptionPane.showMessageDialog(this, sms,
                    "Process Status", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            
            
        }
    }//GEN-LAST:event_listMouseClicked

    private void searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMouseClicked
        this.search = true;
    }//GEN-LAST:event_searchMouseClicked

    private void logOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logOutMouseClicked
    if (this.mainFrame != null) {
            mainFrame.enableComponents(); // Llama al método en MainJFrame para mostrar los componentes
        }
        this.dispose();
    }//GEN-LAST:event_logOutMouseClicked

    private void activeComponents(int caso) {
        disableComponents();
        DefaultListModel<String> aux = new DefaultListModel<>();
        list.setModel(aux);
        contenedor1.setViewportView(list); 
        contenedor1.setVisible(true);
        if (caso == 1) {
            this.textToSearch.setVisible(true);
            this.searchBtn.setVisible(true);
        }
        if (caso == 2) {
            this.instruccion1.setVisible(true);
            list1.setModel(aux);
            this.list1.setVisible(true);
            contenedor2.setViewportView(list1); // Utiliza setViewportView para establecer la vista del JScrollPane
            contenedor2.setVisible(true);
        }
        this.list.setVisible(true);
        this.tittle.setVisible(true);
        this.instruccion.setVisible(true);
        online = false;
        foundUsers = false;
        
        // Llamamos a revalidate y repaint para actualizar la interfaz gráfica
         this.revalidate();
         this.repaint();
    }

    private void disableComponents() {
        this.contenedor1.setVisible(false);
        this.contenedor2.setVisible(false);
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
     *solicita cada 5 seg para refrescar pantalla
     */
    @Override
    public void run() {
        long start;
        long elapse;
        this.espera = 5000; // 5 segundos en milisegundos

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
        //actualizamos cada 10 seg
        this.getUserData(user.getUser());
        
        //Caso 1. 
        if (search == true && foundUsers==true ) {
            getFoundUsers();
            search = false;
        }
        //Caso 2.actualiza cada 10 seg en el hilo
        if (viewRequests==true) {
            getRequestData();
        }
        //Caso 3. Ver lista de amigos
       if (online == true) {
            getOnlineFriendsData(); 
            getFriendsData();
        }       
     
        
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

            //System.out.println(this.user.toString());
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
        this.list1.setModel(friendList);
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
                    //que no se agregue a si mismo
                    if (!request.getFoundUsers().get(i).getUser().equalsIgnoreCase(user.getUser())) {
                        //que no agregue amigos
                        //Caso 1
                        if (!user.getFriends().isEmpty()) {
                          for (int j = 0; j < user.getFriends().size(); j++) {
                              if (!user.getFriends().get(j).getUser().equalsIgnoreCase(request.getFoundUsers().get(i).getUser())) {
                                foundUsersList.addElement(request.getFoundUsers().get(i).getUser()); 
                              }
                          } 
                        //Caso 2  
                        }else{
                             foundUsersList.addElement(request.getFoundUsers().get(i).getUser());
                        }
                        
                    }
                }                         
                list.setModel(foundUsersList);
                //System.out.println(list.getComponents().toString());           
               
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("error btn search " + ex.getMessage());
            }

        }
    }

    //rellenamos con la lista de solicitudes enviadas y recibidas
    private void getRequestData() {
        DefaultListModel<String> requestList;
        //System.out.println("amigos\n"+ user.getFriends());
        if (user.getRequestSent()!= null) {
            //lista 1.
            requestList = new DefaultListModel<>();
            for (int i = 0; i < user.getRequestSent().size(); i++) {
                requestList.addElement(user.getRequestSent().get(i).showData(2));
            }
            this.list1.setModel(requestList);
        }
        if (user.getRequestRecieved() != null) {
            //lista 2    
            requestList = new DefaultListModel<>();
            for (int i = 0; i < user.getRequestRecieved().size(); i++) {
                requestList.addElement(user.getRequestRecieved().get(i).showData(1));
            }
            this.list.setModel(requestList);
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
    private javax.swing.JScrollPane contenedor1;
    private javax.swing.JScrollPane contenedor2;
    private javax.swing.JLabel copas1;
    private javax.swing.JMenuItem friendRequestBtn;
    private javax.swing.JMenu friendsOptions;
    private javax.swing.JMenu inicioUserName;
    private javax.swing.JLabel instruccion;
    private javax.swing.JLabel instruccion1;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
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
