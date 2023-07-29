package Server;

import Domain.ServerRequest;
import Domain.User;
import FileControl.UserFile;
import Utility.Utility;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

/*@author reych
 *Esta clase llamada Handler extiende de la clase Thread 
 *  y se utiliza para manejar la comunicación con un cliente
 *  en el servidor.
 * Representa el manejo de la comunicación con un cliente en el servidor

 * Variables de instancia:
 *send: Un objeto PrintStream utilizado para enviar datos al cliente a través del socket.
 *receive: Un objeto BufferedReader utilizado para recibir datos del cliente a través del socket.
 *socket: El socket utilizado para la comunicación con el cliente.
 *lectura: Una cadena que almacena los datos leídos del cliente.
 *sesionIniciada: Un booleano que indica si la sesión con el cliente está iniciada o no.
 *salida: Un objeto ObjectOutputStream utilizado para enviar objetos al cliente a través del socket.
 *entrada: Un objeto ObjectInputStream utilizado para recibir objetos del cliente a través del socket.
 *mensaje: Un arreglo de cadenas utilizado para almacenar los mensajes recibidos del cliente.*/
public class Handler extends Thread {
    
    private PrintStream send;
    private BufferedReader receive;
    private Socket socket;
    private String lectura;
    private boolean sesionIniciada;
    public ObjectOutputStream salida;
    public ObjectInputStream entrada;
    private String[] mensaje;

    //atributos db Data
    private ArrayList<User> usersList;
    private Utility utility;

    //atributos para manejo de archivos
    private ArrayList<User>usersData;
    private UserFile file;
    
    //atributos auxiliares
    private User user;
    private User userRegister;
    private ServerRequest request;
    
    /*El constructor recibe un objeto Socket como parámetro, que representa la conexión establecida con el cliente.
     *Inicializa las variables de instancia socket, send y receive con los objetos proporcionados por el socket.
     *Establece sesionIniciada como true para indicar que la sesión está iniciada.
     */
    public Handler(Socket socket) throws IOException {
        this.socket = socket;
        this.salida = new ObjectOutputStream(this.socket.getOutputStream());
        this.send = new PrintStream(this.socket.getOutputStream());
        this.receive = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.sesionIniciada = true;
        initInstances();
    } // constructor

    
    private void initInstances() throws IOException {
        utility = new Utility();
        
        usersList = DBget.DBget.getUsers("");
        
        file=new UserFile();
        usersData = file.getUsers();
    }

    /*Método run():
     *Este método se ejecuta cuando se inicia el hilo del manejador (Clase handler).
     *Este bucle se ejecuta mientras sesionIniciada sea true.
     */
// Dentro de la clase Handler
    @Override
    public void run() {
        try {
            entrada = new ObjectInputStream(socket.getInputStream()); // Inicializar el ObjectInputStream

            while (sesionIniciada) {

                Object receivedObject = entrada.readObject();
        //Register/log in        
                if (receivedObject instanceof User) {
                   //actualizamos por si hay otro usuario
                    usersList = DBget.DBget.getUsers("");
                    
                    userRegister = (User) receivedObject;

            //Caso 1. Registro
                    if (userRegister.getAction().equals("registration")) {
                    //actualizamos por si hay otro usuario
                    usersList = DBget.DBget.getUsers("");                       
                        
                    //verificamos que no exista un usuario igual
                        
                        //caso 1.1 guardamos
                        if (utility.search(usersList, userRegister.getUser()) == false) {
                            
                                                      
                            DBsave.DBsave.addUser("user, password",
                                    ("'" + userRegister.getUser() + "', '" 
                                            + userRegister.getPassword() + "'"));

                            sendMessageToClient("Successful process.");

                            file.saveUser(userRegister);
                            
                            
                         //caso 1.2 no guardamos   
                        } else if (utility.search(usersList, userRegister.getUser()) == true) {
                            sendMessageToClient("Existing user");
                        }
                    }
                    
            //Caso 2. Log in
                    if (userRegister.getAction().equals("loggin")) {
                        //actualizamos en caso de ser necesario por si hay user nuevo
                        usersList = DBget.DBget.getUsers("");

                        if (utility.search(usersList, userRegister.getUser()) == true
                                && utility.verifyPassword(usersList, userRegister.getPassword())==true) {
                            if (utility.search(Server.onlineUsers, userRegister.getUser())==false) {
                                Server.onlineUsers.add(file.getUser(userRegister.getUser()));
                                sendMessageToClient("init");
                            }else{
                                sendMessageToClient("You are already online");                                
                            }
                         
                          // System.out.println(onlineUsers.toString());
                        } else if(utility.search(usersList, userRegister.getUser()) == true
                                && utility.verifyPassword(usersList, userRegister.getPassword())==false){
                            sendMessageToClient("Incorrect password");
                        }else if(utility.search(usersList, userRegister.getUser()) == false
                                && utility.verifyPassword(usersList, userRegister.getPassword())==true){
                            sendMessageToClient("Incorrect User");
                        }else if(utility.search(usersList, userRegister.getUser()) == false
                                && utility.verifyPassword(usersList, userRegister.getPassword())==false){
                            sendMessageToClient("User does not exist");                            
                        }
                        
                    }
                }//fin if if(object instanceof User
             
        //User data        
                if (receivedObject instanceof ServerRequest) {
                    request=(ServerRequest)receivedObject;
                //Caso 1. Profile data    
                    if (request.getAction().equalsIgnoreCase("Get user data")) {
                        sendUserToServer(utility.getUser(request.getUser())); 
                    }
                //Caso 2. Online users
                    if (request.getAction().equalsIgnoreCase("Get online users")) {
                        user=file.getUser(request.getUser());
                        request.setOnlineUsers(utility.getOnlineFriends(Server.onlineUsers, user));
                        sendOnlineUserToServer(request); 
                    }                   
                //Caso 3. Log out
                    if (request.getAction().equalsIgnoreCase("log out")) {
                        utility.cleanGameRequest(new User(request.getUser(),""));
                        utility.logOut(Server.onlineUsers, request.getUser());
                     //   System.out.println("successful log out");
                    }
                
                //Caso 4. Search users, no envia usuarios que son amigos
                    if (request.getAction().equalsIgnoreCase("search users")) {
                     request.setFoundUsers(utility.getFoundUsers(
                             file.getUsers(),
                             request.getUser()));
                     sendRequest(request);
                    }
                //Caso 5.  Enviar solicitud
                    if (request.getAction().equalsIgnoreCase("requestSent")) {
                        //actualizamos
                        file.actualizaLista();
                        //guardamos en el archivo. Revisa que no exista la solicitud
                        if (utility.verifyFriendRequestSent(
                                file.getUser(request.getRequest().getRequestBy().getUser()), 
                                file.getUser(request.getRequest().getRequestFor().getUser()),
                                request.getRequest())==false) {
                                                        
                            file.updateUserRequests(
                                   new User(request.getRequest().getRequestBy().getUser(),""), 
                                    new User(request.getRequest().getRequestFor().getUser(),""),
                                    request.getRequest());
                         
                        //enviamos mensaje
                        sendMessageToClient("Friend request sent successfully");
                        }else{
                        sendMessageToClient("Error, previously sent");       
                        }                                            
                    }
                //Caso 6. Aceptar solicitud
                    if (request.getAction().equalsIgnoreCase("AcceptRequest")) {
                        //actualizamos
                        //actualizamos
                        file.actualizaLista();
                        file.deleteFriendRequestSent(request.getRequest().getRequestFor(),request.getRequest().getRequestBy());
                        file.deleteFriendRequestSent(request.getRequest().getRequestBy(),request.getRequest().getRequestFor());
                        file.addFriend(request.getRequest().getRequestBy(), request.getRequest().getRequestFor());
                        file.addFriend(request.getRequest().getRequestFor(), request.getRequest().getRequestBy());
                        sendMessageToClient("Friend added");                                                
                    }
                    
                //Caso 7. Eliminar solicitudes recibidas
                    if (request.getAction().equalsIgnoreCase("DeleteRequest")) {
                        file.deleteFriendRequestSent(request.getRequest().getRequestFor(),request.getRequest().getRequestBy());
                        sendMessageToClient("Friend request deleted");
                    } 
                //Caso 8. Eliminar solicitudes enviadas    
                    if (request.getAction().equalsIgnoreCase("DeleteRequestSent")) {
                     file.deleteFriendRequestSent(request.getRequest().getRequestBy(), request.getRequest().getRequestFor());
                        sendMessageToClient("Friend request deleted");   
                    }             
                //Caso 9. Eliminar amigos
                    if (request.getAction().equalsIgnoreCase("DeleteFriend")) {
                        file.removeFriend(request.getUser(),request.getFriend());
                        file.removeFriend(request.getFriend(),request.getUser());
                        sendMessageToClient("Friend deleted");
                    }
                //Caso 10. Enviar solicitud de juego (memoria temporal) 
                    if (request.getAction().equalsIgnoreCase("sentGameRequest")) {
                        //creamos la solicitud en la lista de usuarios en linea porque es memoria temporal
                        if (utility.verifyGameRequest(
                                request.getGameRequest().getRequestBy().getUser(),
                                request.getGameRequest().getRequestFor().getUser())==false) {
                                                  utility.addGameRequest(request.getGameRequest());
                          sendMessageToClient("Game request sent");
                        }else{
                          sendMessageToClient("Error, previously sent");
                        }
                        
                    }
                //Caso 11. Aceptar solicitud de juego recibida. 
                    if (request.getAction().equalsIgnoreCase("AcceptGameRequest")) {
                    utility.acceptGameRequest(request.getGameRequest().getRequestBy(),
                            request.getGameRequest().getRequestFor());
                       // System.out.println(utility.getUser(request.getGameRequest().getRequestBy().getUser()).isGameState());
                    }
                //Caso 12. Eliminar solicitud de juego recibida.  
                    if (request.getAction().equalsIgnoreCase("RemoveGameRequest")) {
                    utility.deleteGameRequestRecieved(request.getGameRequest().getRequestBy(),
                            request.getGameRequest().getRequestFor());
                        sendMessageToClient("Game request removed");
                    }
                //Caso 13. Obtener game request data
                    if (request.getAction().equalsIgnoreCase("GetGameRequestData")) {
                        request.setGameRequestSent(
                                utility.getGameRequestSent(
                                        Server.onlineUsers, new User(request.getUser(),"")));
                        
                        request.setGameRequestRecieved(
                                utility.getGameRequestRecieved(
                                        Server.onlineUsers, new User(request.getUser(),"")));
                        
                        sendRequest(request);
                    }
                //Caso 14. Obtener friend request data
                    if (request.getAction().equalsIgnoreCase("GetFriendRequestData")) {
                        request.setFriendRequestSent(
                                utility.getFriendRequestSent(
                                        file.getUsers(), new User(request.getUser(),"")));
                        
                        request.setFriendRequestRecieved(
                                utility.getFriendRequestRecieved(
                                        file.getUsers(), new User(request.getUser(),"")));     
                        sendRequest(request);
                    }
                //Caso 15. Obtener friends data 
                    if (request.getAction().equalsIgnoreCase("GetFriendsData")) {
                        request.setFriends(utility.getFriendData(file.getUsers(), new User(request.getUser(),"")));
                        sendRequest(request);
                    }
                //Caso 16. Eliminar solicitud de juego enviada.  
                    if (request.getAction().equalsIgnoreCase("RemoveGameRequestRecieved")) {
                    utility .deleteGameRequestSent(request.getGameRequest().getRequestBy(),
                            request.getGameRequest().getRequestFor());
                        sendMessageToClient("Game request removed");
                    }    
                //Caso 17. valida inicio de juego 
                    if (request.getAction().equalsIgnoreCase("getGameValidation")) {
                    //pedimos el usuario y el gameState
                    
                    
                    }
                }

                //actualizamos
                file.actualizaLista();

            }
            
            
        } catch (IOException e) {
            System.out.println("Cliente cierra el programa");
        } catch (ClassNotFoundException ex) {
            System.out.println("ex=" + ex.getMessage());
        } finally {
            try {
                if (entrada != null) {
                    entrada.close();
                }
                if (salida != null) {
                    salida.close();
                }
                socket.close();
            } catch (IOException e) {
                System.out.println("Error clase handler al cerrar conexion");
            }
        }
    }
    
    //Metodo para enviar string
    private void sendMessageToClient(String message) {
        try {
            this.salida.writeObject(message);
            this.salida.flush();
        } catch (IOException ex) {
            System.out.println("Error al enviar el mensaje al cliente: " + ex.getMessage());
        }
    }
     
    //Método para enviar un objeto User al servidor
    public void sendUserToServer(User user) {
        try {
            this.salida.writeObject(user);
        } catch (IOException ex) {
            System.out.println("Internal error");
        }
    }

    // Método para enviar un objeto tipo lista al servidor
    public void sendOnlineUserToServer(ServerRequest request) {         
        try {
            this.salida.writeObject(request);
            
        } catch (IOException ex) {
            System.out.println("Internal error");
        }
    }
 
    // Método para enviar un objeto tipo lista al servidor
    public void sendRequest(ServerRequest request) {         
        try {
            this.salida.writeObject(request);
            
        } catch (IOException ex) {
            System.out.println("Internal error");
        }
    }
    
     
    
} // fin clase 
