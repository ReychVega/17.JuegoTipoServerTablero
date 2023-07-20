package Utility;

import Domain.User;
import java.util.ArrayList;

/**
 *
 * @author reych
 */
public class Utility {

    public Utility() {
    }
    
    public boolean search(ArrayList<User> list, String user){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUser().equalsIgnoreCase(user)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean verifyPassword(ArrayList<User> list, String password){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPassword().equalsIgnoreCase(password)) {
                return true;
            }
        }
        return false;
    }
}
