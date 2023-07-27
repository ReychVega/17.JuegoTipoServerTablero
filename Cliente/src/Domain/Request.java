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
    abstract class Request  implements Serializable{
    private static final long serialVersionUID=1L;
    private User requestBy;
    private User requestFor;
       
    public Request(User requestBy, User requestFor) {
     this.requestBy=requestBy;
     this.requestFor=requestFor;
     
    }

    public User getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(User requestBy) {
        this.requestBy = requestBy;
    }

    public User getRequestFor() {
        return requestFor;
    }

    public void setRequestFor(User requestFor) {
        this.requestFor = requestFor;
    }
    
    abstract String showData(int i);
    
}
