package Domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author reych
 */
// Clase que representa un usuario del juego
public class User implements Serializable {
    // Atributos de usuario
    private String user; // Nombre de usuario
    private String password; // Contraseña del usuario
    private boolean turno; // Indica si es el turno del usuario en el juego
    private boolean gameState; // Indica el estado del juego del usuario
    private User enemy; // Representa al enemigo del usuario en el juego
    private String action; // Almacena la acción que el usuario debe realizar
    private int puntaje; // Puntaje del usuario en el juego
    // Lista de amigos del usuario y solicitudes de amistad enviadas y recibidas
    private ArrayList<User> friends; 
    private ArrayList<FriendRequest> requestRecieved; 
    private ArrayList<FriendRequest> requestSent;
    // Lista de solicitudes de juego enviadas y recibidas
    private ArrayList<GameRequest> gameRequestRecieved; 
    private ArrayList<GameRequest> gameRequestSent; 
    // Tablero del juego del usuario
    private int[][] tablero;

    private static final long serialVersionUID = 1L;

    // Constructor sin parámetros para crear un nuevo usuario
    public User() {
        this.puntaje = 0;
        this.friends = new ArrayList<>();
        this.requestSent = new ArrayList<>();
        this.requestRecieved = new ArrayList<>();
        this.gameRequestSent = new ArrayList<>();
        this.gameRequestRecieved = new ArrayList<>();
        this.gameState = false;
        this.turno = false;
        this.tablero = null;
    }

    // Constructor con parámetros para crear un nuevo usuario con nombre de usuario y contraseña
    public User(String user, String password) {
        this.user = user;
        this.password = password;
        this.puntaje = 0;
        this.friends = new ArrayList<>();
        this.requestSent = new ArrayList<>();
        this.requestRecieved = new ArrayList<>();
        this.gameRequestSent = new ArrayList<>();
        this.gameRequestRecieved = new ArrayList<>();
        this.gameState = false;
        this.turno = false;
        this.tablero = null;
    }

    // Métodos para obtener y establecer los atributos del usuario

    // ...
    // (Métodos getters y setters para los atributos)
    

   


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public User getEnemy() {
        return enemy;
    }

    public void setEnemy(User enemy) {
        this.enemy = enemy;
    }
   
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public boolean isGameState() {
        return gameState;
    }

    public void setGameState(boolean gameState) {
        this.gameState = gameState;
    }
    
    public ArrayList<User> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public ArrayList<FriendRequest> getRequestRecieved() {
        return requestRecieved;
    }

    public void setRequestRecieved(ArrayList<FriendRequest> requestRecieved) {
        this.requestRecieved = requestRecieved;
    }

    public ArrayList<FriendRequest> getRequestSent() {
        return requestSent;
    }

    public void setRequestSent(ArrayList<FriendRequest> requestSent) {
        this.requestSent = requestSent;
    }

    public ArrayList<GameRequest> getGameRequestRecieved() {
        return gameRequestRecieved;
    }

    public void setGameRequestRecieved(ArrayList<GameRequest> gameRequestRecieved) {
        this.gameRequestRecieved = gameRequestRecieved;
    }

    public ArrayList<GameRequest> getGameRequestSent() {
        return gameRequestSent;
    }

    public void setGameRequestSent(ArrayList<GameRequest> gameRequestSent) {
        this.gameRequestSent = gameRequestSent;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public boolean isTurno() {
        return turno;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    public int[][] getTablero() {
        return tablero;
    }

    public void setTablero(int[][] tablero) {
        this.tablero = tablero;
    }
    
    @Override
    public String toString() {
        return user;
    }
     
}