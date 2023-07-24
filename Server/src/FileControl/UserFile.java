package FileControl;

import Domain.FriendRequest;
import Domain.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author reych
 */
public class UserFile {
    private ArrayList<User> users;
    private File archivo;
    private String fileName;
    
    public UserFile() throws FileNotFoundException, IOException {
        users = new ArrayList<>();
        fileName="./src/files/usersData.txt";
        archivo = new File(fileName);        
    }

    public ArrayList<User> getUsers() {
        this.actualizaLista();
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    
    //métodos
    public void saveUser(User user) throws FileNotFoundException, IOException, ClassNotFoundException {
        
        // System.out.println(element.toString());
        if (archivo.exists()) {
            ObjectInputStream input = null;
           
                input = new ObjectInputStream(new FileInputStream((archivo)));
                Object aux = input.readObject();
                this.users=((ArrayList<User>) (List<User>) aux);
                // System.out.println(piloto.toString());
                input.close();
        }
        
        this.users.add(user);
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream((fileName)));
        output.writeUnshared(this.users);
        output.close();
    }
    
        public void updateUser(User sendRequest,User recieveRequest, FriendRequest newRequest) throws FileNotFoundException, IOException, ClassNotFoundException {
        actualizaLista();
        // System.out.println(element.toString());
        if (archivo.exists()) {
            ObjectInputStream input = null;
           
                input = new ObjectInputStream(new FileInputStream((archivo)));
                Object aux = input.readObject();
                this.users=((ArrayList<User>) (List<User>) aux);
                for (int i = 0; i < this.users.size(); i++) {
                    
                    if (sendRequest.getUser().equalsIgnoreCase(this.users.get(i).getUser())) {
                        this.users.get(i).getRequestSent().add(newRequest);
                    }else if (recieveRequest.getUser().equalsIgnoreCase(this.users.get(i).getUser())) {
                        this.users.get(i).getRequestSent().add(newRequest);
                    }
                    
                }                
                input.close();
        }
        
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream((fileName)));
        output.writeUnshared(this.users);
        output.close();
    }
    
    public boolean verifyExistence(String user){
    if (archivo.exists()) {
            try {
                ObjectInputStream input = new ObjectInputStream(new FileInputStream((fileName)));
                Object aux = input.readObject();
                this.users=((ArrayList<User>) (List<User>) aux);
                // System.out.println(piloto.toString());
                input.close();
            } catch (IOException | ClassNotFoundException ex) {
                return false;
            }
        }
    
        if(!this.users.isEmpty()){
            for (int i = 0; i < this.users.size(); i++) {
                if (this.users.get(i).getUser().equalsIgnoreCase(user)) {
                 return true;   
                }
            }
        }
    
        return false;
    }
    
    //actualiza lista en caso de datos nuevos
    public void actualizaLista(){
    if (archivo.exists()) {
            try {
                ObjectInputStream input = new ObjectInputStream(new FileInputStream((archivo)));
                Object aux = input.readObject();
                this.users=((ArrayList<User>) (List<User>) aux);
                input.close();
            } catch (IOException | ClassNotFoundException ex) {

            }
        }
    
    }  

 
    public User getUser(String user){
        this.actualizaLista();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUser().equalsIgnoreCase(user)) {
               return users.get(i);
            }
        }
        return null;
    }
    

}
