package Domain;

import java.io.Serializable;

/**
 *
 * @author reych
 */
// Clase que representa una solicitud de juego y extiende la clase Request para ser serializable
public class GameRequest extends Request implements Serializable {
    // Número de versión para la serialización
    private static final long serialVersionUID = 1L;

    // Atributos de la solicitud de juego
    private User requestBy; // Usuario que envía la solicitud
    private User requestFor; // Usuario que recibe la solicitud

    // Constructor que recibe el usuario que envía y el usuario que recibe la solicitud
    public GameRequest(User requestBy, User requestFor) {
        // Llama al constructor de la clase base (Request) para establecer los usuarios involucrados
        super(requestBy, requestFor);
        this.requestBy = requestBy;
        this.requestFor = requestFor;
    }

    // Métodos para obtener y establecer el usuario que envía la solicitud
    @Override
    public User getRequestBy() {
        return requestBy;
    }

    @Override
    public void setRequestBy(User requestBy) {
        this.requestBy = requestBy;
    }

    // Métodos para obtener y establecer el usuario que recibe la solicitud
    @Override
    public User getRequestFor() {
        return requestFor;
    }

    @Override
    public void setRequestFor(User requestFor) {
        this.requestFor = requestFor;
    }

    // Método para mostrar datos específicos de la solicitud de juego según el valor de i
    @Override
    public String showData(int i) {
        if (i == 1) {
            // Si i es 1, devuelve la información del usuario que envió la solicitud de juego
            return "Game Request sent by: " + requestBy.getUser();
        } else if (i == 2) {
            // Si i es 2, devuelve la información del usuario que recibió la solicitud de juego
            return "Game Request sent to: " + requestFor.getUser();
        }
        // En otro caso, devuelve una cadena vacía
        return "";
    }

    // Método para representar la solicitud de juego en formato de cadena
    @Override
    public String toString() {
        return "*GR{" + "de=" + requestBy.getUser() + ", para=" + requestFor.getUser() + '}';
    }
}

