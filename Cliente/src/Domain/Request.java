/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.io.Serializable;

/**
 *
 * @author reych
 */
  // Clase abstracta que representa una solicitud realizada por un usuario a otro
abstract class Request implements Serializable {
    // Identificador para la serialización
    private static final long serialVersionUID = 1L;

    // Atributos que almacenan el usuario que realiza la solicitud y el usuario solicitado
    private User requestBy;
    private User requestFor;

    // Constructor que recibe el usuario que realiza la solicitud y el usuario solicitado
    public Request(User requestBy, User requestFor) {
        this.requestBy = requestBy;
        this.requestFor = requestFor;
    }

    // Métodos para acceder y modificar el usuario que realiza la solicitud
    public User getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(User requestBy) {
        this.requestBy = requestBy;
    }

    // Métodos para acceder y modificar el usuario solicitado
    public User getRequestFor() {
        return requestFor;
    }

    public void setRequestFor(User requestFor) {
        this.requestFor = requestFor;
    }

    // Método abstracto que debe ser implementado por las subclases para mostrar información específica de la solicitud
    abstract String showData(int i);
}
