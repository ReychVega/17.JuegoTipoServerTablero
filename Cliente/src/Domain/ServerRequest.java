package Domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author reych
 */
// Clase que representa una solicitud del servidor y contiene información sobre el estado del juego y los usuarios
public class ServerRequest implements Serializable {
    // Identificador para la serialización
    private static final long serialVersionUID = 1L;

    // Atributos que almacenan información sobre el estado del juego y los usuarios
    private boolean turno;
    private boolean gameState;
    private String friend;
    private String user;
    private String action;
    private User enemy;
    private FriendRequest request;
    private GameRequest gameRequest;
    public int[][] juego;

    // Listas para almacenar usuarios y solicitudes relacionadas al juego y amistad
    private ArrayList<User> onlineUsers;
    private ArrayList<User> foundUsers;
    private ArrayList<User> friends;
    private ArrayList<GameRequest> gameRequestSent;
    private ArrayList<GameRequest> gameRequestRecieved;
    private ArrayList<FriendRequest> friendRequestSent;
    private ArrayList<FriendRequest> friendRequestRecieved;

    // Constructor que recibe el nombre de usuario y acción
    public ServerRequest(String user, String action) {
        this.user = user;
        this.action = action;
        this.turno = false;
        this.gameState = false;
        this.friends = new ArrayList<>();
        this.onlineUsers = new ArrayList<>();
        this.foundUsers = new ArrayList<>();
        this.gameRequestSent = new ArrayList<>();
        this.gameRequestRecieved = new ArrayList<>();
        this.foundUsers = new ArrayList<>();
        this.friendRequestSent = new ArrayList<>();
        this.friendRequestRecieved = new ArrayList<>();
        this.juego = null;
    }

    // Métodos para acceder y modificar el nombre de usuario y acción
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

    // Métodos para acceder y modificar el estado del juego
    public boolean isGameState() {
        return gameState;
    }

    public void setGameState(boolean gameState) {
        this.gameState = gameState;
    }

    // Métodos para acceder y modificar la matriz que representa el juego
    public int[][] getJuego() {
        return juego;
    }

    public void setJuego(int[][] juego) {
        this.juego = juego;
    }

    // Métodos para acceder y modificar la lista de usuarios en línea
    public ArrayList<User> getOnlineUsers() {
        return onlineUsers;
    }

    public void setOnlineUsers(ArrayList<User> onlineUsers) {
        this.onlineUsers = onlineUsers;
    }

    // Métodos para acceder y modificar la lista de usuarios encontrados
    public ArrayList<User> getFoundUsers() {
        return foundUsers;
    }

    public void setFoundUsers(ArrayList<User> foundUsers) {
        this.foundUsers = foundUsers;
    }

    // Métodos para acceder y modificar el usuario enemigo
    public User getEnemy() {
        return enemy;
    }

    public void setEnemy(User enemy) {
        this.enemy = enemy;
    }

    // Métodos para acceder y modificar la lista de solicitudes de juego enviadas
    public ArrayList<GameRequest> getGameRequestSent() {
        return gameRequestSent;
    }

    public void setGameRequestSent(ArrayList<GameRequest> gameRequestSent) {
        this.gameRequestSent = gameRequestSent;
    }

    // Métodos para acceder y modificar la lista de solicitudes de juego recibidas
    public ArrayList<GameRequest> getGameRequestRecieved() {
        return gameRequestRecieved;
    }

    public void setGameRequestRecieved(ArrayList<GameRequest> gameRequestRecieved) {
        this.gameRequestRecieved = gameRequestRecieved;
    }

    // Métodos para acceder y modificar la solicitud de amistad
    public FriendRequest getRequest() {
        return request;
    }

    public void setRequest(FriendRequest request) {
        this.request = request;
    }

    // Métodos para acceder y modificar el nombre del amigo
    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    // Métodos para acceder y modificar la solicitud de juego
    public GameRequest getGameRequest() {
        return gameRequest;
    }

    public void setGameRequest(GameRequest gameRequest) {
        this.gameRequest = gameRequest;
    }

    // Métodos para acceder y modificar la lista de solicitudes de amistad enviadas
    public ArrayList<FriendRequest> getFriendRequestSent() {
        return friendRequestSent;
    }

    public void setFriendRequestSent(ArrayList<FriendRequest> friendRequestSent) {
        this.friendRequestSent = friendRequestSent;
    }

    // Métodos para acceder y modificar la lista de solicitudes de amistad recibidas
    public ArrayList<FriendRequest> getFriendRequestRecieved() {
        return friendRequestRecieved;
    }

    public void setFriendRequestRecieved(ArrayList<FriendRequest> friendRequestRecieved) {
        this.friendRequestRecieved = friendRequestRecieved;
    }

    // Métodos para acceder y modificar la lista de amigos
    public ArrayList<User> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    // Métodos para acceder y modificar el estado del turno
    public boolean isTurno() {
        return turno;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }
}
