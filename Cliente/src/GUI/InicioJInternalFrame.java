package GUI;

import java.io.IOException;
import java.util.ArrayList;
import Domain.*;
import javax.swing.*;
import Client.Client;
import static GUI.MainJFrame.clientSocket;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/*
 * La clase RegistrationJInternalFrame representa una interfaz gráfica para el registro de usuarios.
 * Esta clase extiende javax.swing.JInternalFrame para crear la ventana de registro.
 * Contiene atributos como el usuario (User) y una instancia de Client (clientSocket) para manejar la comunicación con el servidor.
 * @author reych
 */
public class InicioJInternalFrame extends JInternalFrame implements Runnable {
    private User user;
    private JLabel imageJLabel;
    private ServerRequest newRequest;
    private final MainJFrame mainFrame;
    private GameJInternalFrame game;
    private GameVsComputerInternalFrame gameVsComputer;
    private ArrayList<User> onlineFriends;
    //atributos necesarios para el hilo
    private boolean start;
    private long espera;
    private Thread thread;
    //atributos para iniciar comunicacion con el server (solicitud de datos)
    private boolean foundUsers;
    private boolean gameRequest;
    private boolean friends;
    private boolean friendRequest;
    private boolean search;

    /**
     * Creates new form RegistrationJInternalFrame
     *
     * @param mainFrame
     * @param userName
     */
    public InicioJInternalFrame(MainJFrame mainFrame, String userName) throws IOException {
        initComponents();
        disableComponents();
        this.lblUser.setVisible(true);
        this.getUserData(userName);

        this.search = false;
        this.foundUsers = false;
        this.gameRequest = false;
        this.friends = false;
        this.friendRequest = false;
        this.start = true;
        this.mainFrame = mainFrame; // Inicializa la referencia a MainJFrame
        this.imageJLabel = new JLabel();        
        //carga imagen de usuario
        getUserImage();
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public boolean getStart() {
        return start;
    }

    public JLabel getLblUser() {
        return lblUser;
    }

    @Override
    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public JDesktopPane getjDesktopPane2() {
        return jDesktopPane2;
    }

    public GameJInternalFrame getGame() {
        return game;
    }

    public GameVsComputerInternalFrame getGameVsComputer() {
        return gameVsComputer;
    }

    public void setGame(GameJInternalFrame game) {
        this.game = game;
    }

    public void setGameVsComputer(GameVsComputerInternalFrame gameVsComputer) {
        this.gameVsComputer = gameVsComputer;
    }

    public JLabel getImageJLabel() {
        return imageJLabel;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane2 = new javax.swing.JDesktopPane();
        btnSearch = new java.awt.Button();
        lblUser = new javax.swing.JLabel();
        lblTittle = new javax.swing.JLabel();
        lblInstruccion = new javax.swing.JLabel();
        txtUserToSearch = new javax.swing.JTextField();
        lblInstruccion2 = new javax.swing.JLabel();
        contenedor1 = new javax.swing.JScrollPane();
        list = new javax.swing.JList<>();
        contenedor2 = new javax.swing.JScrollPane();
        list1 = new javax.swing.JList<>();
        lblInstruccion3 = new javax.swing.JLabel();
        contenedor3 = new javax.swing.JScrollPane();
        list2 = new javax.swing.JList<>();
        menuBar = new javax.swing.JMenuBar();
        menuBtnInicio = new javax.swing.JMenu();
        menuBtnFriendsOptions = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        friendRequestBtn = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setBackground(new java.awt.Color(0, 0, 0));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        setTitle("Profile");
        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setMaximumSize(new java.awt.Dimension(700, 800));
        setMinimumSize(new java.awt.Dimension(700, 800));
        setNormalBounds(new java.awt.Rectangle(0, 0, 700, 800));
        setPreferredSize(new java.awt.Dimension(700, 800));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jDesktopPane2.setBackground(new java.awt.Color(255, 255, 255));

        btnSearch.setActionCommand("back");
        btnSearch.setBackground(new java.awt.Color(51, 0, 51));
        btnSearch.setFont(new java.awt.Font("Segoe Print", 0, 12)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setLabel("Search");
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchMouseClicked(evt);
            }
        });

        lblUser.setFont(new java.awt.Font("Segoe Print", 1, 16)); // NOI18N
        lblUser.setText("User");

        lblTittle.setFont(new java.awt.Font("Segoe Print", 1, 16)); // NOI18N
        lblTittle.setText("Online Friends");

        lblInstruccion.setFont(new java.awt.Font("Segoe Print", 1, 16)); // NOI18N
        lblInstruccion.setText("Select a user to send a friend request.");

        lblInstruccion2.setFont(new java.awt.Font("Segoe Print", 1, 16)); // NOI18N
        lblInstruccion2.setText("Select a user to send a friend request.");

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

        lblInstruccion3.setFont(new java.awt.Font("Segoe Print", 1, 16)); // NOI18N
        lblInstruccion3.setText("Select a user to send a friend request.");

        list2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 102, 102), new java.awt.Color(0, 204, 204), null, null));
        list2.setFont(new java.awt.Font("Segoe Print", 0, 16)); // NOI18N
        list2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                list2MouseClicked(evt);
            }
        });
        contenedor3.setViewportView(list2);

        jDesktopPane2.setLayer(btnSearch, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(lblUser, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(lblTittle, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(lblInstruccion, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(txtUserToSearch, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(lblInstruccion2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(contenedor1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(contenedor2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(lblInstruccion3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(contenedor3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUser, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addComponent(lblTittle)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(contenedor2, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblInstruccion, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblInstruccion2, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(contenedor1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblInstruccion3, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(contenedor3, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addComponent(txtUserToSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTittle)
                    .addComponent(lblUser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtUserToSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblInstruccion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contenedor1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblInstruccion2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(contenedor2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblInstruccion3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contenedor3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        getContentPane().add(jDesktopPane2);

        menuBtnInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/frameIcons/user.png"))); // NOI18N
        menuBtnInicio.setText("user");
        menuBtnInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuBtnInicioMouseClicked(evt);
            }
        });
        menuBar.add(menuBtnInicio);

        menuBtnFriendsOptions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/frameIcons/friends.png"))); // NOI18N
        menuBtnFriendsOptions.setText("Friends");

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/frameIcons/friends.png"))); // NOI18N
        jMenuItem4.setText("Friends list");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showFriendsActionPerformed(evt);
            }
        });
        menuBtnFriendsOptions.add(jMenuItem4);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/frameIcons/settings.png"))); // NOI18N
        jMenu1.setText("Requests");

        friendRequestBtn.setFont(new java.awt.Font("Segoe Print", 0, 16)); // NOI18N
        friendRequestBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/frameIcons/request.png"))); // NOI18N
        friendRequestBtn.setText("Add friends");
        friendRequestBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                friendRequestBtnActionPerformed(evt);
            }
        });
        jMenu1.add(friendRequestBtn);

        jMenuItem1.setFont(new java.awt.Font("Segoe Print", 0, 16)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/frameIcons/label.png"))); // NOI18N
        jMenuItem1.setText("Accept/delete friend requests");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AceptDeleteRequestesActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setFont(new java.awt.Font("Segoe Print", 0, 16)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/frameIcons/game-control.png"))); // NOI18N
        jMenuItem2.setText("Game requests");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gameRequestActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        menuBtnFriendsOptions.add(jMenu1);

        menuBar.add(menuBtnFriendsOptions);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/frameIcons/chess.png"))); // NOI18N
        jMenu3.setText("Play");

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/frameIcons/personal-computer.png"))); // NOI18N
        jMenuItem3.setText("Play vs computer");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playVsComputerActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        menuBar.add(jMenu3);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/frameIcons/logout.png"))); // NOI18N
        jMenu2.setText("Log out");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logOutMouseClicked(evt);
            }
        });
        menuBar.add(jMenu2);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //volvemos a la pag principal
    private void menuBtnInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuBtnInicioMouseClicked
        this.disableComponents();
    }//GEN-LAST:event_menuBtnInicioMouseClicked

    //visualizamos amigos en linea para enviar solicitudes de juego,  aceptamos o rechazamos solicitudes
    private void gameRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gameRequestActionPerformed
        activeComponents(3);
        this.lblTittle.setText("Game Request");
        this.lblInstruccion.setText("Online friends. Select a friend to send a game request.");
        this.lblInstruccion2.setText("Request recieved.");
        this.lblInstruccion3.setText("Request Sent.");
        this.gameRequest = true;
    }//GEN-LAST:event_gameRequestActionPerformed

    //Btn Add friend, enviamos solicitud de amistad
    private void friendRequestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_friendRequestBtnActionPerformed
        activeComponents(1);
        this.lblTittle.setText("Send a friend request");
        this.lblInstruccion.setText("Select a user to send a friend request.");
        this.foundUsers = true;
    }//GEN-LAST:event_friendRequestBtnActionPerformed

    //visualizamos las solicitudes y aceptamos o borramos
    private void AceptDeleteRequestesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AceptDeleteRequestesActionPerformed
        activeComponents(2);
        this.lblTittle.setText("Friend request");
        this.lblInstruccion.setText("Received request:");
        this.lblInstruccion2.setText("Sent request:");
        this.friendRequest = true;
    }//GEN-LAST:event_AceptDeleteRequestesActionPerformed

    //Control de lista 2
    //Caso 1: Eliminar solicitud de amistad enviada
    //Caso 2: Eliminar/aceptar solicitud de juego recibida
    private void list1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_list1MouseClicked
        this.controlSecondList();
    }//GEN-LAST:event_list1MouseClicked

    //Control de lista 1.
    //Caso 1: Eliminar amigos, 
    //Caso 2: Add friends, 
    //Caso 3. Aceptar/eliminar solicitudes de amistad recibidas
    //Caso 4. Aceptar/eliminar solicitudes de juego recibidas
    private void listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listMouseClicked
        this.controlFirstList();
    }//GEN-LAST:event_listMouseClicked

    //buscamos usuario
    private void searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMouseClicked
        this.search = true;
    }//GEN-LAST:event_searchMouseClicked

    //cierre de sesion
    private void logOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logOutMouseClicked
        if (this.mainFrame != null) {
            mainFrame.enableComponents(); // Llama al método en MainJFrame para mostrar los componentes
        }
        this.dispose();
    }//GEN-LAST:event_logOutMouseClicked

    //mostramos data de amigos   
    private void showFriendsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showFriendsActionPerformed
        activeComponents(0);
        this.lblTittle.setText("Friends");
        this.lblInstruccion.setText("Select a user to delete the friendship.");
        friends = true;
    }//GEN-LAST:event_showFriendsActionPerformed

    //control de lista 2
    private void list2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_list2MouseClicked
        controlThirdList();
    }//GEN-LAST:event_list2MouseClicked

    private void playVsComputerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playVsComputerActionPerformed
        this.disableComponents();
        this.menuBar.setVisible(false);
        this.start = false;
        gameVsComputer = new GameVsComputerInternalFrame(this, this.user.getUser());
        gameVsComputer.setVisible(true);
        disableInternalFrameMove(gameVsComputer);
        this.jDesktopPane2.add(gameVsComputer);
        imageJLabel.setVisible(false);
    }//GEN-LAST:event_playVsComputerActionPerformed

    //habilitamos los componentes que necesitamos
    private void activeComponents(int caso) {
        disableComponents();
        this.lblUser.setVisible(true);
        DefaultListModel<String> aux = new DefaultListModel<>();
        list.setModel(aux);
        contenedor1.setViewportView(list);
        contenedor1.setVisible(true);
        if (caso == 1) {
            this.txtUserToSearch.setVisible(true);
            this.btnSearch.setVisible(true);
        }
        if (caso == 2) {
            this.lblInstruccion2.setVisible(true);
            list1.setModel(aux);
            this.list1.setVisible(true);
            contenedor2.setViewportView(list1); // 
            contenedor2.setVisible(true);
        }
        if (caso == 3) {
            this.lblInstruccion2.setVisible(true);
            list1.setModel(aux);
            this.list1.setVisible(true);
            contenedor2.setViewportView(list1); // 
            contenedor2.setVisible(true);
            this.lblInstruccion3.setVisible(true);
            list2.setModel(aux);
            this.list2.setVisible(true);
            contenedor3.setViewportView(list2); // 
            contenedor3.setVisible(true);
        }
        this.list.setVisible(true);
        this.lblTittle.setVisible(true);
        this.lblInstruccion.setVisible(true);
        gameRequest = false;
        foundUsers = false;

        // Llamamos a revalidate y repaint para actualizar la interfaz gráfica
        this.revalidate();
        this.repaint();
    }

    //desactivamos los componentes.
    private void disableComponents() {
        this.lblUser.setVisible(false);
        this.txtUserToSearch.setText("");
        this.contenedor1.setVisible(false);
        this.contenedor2.setVisible(false);
        this.contenedor3.setVisible(false);
        this.list.setVisible(false);
        this.list1.setVisible(false);
        this.list2.setVisible(false);
        this.lblTittle.setVisible(false);
        this.txtUserToSearch.setVisible(false);
        this.lblInstruccion.setVisible(false);
        this.lblInstruccion2.setVisible(false);
        this.lblInstruccion3.setVisible(false);
        this.btnSearch.setVisible(false);
        friends = false;
        gameRequest = false;
        foundUsers = false;
        friendRequest = false;
    }

    /*
     *solicita cada 5 seg para refrescar pantalla
     */
    @Override
    public void run() {
        long started;
        long elapse;
        this.espera = 5000; // 5 segundos en milisegundos

        while (start) {
            repaint();
            started = System.nanoTime();

            elapse = System.nanoTime() - started;
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
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }//addNotify

    private void getCompleteData() {
        //actualizamos cada 5 seg
        this.getUserData(user.getUser());

        //Caso 1. 
        if (search == true && foundUsers == true) {
            getFoundUsers();
            search = false;
        }
        //Caso 2.actualiza cada 5 seg en el hilo
        if (friendRequest == true) {
            getRequestData();
        }
        //Caso 3. Ver lista de amigos y solicitudes
        if (gameRequest == true) {
            try {
                getOnlineFriendsData();
                getGameRequestData();
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("GAME REQUEST ERROR");
            }
        }
        if (friends == true) {
            getFriendsData();
        }

    }

    //solicitamos y actualizamos data del usuario
    private void getUserData(String user) {
        try {

            //solicitamos los datos de usuario
            newRequest = new ServerRequest(user, "Get user data");
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

            //verifica el estado del juego
            getGameValidation();
           
            if (imageJLabel!=null) {
               this.imageJLabel.setVisible(true);
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("error de coneccion");
        }

    }

    private void getGameValidation() {
        try {
            //validamos inicio de juego
            newRequest = new ServerRequest(user.getUser(), "getGameValidation");
            if (clientSocket == null) {
                connectToServer();
            }
            //Enviamos el obj. request al servidor a través del socket
            clientSocket.sendRequestToServer(newRequest);
            Object receivedObject = clientSocket.getEntrada().readObject();
            newRequest = (ServerRequest) receivedObject;

            if (newRequest.getEnemy() != null && newRequest.getEnemy().isGameState() == true
                    && newRequest.isGameState() == true) {
                this.disableComponents();
                this.menuBar.setVisible(false);
                this.imageJLabel.setVisible(false);
                this.jDesktopPane2.remove(this.imageJLabel);
                this.start = false;
                game = new GameJInternalFrame(this, this.user.getUser());
                game.setVisible(true);
                disableInternalFrameMove(game);
                this.jDesktopPane2.add(game);

            }

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(InicioJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

      //mostramos la imagen del usuario
    private void getUserImage() {
    GenericClass gC = new GenericClass();
    String base64Imagen = this.user.getImageBase64();
    BufferedImage imagen = gC.decodeBase64ToImage(base64Imagen);
    
    if (imagen != null) {
        ImageIcon imageIcon = new ImageIcon(imagen);
        
        // Redimensiona la imagen al tamaño deseado (150x150)
        Image scaledImage = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
        // Configura el JLabel con la imagen redimensionada
        imageJLabel.setIcon(scaledIcon);
        Dimension size = imageJLabel.getPreferredSize();
        int xPosition = 7;  // Ajusta la posición X según tus necesidades
        int yPosition = 60; // Coloca la imagen debajo del lblUser con un espacio de 10
        imageJLabel.setBounds(xPosition, yPosition, 150, 150); // Tamaño fijo de 150x150
        imageJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageJLabel.setVerticalAlignment(SwingConstants.CENTER);
        
        // Agregar el JLabel al jDesktopPane2
        jDesktopPane2.add(imageJLabel);
        
        // Asegura que jDesktopPane2 sea visible
        jDesktopPane2.setVisible(true);
    }
}

    //mostramos la info del usuario
    private void showData(User user) {
        lblUser.setText(user.getUser().toUpperCase());
        this.menuBtnInicio.setText("Main page");
    }

    //rellenamos la lista con GameRequest del/para usuario
    private void getGameRequestData() throws IOException, ClassNotFoundException {
        newRequest = new ServerRequest(user.getUser(), "GetGameRequestData");
        if (clientSocket == null) {
            connectToServer();
        }
        //Enviamos el obj. request al servidor a través del socket
        clientSocket.sendRequestToServer(newRequest);

        //recibimos el objeto del usuario
        Object receivedObject = clientSocket.getEntrada().readObject();
        newRequest = (ServerRequest) receivedObject;

        DefaultListModel<String> request = new DefaultListModel<>();
        for (int i = 0; i < newRequest.getGameRequestSent().size(); i++) {
            request.addElement(newRequest.getGameRequestSent().get(i).showData(2));
        }
        this.list2.setModel(request);

        request = new DefaultListModel<>();
        for (int i = 0; i < newRequest.getGameRequestRecieved().size(); i++) {
            request.addElement(newRequest.getGameRequestRecieved().get(i).showData(1));
        }
        this.list1.setModel(request);
    }

    //rellenamos la lista con amigos del usuario
    private void getFriendsData() {
        try {
            newRequest = new ServerRequest(user.getUser(), "GetFriendsData");
            if (clientSocket == null) {
                connectToServer();
            }
            //Enviamos el obj. request al servidor a través del socket
            clientSocket.sendRequestToServer(newRequest);
            //recibimos el objeto del usuario
            Object receivedObject = clientSocket.getEntrada().readObject();
            newRequest = (ServerRequest) receivedObject;

            //System.out.println(newRequest.getFriends().size()+"*");
            DefaultListModel<String> friendList = new DefaultListModel<>();
            for (int i = 0; i < newRequest.getFriends().size(); i++) {
                friendList.addElement(newRequest.getFriends().get(i).toString());
            }
            this.list.setModel(friendList);
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("error al solicitar friends data");
        }
    }

    //rellenamos la lista con amigos en linea del usuario    
    private void getOnlineFriendsData() {
        try {
            //solicitamos los datos de usuario
            newRequest = new ServerRequest(user.getUser(), "Get online users");
            if (clientSocket == null) {
                connectToServer();
            }
            //Enviamos el obj. request al servidor a través del socket
            clientSocket.sendRequestToServer(newRequest);

            //recibimos el objeto del usuario
            Object receivedObject = clientSocket.getEntrada().readObject();

            ServerRequest request = (ServerRequest) receivedObject;

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
        String userToSearch = txtUserToSearch.getText();
        if (!userToSearch.isEmpty()) {

            try {
                //solicitamos los datos de usuario
                if (clientSocket == null) {
                    connectToServer();
                }

                //Enviamos el obj. request al servidor a través del socket
                clientSocket.sendRequestToServer(new ServerRequest(userToSearch, "search users"));

                //recibimos el objeto del usuario
                Object receivedObject = clientSocket.getEntrada().readObject();

                ServerRequest request = (ServerRequest) receivedObject;
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
                        } else {
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
        try {
            newRequest = new ServerRequest(user.getUser(), "GetFriendRequestData");
            if (clientSocket == null) {
                connectToServer();
            }
            //Enviamos el obj. request al servidor a través del socket
            clientSocket.sendRequestToServer(newRequest);
            //recibimos el objeto del usuario
            Object receivedObject = clientSocket.getEntrada().readObject();
            newRequest = (ServerRequest) receivedObject;

            DefaultListModel<String> requestList;
            //System.out.println("amigos\n"+ user.getFriends());
            if (user.getRequestSent() != null) {
                //lista 1.
                requestList = new DefaultListModel<>();
                for (int i = 0; i < newRequest.getFriendRequestSent().size(); i++) {
                    requestList.addElement(newRequest.getFriendRequestSent().get(i).showData(2));
                }
                this.list1.setModel(requestList);
            }
            if (user.getRequestRecieved() != null) {
                //lista 2
                requestList = new DefaultListModel<>();
                for (int i = 0; i < newRequest.getFriendRequestRecieved().size(); i++) {
                    requestList.addElement(newRequest.getFriendRequestRecieved().get(i).showData(1));
                }
                this.list.setModel(requestList);
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("error get friend request data");
        }
    }

    //control de la primer lista
    private void controlFirstList() {
        String selectedValue = list.getSelectedValue();
        //Caso 1. Eliminar amigos
        if (selectedValue != null & friends == true) {
            if (clientSocket == null) {
                connectToServer();
            }

            int delete = JOptionPane.showConfirmDialog(this, "Remove " + selectedValue.toUpperCase() + "?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (delete == JOptionPane.YES_OPTION) {
                ServerRequest deleteFriend = new ServerRequest(user.getUser(), "DeleteFriend");
                deleteFriend.setFriend(selectedValue.trim());
                //Enviamos el obj. request al servidor a través del socket
                clientSocket.sendRequestToServer(deleteFriend);
                String sms = clientSocket.receiveMessageFromServer();
                JOptionPane.showMessageDialog(this, sms,
                        "Process Status", JOptionPane.INFORMATION_MESSAGE);
            }

        }

        //Caso 2. send a Friend request
        if (selectedValue != null & foundUsers == true) {
            try {
                this.getUserData(user.getUser());
                boolean valid = true;
                newRequest = new ServerRequest(user.getUser(), "GetFriendsData");
                if (clientSocket == null) {
                    connectToServer();
                }
                //Enviamos el obj. request al servidor a través del socket
                clientSocket.sendRequestToServer(newRequest);
                //recibimos el objeto del usuario
                Object receivedObject = clientSocket.getEntrada().readObject();
                newRequest = (ServerRequest) receivedObject;

                for (int i = 0; i < newRequest.getFriends().size(); i++) {
                    if (newRequest.getFriends().get(i).getUser().equalsIgnoreCase(selectedValue)) {
                        valid = false;
                    }
                }
                if (valid == true) {

                    //verificamos que el usuario este seguro
                    int response = JOptionPane.showConfirmDialog(this, "Send request to " + selectedValue + "?",
                            "Confirmation",
                            JOptionPane.YES_NO_OPTION);

                    if (response == JOptionPane.YES_OPTION) {

                        if (clientSocket == null) {
                            connectToServer();
                        }

                        ServerRequest request = new ServerRequest(user.getUser(), "requestSent");
                        request.setRequest(new FriendRequest(user, new User(selectedValue, "")));
                        //System.out.println(request.getRequest().showData(1));
                        //Enviamos el obj. request al servidor a través del socket

                        clientSocket.sendRequestToServer(request);
                        String message = clientSocket.receiveMessageFromServer();
                        JOptionPane.showMessageDialog(this, message,
                                "Process Status", JOptionPane.INFORMATION_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(this, selectedValue.toUpperCase() + " has accepted your friend request.",
                            "Process Status", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (IOException ex) {
                Logger.getLogger(InicioJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(InicioJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Caso 3. Aceptar solicitudes de amistad recibidas
        if (selectedValue != null & friendRequest == true) {
            //verificamos decision
            Object[] opciones = {"Accept", "Delete", "Cancel"};
            String[] aux = selectedValue.split(":");
            int seleccion = JOptionPane.showOptionDialog(null, "Choose an option.", "Friend Request", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            switch (seleccion) {
                case 0 -> {
                    // Aceptar
                    if (clientSocket == null) {
                        connectToServer();
                    }
                    ServerRequest acceptRequest = new ServerRequest(user.getUser(), "AcceptRequest");
                    acceptRequest.setRequest(new FriendRequest(user, new User(aux[1].trim(), "")));
                    //Enviamos el obj. request al servidor a través del socket
                    clientSocket.sendRequestToServer(acceptRequest);
                    String message = clientSocket.receiveMessageFromServer();
                    JOptionPane.showMessageDialog(this, message,
                            "Process Status", JOptionPane.INFORMATION_MESSAGE);
                }
                case 1 -> {
                    // Borrar
                    if (clientSocket == null) {
                        connectToServer();
                    }
                    ServerRequest deleteRequest = new ServerRequest(user.getUser(), "DeleteRequest");
                    deleteRequest.setRequest(new FriendRequest(user, new User(aux[1].trim(), "")));
                    //Enviamos el obj. request al servidor a través del socket
                    clientSocket.sendRequestToServer(deleteRequest);
                    String sms = clientSocket.receiveMessageFromServer();
                    JOptionPane.showMessageDialog(this, sms,
                            "Process Status", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        //Caso 4. Aceptar solicitudes de juego recibidas
        if (selectedValue != null & gameRequest == true) {
            int response = JOptionPane.showConfirmDialog(this, "Send request to " + selectedValue + "?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION);

            //enviamos solicitud de juego
            if (response == JOptionPane.YES_OPTION) {
                ServerRequest gameRequest = new ServerRequest(user.getUser(), "sentGameRequest");
                gameRequest.setGameRequest(new GameRequest(new User(user.getUser(), ""), new User(selectedValue, "")));
                //Enviamos el obj. request al servidor a través del socket
                clientSocket.sendRequestToServer(gameRequest);
                String sms = clientSocket.receiveMessageFromServer();
                JOptionPane.showMessageDialog(this, sms,
                        "Process Status", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void controlSecondList() {
        String selectedValue = list1.getSelectedValue();

        //Caso 1. eliminamos solicitudes de amistad enviadas
        if (selectedValue != null & friendRequest == true) {
            String[] aux = selectedValue.split(":");
            int delete = JOptionPane.showConfirmDialog(this, "Remove friend request sent to\n"
                    + aux[1].toUpperCase() + "?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (delete == JOptionPane.YES_OPTION) {

                if (clientSocket == null) {
                    connectToServer();
                }
                ServerRequest deleteRequest = new ServerRequest(user.getUser(), "DeleteRequestSent");
                deleteRequest.setRequest(new FriendRequest(user, new User(aux[1].trim(), "")));
                //Enviamos el obj. request al servidor a través del socket
                clientSocket.sendRequestToServer(deleteRequest);
                String sms = clientSocket.receiveMessageFromServer();
                JOptionPane.showMessageDialog(this, sms,
                        "Process Status", JOptionPane.INFORMATION_MESSAGE);

            }
        }
        //Caso 2. eliminamos o aceptamos solicitudes de juego recibidas
        if (selectedValue != null & gameRequest == true) {
            String[] aux = selectedValue.split(":");
            Object[] opciones = {"Accept", "Delete", "Cancel"};
            int seleccion = JOptionPane.showOptionDialog(null, "Choose an option.", "Game Request",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            switch (seleccion) {
                //aceptar
                case 0 -> {
                    if (clientSocket == null) {
                        connectToServer();
                    }
                    ServerRequest acceptRequest = new ServerRequest(user.getUser(), "AcceptGameRequest");
                    acceptRequest.setGameRequest(new GameRequest(user, new User(aux[1].trim(), "")));
                    //Enviamos el obj. request al servidor a través del socket
                    clientSocket.sendRequestToServer(acceptRequest);

                }
                //borrar
                case 1 -> {
                    if (clientSocket == null) {
                        connectToServer();
                    }
                    ServerRequest removeRequest = new ServerRequest(user.getUser(), "RemoveGameRequest");
                    removeRequest.setGameRequest(new GameRequest(user, new User(aux[1].trim(), "")));
                    clientSocket.sendRequestToServer(removeRequest);
                    String sms = clientSocket.receiveMessageFromServer();
                    JOptionPane.showMessageDialog(this, sms,
                            "Process Status", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        }
    }

    private void controlThirdList() {
        String selectedValue = list2.getSelectedValue();
        if (selectedValue != null & gameRequest == true) {
            String[] aux = selectedValue.split(":");
            Object[] opciones = {"Delete", "Cancel"};
            int seleccion = JOptionPane.showOptionDialog(null, "Choose an option.", "Game Request",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            if (seleccion == 0) {
                if (clientSocket == null) {
                    connectToServer();
                }
                ServerRequest removeRequest = new ServerRequest(user.getUser(), "RemoveGameRequestRecieved");
                removeRequest.setGameRequest(new GameRequest(user, new User(aux[1].trim(), "")));
                clientSocket.sendRequestToServer(removeRequest);
                String sms = clientSocket.receiveMessageFromServer();
                JOptionPane.showMessageDialog(this, sms,
                        "Process Status", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void disableInternalFrameMove(GameJInternalFrame frame) {
        // Deshabilitar el comportamiento de arrastrar y mover para el RegistrationJInternalFrame
        frame.setBorder(null);
        BasicInternalFrameUI ui = (BasicInternalFrameUI) frame.getUI();
        Component northPane = ui.getNorthPane();
        MouseMotionListener[] motionListeners = (MouseMotionListener[]) northPane.getListeners(MouseMotionListener.class);
        for (MouseMotionListener listener : motionListeners) {
            northPane.removeMouseMotionListener(listener);
        }

    }

    //inmoviliza los internal frames
    private void disableInternalFrameMove(GameVsComputerInternalFrame frame) {
        // Deshabilitar el comportamiento de arrastrar y mover para el RegistrationJInternalFrame
        frame.setBorder(null);
        BasicInternalFrameUI ui = (BasicInternalFrameUI) frame.getUI();
        Component northPane = ui.getNorthPane();
        MouseMotionListener[] motionListeners = (MouseMotionListener[]) northPane.getListeners(MouseMotionListener.class);
        for (MouseMotionListener listener : motionListeners) {
            northPane.removeMouseMotionListener(listener);
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

    //cierre seguro
    @Override
    public void dispose() {
        this.start = false;
        if (clientSocket != null) {
            // Envia el mensaje al servidor indicando que el usuario se está desconectando
            clientSocket.sendRequestToServer(new ServerRequest(this.user.getUser(), "log out"));
        }

        if (this.mainFrame != null) {
            mainFrame.enableComponents();
        }

        super.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnSearch;
    private javax.swing.JScrollPane contenedor1;
    private javax.swing.JScrollPane contenedor2;
    private javax.swing.JScrollPane contenedor3;
    private javax.swing.JMenuItem friendRequestBtn;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JLabel lblInstruccion;
    private javax.swing.JLabel lblInstruccion2;
    private javax.swing.JLabel lblInstruccion3;
    private javax.swing.JLabel lblTittle;
    private javax.swing.JLabel lblUser;
    private javax.swing.JList<String> list;
    private javax.swing.JList<String> list1;
    private javax.swing.JList<String> list2;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuBtnFriendsOptions;
    private javax.swing.JMenu menuBtnInicio;
    private javax.swing.JTextField txtUserToSearch;
    // End of variables declaration//GEN-END:variables

}
