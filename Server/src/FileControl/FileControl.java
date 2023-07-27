package FileControl;

import Domain.FriendRequest;
import Domain.User;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author reych
 */
public interface FileControl {
    public void saveUser(User user) throws FileNotFoundException, IOException, ClassNotFoundException;
    public void updateUserRequests(User sendRequest, User recieveRequest, FriendRequest newRequest) throws FileNotFoundException, IOException, ClassNotFoundException;
    public void deleteUserRequests(User sendRequest,User recieveRequest, FriendRequest newRequest) throws FileNotFoundException, IOException, ClassNotFoundException;
    public boolean verifyExistence(String user);
    public void actualizaLista();
    public User getUser(String user);
    public void addFriend(User user, User friend) throws FileNotFoundException, IOException, ClassNotFoundException;
    public void removeFriend(String user, String friend) throws FileNotFoundException, IOException, ClassNotFoundException;
    
}
