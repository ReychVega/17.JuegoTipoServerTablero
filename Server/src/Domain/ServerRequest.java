package Domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author reych
 */
public class ServerRequest implements Serializable{
private static final long serialVersionUID=1L;
    
private boolean gameState;
private String friend;    
private String user;
private String action;
private User enemy;
private FriendRequest request;
private GameRequest gameRequest;

private ArrayList<User>onlineUsers;
private ArrayList<User>foundUsers;
private ArrayList<User>friends;
private ArrayList<GameRequest>gameRequestSent;
private ArrayList<GameRequest>gameRequestRecieved;
private ArrayList<FriendRequest>friendRequestSent;
private ArrayList<FriendRequest>friendRequestRecieved;


    public ServerRequest(String user, String action) {
        this.user = user;
        this.action = action;
        this.friends=new ArrayList<>();
        this.onlineUsers=new ArrayList<>();
        this.foundUsers=new ArrayList<>();
        this.gameRequestSent=new ArrayList<>();
        this.gameRequestRecieved=new ArrayList<>();
    }

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

    
    
}
