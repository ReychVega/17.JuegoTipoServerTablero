package Domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author reych
 */
// Clase que representa una solicitud del servidor
public class ServerRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    // Atributos de la solicitud del servidor
    private boolean turno; // Indica si es el turno del jugador
    private boolean gameState; // Indica el estado del juego (si está en curso o no)
    private String friend; // Almacena el nombre del amigo
    private String user; // Almacena el nombre de usuario
    private String action; // Almacena la acción que se debe realizar
    private User enemy; // Representa al enemigo del usuario
    private FriendRequest request; // Solicitud de amistad recibida
    private GameRequest gameRequest; // Solicitud de juego recibida
    public int[][] juego; // Representa el tablero del juego
   private int puntaje;

    // Listas para almacenar diferentes objetos relacionados con el usuario
    private ArrayList<User> onlineUsers; // Usuarios en línea
    private ArrayList<User> foundUsers; // Usuarios encontrados
    private ArrayList<User> friends; // Lista de amigos del usuario
    private ArrayList<GameRequest> gameRequestSent; // Solicitud de juego enviada
    private ArrayList<GameRequest> gameRequestRecieved; // Solicitud de juego recibida
    private ArrayList<FriendRequest> friendRequestSent; // Solicitud de amistad enviada
    private ArrayList<FriendRequest> friendRequestRecieved; // Solicitud de amistad recibida
    private ArrayList<User>dbUsers;

    // Constructor para crear una solicitud del servidor con el nombre de usuario y acción específica
    public ServerRequest(String user, String action) {
        this.user = user;
        this.action = action;
        this.turno = false;
        this.gameState = false;
        this.friends = new ArrayList<>();
        this.dbUsers = new ArrayList<>();
        this.onlineUsers = new ArrayList<>();
        this.foundUsers = new ArrayList<>();
        this.gameRequestSent = new ArrayList<>();
        this.gameRequestRecieved = new ArrayList<>();
        this.foundUsers = new ArrayList<>();
        this.friendRequestSent = new ArrayList<>();
        this.friendRequestRecieved = new ArrayList<>();
        this.juego = null;
    }

    // Métodos para obtener y establecer los atributos de la solicitud del servidor

    // ...
    // (Métodos getters y setters para los atributos)
    // ...



    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }


    public int[][] getJuego() {
        return juego;
    }

    public void setJuego(int[][] juego) {
        this.juego = juego;
    }
    
    public ArrayList<User> getDbUsers() {
        return dbUsers;
    }

    public void setDbUsers(ArrayList<User> dbUsers) {
        this.dbUsers = dbUsers;
    }
    
    public ArrayList<User> getOnlineUsers() {
        return onlineUsers;
    }

    public void setOnlineUsers(ArrayList<User> onlineUsers) {
        this.onlineUsers = onlineUsers;
    }

    public ArrayList<User> getFoundUsers() {
        return foundUsers;
    }

    public void setFoundUsers(ArrayList<User> foundUsers) {
        this.foundUsers = foundUsers;
    }

    public User getEnemy() {
        return enemy;
    }

    public void setEnemy(User enemy) {
        this.enemy = enemy;
    }
    
    public ArrayList<GameRequest> getGameRequestSent() {
        return gameRequestSent;
    }

    public void setGameRequestSent(ArrayList<GameRequest> gameRequestSent) {
        this.gameRequestSent = gameRequestSent;
    }

    public ArrayList<GameRequest> getGameRequestRecieved() {
        return gameRequestRecieved;
    }

    public void setGameRequestRecieved(ArrayList<GameRequest> gameRequestRecieved) {
        this.gameRequestRecieved = gameRequestRecieved;
    }

    public boolean isGameState() {
        return gameState;
    }

    public void setGameState(boolean gameState) {
        this.gameState = gameState;
    }
    
    public FriendRequest getRequest() {
        return request;
    }

    public void setRequest(FriendRequest request) {
        this.request = request;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    public void setDBUsers(ArrayList<User> dbUsers) {
        this.dbUsers = dbUsers;
    }
    
    public GameRequest getGameRequest() {
        return gameRequest;
    }

    public void setGameRequest(GameRequest gameRequest) {
        this.gameRequest = gameRequest;
    }

    public ArrayList<FriendRequest> getFriendRequestSent() {
        return friendRequestSent;
    }

    public void setFriendRequestSent(ArrayList<FriendRequest> friendRequestSent) {
        this.friendRequestSent = friendRequestSent;
    }

    public ArrayList<FriendRequest> getFriendRequestRecieved() {
        return friendRequestRecieved;
    }

    public void setFriendRequestRecieved(ArrayList<FriendRequest> friendRequestRecieved) {
        this.friendRequestRecieved = friendRequestRecieved;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public boolean isTurno() {
        return turno;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }
    
    
}
