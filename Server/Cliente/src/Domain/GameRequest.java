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
     super(requestBy, requestFor);
     this.requestBy=requestBy;
     this.requestFor=requestFor;
     
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
    public String showData(int i){
        if (i==1) {
              return "Game Request sent by :" + requestBy.getUser();
        }else if (i==2) {
              return "Game Request sent to :" + requestFor.getUser();            
        }
        return "";
    } 
     @Override
    public String toString() {
        return "*GR{" + "de=" + requestBy.getUser() + ", para=" + requestFor.getUser() + '}';
    }
 
 
}
