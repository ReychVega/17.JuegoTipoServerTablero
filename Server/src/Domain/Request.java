package Domain;

import java.io.Serializable;

/**
 *
 * @author reych
 */
    // Clase abstracta que representa una solicitud (request)
abstract class Request implements Serializable {
    private static final long serialVersionUID = 1L;
    private User requestBy; // Usuario que realiza la solicitud
    private User requestFor; // Usuario al que se dirige la solicitud

    // Constructor para crear una solicitud con los usuarios que la envían y la reciben
    public Request(User requestBy, User requestFor) {
        this.requestBy = requestBy;
        this.requestFor = requestFor;
    }

    // Método para obtener el usuario que realiza la solicitud
    public User getRequestBy() {
        return requestBy;
    }

    // Método para establecer el usuario que realiza la solicitud
    public void setRequestBy(User requestBy) {
        this.requestBy = requestBy;
    }

    // Método para obtener el usuario al que se dirige la solicitud
    public User getRequestFor() {
        return requestFor;
    }

    // Método para establecer el usuario al que se dirige la solicitud
    public void setRequestFor(User requestFor) {
        this.requestFor = requestFor;
    }

    // Método abstracto para mostrar información específica de la solicitud según un índice (i)
    abstract String showData(int i);
}

