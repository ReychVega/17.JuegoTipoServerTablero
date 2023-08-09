package Domain;

import java.io.Serializable;

/**
 *
 * @author reych
 */
public class GameRequest extends Request implements Serializable {
    private static final long serialVersionUID=1L;
    private User requestBy;
    private User requestFor;
       
 public GameRequest(User requestBy, User requestFor) {
    // Llama al constructor de la clase base Request para inicializar los usuarios involucrados en la solicitud
    super(requestBy, requestFor);
    
    // Asigna los usuarios a los campos de la clase GameRequest
    this.requestBy = requestBy;
    this.requestFor = requestFor;
}

  // Devuelve el usuario que envió la solicitud de juego
@Override
public User getRequestBy() {
    return requestBy;
}

// Establece el usuario que envía la solicitud de juego
@Override
public void setRequestBy(User requestBy) {
    this.requestBy = requestBy;
}

// Devuelve el usuario que recibió la solicitud de juego
@Override
public User getRequestFor() {
    return requestFor;
}

// Establece el usuario que recibe la solicitud de juego
@Override
public void setRequestFor(User requestFor) {
    this.requestFor = requestFor;
}

// Muestra información sobre la solicitud de juego
@Override
public String showData(int i) {
    if (i == 1) {
        // Devuelve una cadena que indica que la solicitud de juego fue enviada por un usuario específico
        return "Game Request sent by: " + requestBy.getUser();
    } else if (i == 2) {
        // Devuelve una cadena que indica que la solicitud de juego fue enviada a un usuario específico
        return "Game Request sent to: " + requestFor.getUser();
    }
    // Devuelve una cadena vacía si el valor de 'i' no coincide con 1 o 2
    return "";
}

// Devuelve una representación de cadena de la solicitud de juego, incluyendo los usuarios involucrados
@Override
public String toString() {
    return "*GR{" + "de=" + requestBy.getUser() + ", para=" + requestFor.getUser() + '}';
} 
 
}
