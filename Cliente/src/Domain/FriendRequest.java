package Domain;

import java.io.Serializable;

/**
 *
 * @author reych
 */
public class FriendRequest extends Request implements Serializable {
    private static final long serialVersionUID=1L;
    private User requestBy;
    private User requestFor;
       
   public FriendRequest(User requestBy, User requestFor) {
    super(requestBy, requestFor); // Llama al constructor de la clase padre Request
    this.requestBy = requestBy; // Establece el usuario que envía la solicitud
    this.requestFor = requestFor; // Establece el usuario que recibe la solicitud
}

    @Override
    public User getRequestBy() {
        return requestBy;
    }

    @Override
    public void setRequestBy(User requestBy) {
        this.requestBy = requestBy;
    }

    @Override
    public User getRequestFor() {
        return requestFor;
    }

    @Override
    public void setRequestFor(User requestFor) {
        this.requestFor = requestFor;
    }

    
    
    @Override
public String showData(int i) {
    if (i == 1) {
        // Devuelve una cadena que indica el usuario que envió la solicitud
        return "Friend Request sent by: " + requestBy.getUser();
    } else if (i == 2) {
        // Devuelve una cadena que indica el usuario que recibió la solicitud
        return "Friend Request sent to: " + requestFor.getUser();            
    }
    // Si 'i' no es igual a 1 ni a 2, devuelve una cadena vacía.
    return "";
}
     @Override
   // Devuelve una cadena que representa la solicitud de amistad con el formato "*FR{de=usuario1, para=usuario2}"
public String toString() {
    return "*FR{" + "de=" + requestBy.getUser() + ", para=" + requestFor.getUser() + '}';
}
 
 
}
