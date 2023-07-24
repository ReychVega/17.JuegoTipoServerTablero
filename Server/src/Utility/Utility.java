package Utility;

import Domain.FriendRequest;
import Domain.User;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author reych
 */
public class Utility {

    public Utility() {
    }
    
    //buscamos un usuario
    public boolean search(ArrayList<User> list, String user) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getUser().equalsIgnoreCase(user)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    
    //revisamos password
    public boolean verifyPassword(ArrayList<User> list, String password){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPassword().equalsIgnoreCase(password)) {
                return true;
            }
        }
        return false;
    }

    //revisamos los amigos en linea
    public ArrayList<User> getOnlineFriends(ArrayList<User> onlineUsers, User user){
        
       ArrayList<User> onlineFriends=new ArrayList<>();
       
        for (int i = 0; i < onlineUsers.size(); i++) {
            for (int j = 0; j < user.getFriends().size(); j++) {
                if (onlineUsers.get(i).getUser().equalsIgnoreCase(user.getFriends().get(i).getUser())
                        & !onlineUsers.get(i).getUser().equalsIgnoreCase(user.getUser())) {
                        onlineFriends.add(new User(onlineUsers.get(i).getUser(),""));
                }
            }
        }        
        return onlineFriends;
    }
    
    public void logOut(ArrayList<User> onlineUsers, String user){
            for (int i = 0; i < onlineUsers.size(); i++) {
                if (onlineUsers.get(i).getUser().equalsIgnoreCase(user)) {
                    onlineUsers.remove(i);
                }
            }
    }
    
    public ArrayList<User>getFoundUsers(ArrayList<User> list){
      ArrayList<User> users=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            users.add(new User(list.get(i).getUser(),""));
        }
        return users;
    }
    
    public boolean verifyFriendRequestSent(User sentBy, User sentFor, FriendRequest request){
        for (int i = 0; i < sentBy.getRequestSent().size(); i++) {
            
            if (sentBy.getRequestSent().get(i).getRequestFor().getUser().equalsIgnoreCase(request.getRequestFor().getUser())) {
                return true;
            }
        }
        
        return false;
    }

}
