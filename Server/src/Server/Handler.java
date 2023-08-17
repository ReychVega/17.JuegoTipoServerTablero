package Server;

import Domain.ServerRequest;
import Domain.User;
import FileControl.UserFile;
import Utility.Utility;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
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
    private ArrayList<User> usersData;
    private UserFile file;

    //atributos auxiliares
    private User user;
    private ServerRequest request;
    private boolean flag;

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
        flag = false;
        initInstances();
    } // constructor

    private void initInstances() {
        try {
            utility = new Utility();
            usersList = DBget.DBget.getUsers("");
            file = new UserFile();
            usersData = file.getUsers();
        } catch (IOException ex) {
            System.out.println("FILE ERROR.");
        }
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
                
                if (receivedObject instanceof User user1) {
                user = user1;
                    //Caso 1. Valida inicio de sesion
                    validaInicioDeSesion();
                    //Caso 2. Valida registro de nuevo usuario
                    validaRegistroDeUsuario();
                }
                
                if (receivedObject instanceof ServerRequest serverRequest) {
                request = serverRequest;            
                    //Caso 1. valida solicitud de la informacion del usuario
                    getUserData();
                    //Caso 2. valida solicitud de la informacion de los usarios en linea
                    getOnlineUsersData();
                    //Caso 3. Procesa el log out
                    procesaLogOut();
                    //Caso 4. Search users, no envia usuarios que son amigos
                    buscaUsuariosParaEnviarSolicitud();
                    //Caso 5.  Enviar solicitud de amistad
                    enviarSolicitudDeAmistad();
                    //Caso 6. Aceptar solicitud de amistad
                    aceptarSolicitudDeAmistad();
                    //Caso 7. Eliminar solicitudes de amistad recibidas      
                    eliminarSolicitudDeAmistadRecibidas();
                    //Caso 8. Eliminar solicitudes de amistad enviadas      
                    eliminarSolicitudDeAmistadEnviadas();
                    //Caso 9. Eliminar amigos
                    eliminarAmigos();
                    //Caso 10. Enviar solicitud de juego (se guarda en memoria en tiempo de ejecucion y no en txt) 
                    enviarSolicitudDeJuego();
                    //Caso 11. Aceptar solicitud de juego recibida. 
                    aceptarSolicitudDeJuego();
                    //Caso 12. Eliminar solicitud de juego recibida.  
                    eliminarSolicitudDeJuegoRecibida();
                    //Caso 13. Eliminar solicitud de juego de juego enviada. 
                    eliminarSolicitudDeJuegoEnviada();
                    //Caso 14. Solicita informacion de las solicitudes de juego
                    solicitaInformacionDeSolicitudesDeJuego();
                    //Caso 15. Solicita informacion de las solicitudes de amistad
                    solicitaInformacionDeSolicitudesDeAmistad();
                    //Caso 16. Solicita la lista de los amigos
                    solicitaListaDeAmigos();
                    //Caso 17. Solicita datos del juego
                    solicitaDatosDelJuego();
                    //Caso 18. Valida movimiento de juego 
                    validaMovimientoDelJuego();
                    //Caso 19. Get DB user list
                    getUserLists();  
                    //case 20."El juego ha terminado"
                    terminaJuego();
                    //case 21. Usuario salta movimiento
                    usuarioSaltaMovimiento();
                    //case 22. Usuario se rinde
                    usuarioSeRinde();
                    //case 23. obtenemos el score
                    getScore();
                    
                }
                                
                //actualizamos
                file.actualizaLista();
                flag = false;
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

    
    //verifica el inicio de sesion
    private void validaInicioDeSesion() {
            if (user.getAction().equals("loggin")) {
                //actualizamos en caso de ser necesario por si hay user nuevo
                initInstances();

                //Caso 1. Existe el usuario y password correcta
                if (utility.search(usersList, user.getUser()) == true
                        && utility.verifyPassword(usersList, user.getUser(), user.getPassword()) == true) {

                    //verifica que no se encuentre en linea
                    if (utility.search(user.getUser()) == false) {
                        Server.onlineUsers.add(file.getUser(user.getUser()));
                        sendMessageToClient("init");
                    } else {
                        sendMessageToClient("You are already online");
                    }

                    //Caso 2. Contrasena erronea    
                } else if (utility.search(usersList, user.getUser()) == true
                        && utility.verifyPassword(usersList, user.getUser(), user.getPassword()) == false) {
                    sendMessageToClient("Incorrect password");
                    //Caso 3. Usuario no existe    
                } else if (utility.search(usersList, user.getUser()) == false) {
                    sendMessageToClient("User does not exist");
                }
            }
    }

    //verifica la posibilidad de un nuevo registro de usuario
    private void validaRegistroDeUsuario() throws IOException, FileNotFoundException, ClassNotFoundException {
        //Caso 1. Registro
        if (user.getAction().equals("registration")) {
            //actualizamos en caso de ser necesario por si hay user nuevo
            initInstances();

            //verificamos que no exista un usuario igual
            //caso 1.1 guardamos
            if (utility.search(usersList, user.getUser()) == false) {

                DBsave.DBsave.addUser("user, password",
                        ("'" + user.getUser() + "', '"
                        + user.getPassword() + "'"));

                sendMessageToClient("Successful process.");

                file.saveUser(user);

                //caso 1.2 no guardamos porque existe un usuario
            } else if (utility.search(usersList, user.getUser()) == true) {
                sendMessageToClient("Existing user");
            }

        }
    }

    //valida solicitud de la informacion del usuario
    private void getUserData() {
        if (request.getAction().equalsIgnoreCase("Get user data")) {
            sendUserToServer(utility.getUser(request.getUser()));
        }
    }

    //valida solicitud de la informacion de los usarios en linea    
    private void getOnlineUsersData() {
        if (request.getAction().equalsIgnoreCase("Get online users")) {
            user = file.getUser(request.getUser());
            request.setOnlineUsers(utility.getOnlineFriends(user));
            sendOnlineUserToServer(request);
        }
    }

    //Procesa el log out
    private void procesaLogOut() throws IOException, FileNotFoundException, ClassNotFoundException {
        if (request.getAction().equalsIgnoreCase("log out")) {
            User enemy=utility.getEnemyByUser(request.getUser());
            utility.cleanGameRequest(new User(request.getUser(), ""));
            flag = utility.removeEnemy(request.getUser());
            utility.logOut(Server.onlineUsers, request.getUser());
            if (flag == true && enemy!=null) {
                file.actualizarPuntaje(enemy.getUser(), 100);
                utility.actualizarPuntaje(enemy.getUser(), 100);
            }
        }
    }

    //Search users, no envia usuarios que son amigos***mejorar
    private void buscaUsuariosParaEnviarSolicitud() {
        if (request.getAction().equalsIgnoreCase("search users")) {
            request.setFoundUsers(utility.getFoundUsers(
                    file.getUsers(),
                    request.getUser()));
            sendRequest(request);
        }
    }

    //Enviar solicitud de amistad
    private void enviarSolicitudDeAmistad() throws IOException, FileNotFoundException, ClassNotFoundException {
        if (request.getAction().equalsIgnoreCase("requestSent")) {
            //actualizamos
            initInstances();
            //Revisa que no exista la solicitud y guardamos en el archivo. 
            if (utility.verifyFriendRequestSent(
                    file.getUser(request.getRequest().getRequestBy().getUser()),
                    file.getUser(request.getRequest().getRequestFor().getUser()),
                    request.getRequest()) == false) {

                file.updateUserRequests(
                        new User(request.getRequest().getRequestBy().getUser(), ""),
                        new User(request.getRequest().getRequestFor().getUser(), ""),
                        request.getRequest());

                //enviamos mensaje
                sendMessageToClient("Friend request sent successfully");
            } else {
                sendMessageToClient("Error, previously sent");
            }
        }
    }

    //Aceptar solicitud de amistad
    private void aceptarSolicitudDeAmistad()throws IOException, FileNotFoundException, ClassNotFoundException {
        if (request.getAction().equalsIgnoreCase("AcceptRequest")) {
            //actualizamos
            file.actualizaLista();
            file.deleteFriendRequestSent(request.getRequest().getRequestFor(), request.getRequest().getRequestBy());
            file.deleteFriendRequestSent(request.getRequest().getRequestBy(), request.getRequest().getRequestFor());
            file.addFriend(request.getRequest().getRequestBy(), request.getRequest().getRequestFor());
            file.addFriend(request.getRequest().getRequestFor(), request.getRequest().getRequestBy());
            sendMessageToClient("Friend added");
        }
    }
    
    //Eliminar solicitudes de amistad recibidas 
    private void eliminarSolicitudDeAmistadRecibidas()throws IOException, FileNotFoundException, ClassNotFoundException {
        if (request.getAction().equalsIgnoreCase("DeleteRequest")) {
            file.deleteFriendRequestSent(request.getRequest().getRequestFor(), request.getRequest().getRequestBy());
            sendMessageToClient("Friend request deleted");
        }
    }
    
    //Eliminar solicitudes de amistad enviadas    
    private void eliminarSolicitudDeAmistadEnviadas() throws IOException, FileNotFoundException, ClassNotFoundException {
        if (request.getAction().equalsIgnoreCase("DeleteRequestSent")) {
            file.deleteFriendRequestSent(request.getRequest().getRequestBy(), request.getRequest().getRequestFor());
            sendMessageToClient("Friend request deleted");
        }
    }

    //Eliminar amigos
    private void eliminarAmigos() throws IOException, FileNotFoundException, ClassNotFoundException{
        if (request.getAction().equalsIgnoreCase("DeleteFriend")) {
            file.removeFriend(request.getUser(), request.getFriend());
            file.removeFriend(request.getFriend(), request.getUser());
            sendMessageToClient("Friend deleted");
        }
    }

    //Enviar solicitud de juego (memoria temporal) 
    private void enviarSolicitudDeJuego() {
        if (request.getAction().equalsIgnoreCase("sentGameRequest")) {
            //creamos la solicitud en la lista de usuarios en linea porque es memoria temporal
            if (utility.verifyGameRequest(
                    request.getGameRequest().getRequestBy().getUser(),
                    request.getGameRequest().getRequestFor().getUser()) == false) {
                utility.addGameRequest(request.getGameRequest());
                sendMessageToClient("Game request sent");
            } else {
                sendMessageToClient("Error, previously sent");
            }
        }
    }

    //Aceptar solicitud de juego recibida. 
    private void aceptarSolicitudDeJuego() {
        if (request.getAction().equalsIgnoreCase("AcceptGameRequest")) {
            utility.acceptGameRequest(request.getGameRequest().getRequestBy(),
                    request.getGameRequest().getRequestFor());
        }
    }

    //Eliminar solicitud de juego recibida. 
    private void eliminarSolicitudDeJuegoRecibida() {
        if (request.getAction().equalsIgnoreCase("RemoveGameRequest")) {
            utility.deleteGameRequestRecieved(request.getGameRequest().getRequestBy(),
                    request.getGameRequest().getRequestFor());
            sendMessageToClient("Game request removed");
        }
    }

    //Eliminar solicitud de juego de juego enviada.  
    private void eliminarSolicitudDeJuegoEnviada() {
        if (request.getAction().equalsIgnoreCase("RemoveGameRequestRecieved")) {
            utility.deleteGameRequestSent(request.getGameRequest().getRequestBy(),
                    request.getGameRequest().getRequestFor());
            sendMessageToClient("Game request removed");
        }
    }
    
    //Solcita informacion de las solicitudes de juego    
    private void solicitaInformacionDeSolicitudesDeJuego() {
        if (request.getAction().equalsIgnoreCase("GetGameRequestData")) {
            request.setGameRequestSent(
                    utility.getGameRequestSent(new User(request.getUser(), "")));
            request.setGameRequestRecieved(
                    utility.getGameRequestRecieved(new User(request.getUser(), "")));
            sendRequest(request);
        }
    }

    //Solicita informacion de las solicitudes de amistad    
    private void solicitaInformacionDeSolicitudesDeAmistad() {
        if (request.getAction().equalsIgnoreCase("GetFriendRequestData")) {
            request.setFriendRequestSent(
                    utility.getFriendRequestSent(
                            file.getUsers(), new User(request.getUser(), "")));

            request.setFriendRequestRecieved(
                    utility.getFriendRequestRecieved(
                            file.getUsers(), new User(request.getUser(), "")));
            sendRequest(request);
        }
    }

    //Solicita la lista de los amigos    
    private void solicitaListaDeAmigos() {
        if (request.getAction().equalsIgnoreCase("GetFriendsData")) {
            request.setFriends(utility.getFriendData(file.getUsers(), new User(request.getUser(), "")));
            sendRequest(request);
        }
    }

    //solicita datos del juego
    private void solicitaDatosDelJuego() {
        if (request.getAction().equalsIgnoreCase("getGameValidation")) {
            //pedimos el usuario y el gameState
            request.setEnemy(utility.getEnemyByUser(request.getUser()));
            request.setGameState(utility.getGameState(request.getUser()));
            request.setTurno(utility.getTurno(request.getUser()));
            if (request.getEnemy() != null) {
                request.setJuego(
                        utility.getJuego(request.getUser(), request.getEnemy().getUser()));
            }
            sendRequest(request);
        }
    }

    //valida movimiento de juego
    private void validaMovimientoDelJuego() {
        if (request.getAction().equalsIgnoreCase("GameMove")) {
            utility.setTablero(request.getUser(), request.getEnemy().getUser(), request.getJuego());
            utility.setTurnos(request.getUser(), request.getEnemy().getUser());
            request.setJuego(null);
        }
    }

    //Get DB user list
    private void getUserLists() {
        if (request.getAction().equalsIgnoreCase("Get DB User List")) {
            request.setDBUsers(utility.obtenerData(file.getUsers()));
            sendRequest(request);
        }
    }

    //"El juego ha terminado"**
    private void terminaJuego() throws IOException, FileNotFoundException, ClassNotFoundException {
        if (request.getAction().equalsIgnoreCase("El juego ha terminado")) {
            System.out.println("*termina el juego");
            System.out.println("User="+request.getUser());
            System.out.println("Puntaje= "+request.getPuntaje());
          
            if (request.getPuntaje()==100) {
              file.actualizarPuntaje(request.getUser(), request.getPuntaje());
              utility.actualizarPuntaje(request.getUser(), request.getPuntaje());  
            }
        }
        if (request.getAction().equalsIgnoreCase("removeEnemyForGameClosed")) {
            utility.removeEnemy(request.getEnemy().getUser());
        }
           
    }

    //Usuario salta movimiento
    private void usuarioSaltaMovimiento() {
        if (request.getAction().equalsIgnoreCase("skipMove")) {
            utility.setTablero(request.getUser(), request.getEnemy().getUser(), request.getJuego());
            utility.setTurnos(request.getUser(), request.getEnemy().getUser());
            request.setJuego(null);
        }
    }

    //Usuario se rinde
    private void usuarioSeRinde() throws IOException, FileNotFoundException, ClassNotFoundException{
        if (request.getAction().equalsIgnoreCase("surrender")) {
            utility.removeEnemy(request.getUser());
            utility.removeEnemy(request.getEnemy().getUser());
            file.actualizarPuntaje(request.getEnemy().getUser(), 100);
            utility.actualizarPuntaje(request.getEnemy().getUser(), 100); 
        }
    }

    //enviamos el score actualizado
    private void getScore() {
        if (request.getAction().equalsIgnoreCase("GetScore")) {
            request.setPuntaje(utility.getPuntaje(request.getUser()));
            sendOnlineUserToServer(request);
        }
    }

} // fin clase 
