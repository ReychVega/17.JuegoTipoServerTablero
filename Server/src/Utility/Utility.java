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
}
