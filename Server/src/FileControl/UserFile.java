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
public class UserFile implements FileControl{

    private ArrayList<User> users;
    private File archivo;
    private String fileName;

    public UserFile() throws FileNotFoundException, IOException {
        users = new ArrayList<>();
        fileName = "./src/files/usersData.txt";
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

    //guardamos usuario en el archivo
    @Override
    public void saveUser(User user) throws FileNotFoundException, IOException, ClassNotFoundException {

        if (archivo.exists()) {
            ObjectInputStream input = null;

            input = new ObjectInputStream(new FileInputStream((archivo)));
            Object aux = input.readObject();
            this.users = ((ArrayList<User>) (List<User>) aux);
            // System.out.println(piloto.toString());
            input.close();
        }

        this.users.add(user);
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream((fileName)));
        output.writeUnshared(this.users);
        output.close();
    }

    //creamos solicitud de amistad
    @Override
    public void updateUserRequests(User sendRequest, User recieveRequest, FriendRequest newRequest) throws FileNotFoundException, IOException, ClassNotFoundException {
        if (archivo.exists()) {
            ObjectInputStream input = null;

            input = new ObjectInputStream(new FileInputStream((archivo)));
            Object aux = input.readObject();
            this.users = ((ArrayList<User>) (List<User>) aux);
            for (int i = 0; i < this.users.size(); i++) {

                if (sendRequest.getUser().equalsIgnoreCase(this.users.get(i).getUser())) {
                    this.users.get(i).getRequestSent().add(newRequest);
                } else if (recieveRequest.getUser().equalsIgnoreCase(this.users.get(i).getUser())) {
                    this.users.get(i).getRequestRecieved().add(newRequest);
                }

            }
            input.close();
        }

        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream((fileName)));
        output.writeUnshared(this.users);
        output.close();
    }

    //borramos las solicitudes por que el usuario las borro o acepto
    @Override
    public void deleteUserRequests(User sendRequest,User recieveRequest, FriendRequest newRequest) throws FileNotFoundException, IOException, ClassNotFoundException {
       
        if (archivo.exists()) {
            ObjectInputStream input = null;
           
                input = new ObjectInputStream(new FileInputStream((archivo)));
                Object aux = input.readObject();
                
                this.users=((ArrayList<User>) (List<User>) aux);
                //recorremos toda la lista
                for (int i = 0; i < this.users.size(); i++) {
                    
                    //verificamos el usuario que envia
                    if (sendRequest.getUser().equalsIgnoreCase(this.users.get(i).getUser())) {
                        //recorremos la lista de solicitudes de el que envia
                        for (int j = 0; j < this.users.get(i).getRequestSent().size(); j++) {
                            //revisamos las solicitudes y la eliminamos
                            if (recieveRequest.getUser().equalsIgnoreCase(
                                    this.users.get(i).getRequestSent().get(j).getRequestFor().getUser())) {
                                this.users.get(i).getRequestSent().remove(j);
                               
                            }
                        }
                        //recorremos la lista de solicitudes de el que recibe
                        for (int k = 0; k < this.users.get(i).getRequestRecieved().size(); k++) {
                            //revisamos las solicitudes y la eliminamos
                            if (this.users.get(i).getRequestRecieved().get(k).getRequestFor().getUser().
                                    equalsIgnoreCase(sendRequest.getUser())) {
                                this.users.get(i).getRequestRecieved().remove(k);
                            }
                        }                    
                    }
                    
                }                
                input.close();
        }
        
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream((fileName)));
        output.writeUnshared(this.users);
        output.close();
    }    
     
    //revisamos si existe un usuario igual para que no se registre otro igual 
    @Override
    public boolean verifyExistence(String user) {
        if (archivo.exists()) {
            try {
                ObjectInputStream input = new ObjectInputStream(new FileInputStream((fileName)));
                Object aux = input.readObject();
                this.users = ((ArrayList<User>) (List<User>) aux);
                input.close();
            } catch (IOException | ClassNotFoundException ex) {
                return false;
            }
        }

        if (!this.users.isEmpty()) {
            for (int i = 0; i < this.users.size(); i++) {
                if (this.users.get(i).getUser().equalsIgnoreCase(user)) {
                    return true;
                }
            }
        }

        return false;
    }

    //actualiza lista en caso de datos nuevos
    @Override
    public void actualizaLista() {
        if (archivo.exists()) {
            try {
                ObjectInputStream input = new ObjectInputStream(new FileInputStream((archivo)));
                Object aux = input.readObject();
                this.users = ((ArrayList<User>) (List<User>) aux);
                input.close();
            } catch (IOException | ClassNotFoundException ex) {

            }
        }

    }

    //obtener usuario
    @Override
    public User getUser(String user) {
        this.actualizaLista();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUser().equalsIgnoreCase(user)) {
                return users.get(i);
            }
        }
        return null;
    }

    //agregar amigo
    @Override
    public void addFriend(User user, User friend) throws FileNotFoundException, IOException, ClassNotFoundException {
 //  System.out.println(sendRequest.getUser()+"\n"+recieveRequest.getUser()+"\n"+newRequest+"\n");
        if (archivo.exists()) {
            ObjectInputStream input = null;
            boolean flag=false;
            int index=-1;
            input = new ObjectInputStream(new FileInputStream((archivo)));
            Object aux = input.readObject();
            this.users = ((ArrayList<User>) (List<User>) aux);
            for (int i = 0; i < this.users.size(); i++) {
                if (this.users.get(i).getUser().equalsIgnoreCase(user.getUser())) {
                   index=i;
                    for (int j = 0; j < this.users.get(i).getFriends().size(); j++) {
                        if (this.users.get(i).getFriends().get(j).getUser().equalsIgnoreCase(friend.getUser())) {
                            flag=true;
                        }
                    }
                }
            }
            if (flag==false & index!=-1) {
                this.users.get(index).getFriends().add(friend);
            }
            
            input.close();
        }

        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream((fileName)));
        output.writeUnshared(this.users);
        output.close();

    }

    //eliminamos la solicitud de amistad
    @Override
    public void removeFriend(String user, String friend) throws FileNotFoundException, IOException, ClassNotFoundException {
        if (archivo.exists()) {
            ObjectInputStream input = null;
            input = new ObjectInputStream(new FileInputStream((archivo)));
            Object aux = input.readObject();
            this.users = ((ArrayList<User>) (List<User>) aux);
            for (int i = 0; i < this.users.size(); i++) {
                if (this.users.get(i).getUser().equalsIgnoreCase(user)) {
                  
                    for (int j = 0; j < this.users.get(i).getFriends().size(); j++) {
                        if (this.users.get(i).getFriends().get(j).getUser().equalsIgnoreCase(friend)) {
                           this.users.get(i).getFriends().remove(j);
                        }
                    }
                }
            }
   
            
            input.close();
        }

        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream((fileName)));
        output.writeUnshared(this.users);
        output.close();

    }

    
}
