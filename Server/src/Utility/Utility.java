package Utility;

import Domain.FriendRequest;
import Domain.GameRequest;
import Domain.User;
import Server.Server;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author reych
 */
public class Utility implements Serializable{
   private static final long serialVersionUID=1L;

    public Utility() {
    }
    
     public String mostrarArray(int [][] juego) {
        String s="";
        for (int i = 0; i < juego.length; i++) {
            for (int j = 0; j < juego[0].length; j++) {
                s+=juego[i][j]+" ";
            }
            s+="\n";
        }
        return s;
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
    
    //verifica solicitud de amistad enviada
    public boolean verifyFriendRequestSent(User sentBy, User sentFor, FriendRequest request){
        
        for (int i = 0; i < sentBy.getRequestSent().size(); i++) {
            if (sentBy.getRequestSent().get(i).getRequestFor().getUser().equalsIgnoreCase(request.getRequestFor().getUser())) {
                return true;
            }
        }
        
        return false;
    }
    
    //agregamos solicitud de juego en la lista temporal
    public void addGameRequest(GameRequest request){
        for (int i = 0; i < Server.onlineUsers.size(); i++) {
            if (Server.onlineUsers.get(i).getUser().equalsIgnoreCase(request.getRequestBy().getUser().trim())) {
                Server.onlineUsers.get(i).getGameRequestSent().add(request);
            }
            if (Server.onlineUsers.get(i).getUser().equalsIgnoreCase(request.getRequestFor().getUser().trim())) {
                Server.onlineUsers.get(i).getGameRequestRecieved().add(request);
            }
        }   
    }
    
    //recuperamos los game request recibidos por usuario
    public ArrayList<GameRequest> getGameRequestRecieved(ArrayList<User> usersOnline,  User user ){
        ArrayList<GameRequest> gameRequest=new ArrayList<>(); 
        for (int i = 0; i < usersOnline.size(); i++) {
                if (usersOnline.get(i).getUser().equalsIgnoreCase(user.getUser())) {
                     for (int j = 0; j < usersOnline.get(i).getGameRequestRecieved().size(); j++) {
                        gameRequest.add(usersOnline.get(i).getGameRequestRecieved().get(j));
                    } 
                }
            }
        return gameRequest;
    }

    //recuperamos los game request enviados por usuario
    public ArrayList<GameRequest> getGameRequestSent(ArrayList<User> usersOnline, User user){
        ArrayList<GameRequest> list=new ArrayList<>();
        for (int i = 0; i < usersOnline.size(); i++) {
                if (usersOnline.get(i).getUser().equalsIgnoreCase(user.getUser())) {   
                    for (int j = 0; j < usersOnline.get(i).getGameRequestSent().size(); j++) {
                        list.add(usersOnline.get(i).getGameRequestSent().get(j));
                    }         
                }
            }
        return list;
    }

    //agregamos solicitud de juego en la lista temporal
    public void acceptGameRequest(User user, User friend){
        for (int i = 0; i < Server.onlineUsers.size(); i++) {
            if (Server.onlineUsers.get(i).getUser().equalsIgnoreCase(user.getUser())) {
                Server.onlineUsers.get(i).setGameState(true);   
                Server.onlineUsers.get(i).setTurno(true);                   
            }
            if (Server.onlineUsers.get(i).getUser().equalsIgnoreCase(friend.getUser())) {
                Server.onlineUsers.get(i).setGameState(true);
            }
        }   
        for (int i = 0; i < Server.onlineUsers.size(); i++) {
            if (Server.onlineUsers.get(i).getUser().equalsIgnoreCase(user.getUser())) {
                Server.onlineUsers.get(i).setEnemy(getEnemyData(friend.getUser()));
                
            }
            if (Server.onlineUsers.get(i).getUser().equalsIgnoreCase(friend.getUser())) {
                Server.onlineUsers.get(i).setEnemy(getEnemyData(user.getUser()));
            }
        }       
        
        deleteGameRequestRecieved(user, friend);
        deleteGameRequestSent(user, friend);
    }  
  
    //eliminamos solicitud recibidas de juego en la lista temporal
    public void deleteGameRequestRecieved(User user, User friend) {
        for (int i = 0; i < Server.onlineUsers.size(); i++) {
                for (int j = 0; j < Server.onlineUsers.get(i).getGameRequestSent().size(); j++) {
                    if (Server.onlineUsers.get(i).getGameRequestSent().get(j).
                            getRequestBy().getUser().equalsIgnoreCase(friend.getUser())
                            & Server.onlineUsers.get(i).getGameRequestSent().get(j).
                            getRequestFor().getUser().equalsIgnoreCase(user.getUser())) {
                        Server.onlineUsers.get(i).getGameRequestSent().remove(j);
                    }
                }
                for (int j = 0; j < Server.onlineUsers.get(i).getGameRequestRecieved().size(); j++) {
                    if (Server.onlineUsers.get(i).getGameRequestRecieved().get(j).
                            getRequestFor().getUser().equalsIgnoreCase(user.getUser()) 
                            & Server.onlineUsers.get(i).getGameRequestRecieved().get(j).
                            getRequestBy().getUser().equalsIgnoreCase(friend.getUser())) {
                        Server.onlineUsers.get(i).getGameRequestRecieved().remove(j);
                    }
                }
          }
    } 
    
    //eliminamos solicitud enviadas de juego en la lista temporal
    public void deleteGameRequestSent(User user, User friend) {
        for (int i = 0; i < Server.onlineUsers.size(); i++) {
                for (int j = 0; j < Server.onlineUsers.get(i).getGameRequestSent().size(); j++) {
                    if (Server.onlineUsers.get(i).getGameRequestSent().get(j).
                            getRequestBy().getUser().equalsIgnoreCase(user.getUser())
                            & Server.onlineUsers.get(i).getGameRequestSent().get(j).
                            getRequestFor().getUser().equalsIgnoreCase(friend.getUser())) {
                        Server.onlineUsers.get(i).getGameRequestSent().remove(j);
                    }
                }
                for (int j = 0; j < Server.onlineUsers.get(i).getGameRequestRecieved().size(); j++) {
                    if (Server.onlineUsers.get(i).getGameRequestRecieved().get(j).
                            getRequestFor().getUser().equalsIgnoreCase(friend.getUser()) 
                            & Server.onlineUsers.get(i).getGameRequestRecieved().get(j).
                            getRequestBy().getUser().equalsIgnoreCase(user.getUser())) {
                        Server.onlineUsers.get(i).getGameRequestRecieved().remove(j);
                    }
                }
          }
    } 
    
    //asigna estado de juego
    public void setGameState(ArrayList<User> usersOnline, User user){
        for (int i = 0; i < usersOnline.size(); i++) {
            if (usersOnline.get(i).getUser().equalsIgnoreCase(user.getUser())) {
                usersOnline.get(i).setGameState(true);
                
            }
        }   
    }  
    
    //obtenemos el usuario
    public User getUser(String name) {
        User user = new User();
       // System.out.println("" + Server.onlineUsers.size());
        for (int i = 0; i < Server.onlineUsers.size(); i++) {
            if (Server.onlineUsers.get(i).getUser().equalsIgnoreCase(name)) {
                user = Server.onlineUsers.get(i);
            }
        }
        return user;
    }
    
    //limpiamos gameRequest
    public void cleanGameRequest(User user){
          for (int i = 0; i < Server.onlineUsers.size(); i++) {
                for (int j = 0; j < Server.onlineUsers.get(i).getGameRequestSent().size(); j++) {
                    if (Server.onlineUsers.get(i).getGameRequestSent().get(j).
                            getRequestBy().getUser().equalsIgnoreCase(user.getUser())
                            || Server.onlineUsers.get(i).getGameRequestSent().get(j).
                            getRequestFor().getUser().equalsIgnoreCase(user.getUser())) {
                        Server.onlineUsers.get(i).getGameRequestSent().remove(j);
                    }
                }
                for (int j = 0; j < Server.onlineUsers.get(i).getGameRequestRecieved().size(); j++) {
                    if (Server.onlineUsers.get(i).getGameRequestRecieved().get(j).
                            getRequestFor().getUser().equalsIgnoreCase(user.getUser()) 
                            || Server.onlineUsers.get(i).getGameRequestRecieved().get(j).
                            getRequestBy().getUser().equalsIgnoreCase(user.getUser())) {
                        Server.onlineUsers.get(i).getGameRequestRecieved().remove(j);
                    }
                }
          }
    }
 
    //verifica gameRequest
    public boolean verifyGameRequest(String user, String friend){
          for (int i = 0; i < Server.onlineUsers.size(); i++) {
                for (int j = 0; j < Server.onlineUsers.get(i).getGameRequestRecieved().size(); j++) {
                    if (Server.onlineUsers.get(i).getGameRequestRecieved().get(j).
                            getRequestBy().getUser().equalsIgnoreCase(user)
                                & Server.onlineUsers.get(i).getGameRequestRecieved().get(j).
                            getRequestFor().getUser().equalsIgnoreCase(friend)) {
                       return true;
                    }
                }
          }
          return false;
    }
    
   //recuperamos los friend request enviados
    public ArrayList<FriendRequest> getFriendRequestSent(ArrayList<User> usersOnline, User user){
        ArrayList<FriendRequest> list=new ArrayList<>();
        for (int i = 0; i < usersOnline.size(); i++) {
                if (usersOnline.get(i).getUser().equalsIgnoreCase(user.getUser())) {   
                    for (int j = 0; j < usersOnline.get(i).getRequestSent().size(); j++) {
                        list.add(usersOnline.get(i).getRequestSent().get(j));
                    }         
                }
            }
        return list;
    }
    
    //recuperamos los friend request recibidos por usuario
    public ArrayList<FriendRequest> getFriendRequestRecieved(ArrayList<User> usersOnline,  User user ){
        ArrayList<FriendRequest> gameRequest=new ArrayList<>(); 
        for (int i = 0; i < usersOnline.size(); i++) {
                if (usersOnline.get(i).getUser().equalsIgnoreCase(user.getUser())) {
                     for (int j = 0; j < usersOnline.get(i).getRequestRecieved().size(); j++) {
                        gameRequest.add(usersOnline.get(i).getRequestRecieved().get(j));
                    } 
                }
            }
        return gameRequest;
    }
    
    //recuperamos data de amigos
    public ArrayList<User> getFriendData(ArrayList<User> usersOnline,  User user ){
        ArrayList<User> friends=new ArrayList<>(); 
        for (int i = 0; i < usersOnline.size(); i++) {
                if (usersOnline.get(i).getUser().equalsIgnoreCase(user.getUser())) {
                     for (int j = 0; j < usersOnline.get(i).getFriends().size(); j++) {
                        friends.add(usersOnline.get(i).getFriends().get(j));
                    } 
                }
            }
        return friends;
    }

    //verifica estado de juego
    public boolean getGameState(String user){
        for (int i = 0; i < Server.onlineUsers.size(); i++) {
            if (Server.onlineUsers.get(i).getUser().equalsIgnoreCase(user)) {
                return Server.onlineUsers.get(i).isGameState();
            }
        }
    
        return false;
    }

    //verifica turnos de juego
    public boolean getTurno(String user){
        for (int i = 0; i < Server.onlineUsers.size(); i++) {
            if (Server.onlineUsers.get(i).getUser().equalsIgnoreCase(user)) {
                return Server.onlineUsers.get(i).isTurno();
            }
        }
    
        return false;
    }
    
    //recuperamos la data del oponente
    private User getEnemyData(String user){
        for (int i = 0; i < Server.onlineUsers.size(); i++) {
            if (Server.onlineUsers.get(i).getUser().equalsIgnoreCase(user)) {
                return Server.onlineUsers.get(i);
            }
        }
        return null;
    }  
 
    //recuperamos el tablero del oponente
    public int[][] getJuego(String user, String enemy) {
        int[][] tablero = null;
        for (int i = 0; i < Server.onlineUsers.size(); i++) {
            if (Server.onlineUsers.get(i).getUser().equalsIgnoreCase(user)
                    && Server.onlineUsers.get(i).getEnemy() != null
                    && Server.onlineUsers.get(i).getEnemy().getUser().equalsIgnoreCase(enemy)
                    && Server.onlineUsers.get(i).getEnemy().getTablero() != null) {
                
                tablero = Server.onlineUsers.get(i).getEnemy().getTablero();
                Server.onlineUsers.get(i).getEnemy().setTablero(null);
                return tablero;
            }
        }
        return tablero;
    }
    
    //recuperamos la data del enemigo
    public User getEnemyByUser(String user){
        for (int i = 0; i < Server.onlineUsers.size(); i++) {
            if (Server.onlineUsers.get(i).getUser().equalsIgnoreCase(user)) {
                return Server.onlineUsers.get(i).getEnemy();
            }
        }
        return null;
    }  
    
    //borramos el enemigo
    public void removeEnemy(String enemy){
        for (int i = 0; i < Server.onlineUsers.size(); i++) {
           if (Server.onlineUsers.get(i).getEnemy()!=null &&
                   Server.onlineUsers.get(i).getEnemy().getUser().equalsIgnoreCase(enemy)) {
                   Server.onlineUsers.get(i).setEnemy(null);
                   Server.onlineUsers.get(i).setGameState(false);
            }
        }
    }
    
    //establecemos el tablero para el enemigo, de forma que se pueda actualizar
    public void setTablero(String user, String enemy, int[][]tablero){
        for (int i = 0; i < Server.onlineUsers.size(); i++) {
            if (Server.onlineUsers.get(i).getUser().equalsIgnoreCase(enemy)) {
                Server.onlineUsers.get(i).getEnemy().setTablero(tablero);
              //  System.out.println("para "+enemy+"\n"+mostrarArray(tablero));
            }
            if (Server.onlineUsers.get(i).getUser().equalsIgnoreCase(user)) {
                Server.onlineUsers.get(i).getEnemy().setTablero(null);
            }
        }
    }
    
    //intercalamos turnos
    public void setTurnos(String user, String enemy) {
        for (int i = 0; i < Server.onlineUsers.size(); i++) {
            if (Server.onlineUsers.get(i).getUser().equalsIgnoreCase(user)) {
               Server.onlineUsers.get(i).setTurno(false);
            }
            if (Server.onlineUsers.get(i).getUser().equalsIgnoreCase(enemy)) {
               Server.onlineUsers.get(i).setTurno(true);
            }
        }

    }
    
     public ArrayList<User> obtenerData(ArrayList<User> usuarios ){
        ArrayList<User> list = new ArrayList<>();
        for (int i = 0; i < usuarios.size(); i++) {
            list.add(usuarios.get(i));
        }
        return list;
       
    }
}
