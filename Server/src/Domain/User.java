package Domain;

import java.io.Serializable;

/**
 *
 * @author reych
 */
public class User implements Serializable{
   private String user;
   private String password;
   private String action;
   private static final long serialVersionUID=1L;

    public User() {
    }

    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    @Override
    public String toString() {
        return "User{" + "user=" + user + ", password=" + password + '}';
    }
       
    
}
