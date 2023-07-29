package Domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author reych
 */
public class User implements Serializable{
   //user
   private String user;
   private String password;
   //auxiliares
   private boolean gameState;
   private User enemy;
   private String action;
   //profile data
   private int puntaje;
   //friendship
   private ArrayList<User> friends;
   private ArrayList<FriendRequest> requestRecieved;
   private ArrayList<FriendRequest> requestSent;   
   //Game request
   private ArrayList<GameRequest> gameRequestRecieved;
   private ArrayList<GameRequest> gameRequestSent;
   
   
   private static final long serialVersionUID=1L;

    public User() {
        this.puntaje=0;
        this.friends=new ArrayList<>();
        this.requestSent=new ArrayList<>();
        this.requestRecieved=new ArrayList<>();
        this.gameRequestSent=new ArrayList<>();
        this.gameRequestRecieved=new ArrayList<>();
        this.gameState=false;
    }


    public User(String user, String password) {
        this.user = user;
        this.password = password;
         this.puntaje=0;
        this.friends=new ArrayList<>();
        this.requestSent=new ArrayList<>();
        this.requestRecieved=new ArrayList<>();
        this.gameRequestSent=new ArrayList<>();
        this.gameRequestRecieved=new ArrayList<>();
        this.gameState=false;
    }
    
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

    @Override
    public String toString() {
        return user;
    }

   
     
}