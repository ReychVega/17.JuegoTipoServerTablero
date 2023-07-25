package Domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author reych
 */
public class Request implements Serializable{
private static final long serialVersionUID=1L;
    
private String friend;    
private String user;
private String action;
private FriendRequest request;

private ArrayList<User>onlineUsers;
private ArrayList<User>foundUsers;

    public Request(String user, String action) {
        this.user = user;
        this.action = action;
        this.onlineUsers=new ArrayList<>();
        this.foundUsers=new ArrayList<>();
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

        
}
