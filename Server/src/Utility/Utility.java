package Utility;

import Domain.FriendRequest;
import Domain.GameRequest;
import Domain.User;
import com.sun.net.httpserver.Request;
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
                if (onlineUsers.get(i).getUser().equalsIgnoreCase(user.getFriends().get(j).getUser())
                        & !onlineUsers.get(i).getUser().equalsIgnoreCase(user.getUser())) {
                        onlineFriends.add(new User(onlineUsers.get(i).getUser(),""));
                }
            }
        }        
        return onlineFriends;
    }
    
    //procesa el log out    
    public void logOut(ArrayList<User> onlineUsers, String user){
            for (int i = 0; i < onlineUsers.size(); i++) {
                if (onlineUsers.get(i).getUser().equalsIgnoreCase(user)) {
                    onlineUsers.remove(i);
                }
            }
    }
    
    //procesa busqueda de usuarios
    public ArrayList<User>getFoundUsers(ArrayList<User> list, String user){
      
        ArrayList<User> users=new ArrayList<>();
        //primero agregamos los usuarios que contengan ese nombre
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUser().toLowerCase().contains(user.toLowerCase())) {
                users.add(new User(list.get(i).getUser(),""));
            }           
        }        
        return users;
    }
    
    //verifica solicitud enviada
    public boolean verifyFriendRequestSent(User sentBy, User sentFor, FriendRequest request){
        for (int i = 0; i < sentBy.getRequestSent().size(); i++) {
            
            if (sentBy.getRequestSent().get(i).getRequestFor().getUser().equalsIgnoreCase(request.getRequestFor().getUser())) {
                return true;
            }
        }
        
        return false;
    }
    
    //agregamos solicitud de juego en la lista temporal
    public void addGameRequest(ArrayList<User> usersOnline, User user, User friend){
        for (int i = 0; i < usersOnline.size(); i++) {
            if (usersOnline.get(i).getUser().equalsIgnoreCase(user.getUser())) {
                usersOnline.get(i).getGameRequestSent().add(new GameRequest(user, friend));
            }
            if (usersOnline.get(i).getUser().equalsIgnoreCase(friend.getUser())) {
                usersOnline.get(i).getGameRequestRecieved().add(new GameRequest(user, friend));
            }
        }   
    }
    
    //recuperamos los game request recibidos por usuario
    public ArrayList<GameRequest> getGameRequestRecieved(ArrayList<User> usersOnline, User user){
        ArrayList<GameRequest> gameRequest=new ArrayList<>(); 
        for (int i = 0; i < usersOnline.size(); i++) {
                if (usersOnline.get(i).getUser().equalsIgnoreCase(user.getUser())) {
                    gameRequest=usersOnline.get(i).getGameRequestRecieved();
                }
            }
        return gameRequest;
    }

    //recuperamos los game request enviados por usuario
    public ArrayList<GameRequest> getGameRequestSent(ArrayList<User> usersOnline, User user){
        ArrayList<GameRequest> gameRequest=new ArrayList<>(); 
        for (int i = 0; i < usersOnline.size(); i++) {
                if (usersOnline.get(i).getUser().equalsIgnoreCase(user.getUser())) {
                    gameRequest=usersOnline.get(i).getGameRequestSent();
                }
            }
        return gameRequest;
    }

}
