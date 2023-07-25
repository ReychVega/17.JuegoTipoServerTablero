package Client;

import Domain.FriendRequest;
import Domain.Request;
import Domain.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 *@author reych
 *Atributos
 *socket: El socket utilizado para la conexión con el servidor
 *salida: Flujo de salida de objetos hacia el servidor
 *entrada: Flujo de entrada de objetos desde el servidor
 *send: Flujo de salida de texto hacia el servidor
 */
public class Client {
    
    private Socket socket;
    private ObjectOutputStream salida; 
    private ObjectInputStream entrada; 
    private String smsCliente; 
    private PrintStream send;

    public Client(String ip, int socketPortNumber) throws UnknownHostException, IOException {
        // Se crea el socket y se establece la conexión con el servidor
        this.socket = new Socket(ip, socketPortNumber);
       
        this.salida = new ObjectOutputStream(this.socket.getOutputStream());
       
        // Se inicializa el flujo de salida de texto hacia el servidor
        this.send = new PrintStream(this.socket.getOutputStream());

        this.entrada = new ObjectInputStream(this.socket.getInputStream());
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ObjectOutputStream getSalida() {
        return salida;
    }

    public void setSalida(ObjectOutputStream salida) {
        this.salida = salida;
    }

    public ObjectInputStream getEntrada() {
        return entrada;
    }

    public void setEntrada(ObjectInputStream entrada) {
        this.entrada = entrada;
    }

    public PrintStream getSend() {
        return send;
    }

    public void setSend(PrintStream send) {
        this.send = send;
    }    
    
    public String getSmsCliente() {
        return smsCliente;
    }

    public void setSmsCliente(String smsCliente) {
        this.smsCliente = smsCliente;
    }
    
   
    // Método para enviar un objeto User al servidor
    public void sendUserToServer(User user) {
        try {
            this.salida.writeObject(user);
        } catch (IOException ex) {
            System.out.println("Internal error");
        }
    }
    
    // Método para enviar un obj. tipo request al servidor
    public void sendRequestToServer(Request request) {
        try {
            this.salida.writeObject(request);
        } catch (IOException ex) {
            System.out.println("Internal error"+ex.getMessage());
        }
    }

    // Método para enviar un string User al servidor
    public void sendMessageToServer(String message) {
        try {
            this.salida.writeObject(message);
            this.salida.flush();
        } catch (IOException ex) {
            System.out.println("Error al enviar el mensaje al server: " + ex.getMessage());
        }
    }
    
    // Método para enviar una solicitud User al servidor
    public void sendFriendRequestToServer( FriendRequest request) {
        try {
            this.salida.writeObject(request);
            this.salida.flush();
        } catch (IOException ex) {
            System.out.println("Error al enviar el mensaje al server: " + ex.getMessage());
        }
    }
    
    // Método para recibir un mensaje del servidor como texto
    public String receiveMessageFromServer() {
        try {
            String s=null;
            Object sms=this.entrada.readObject();
            if (sms!=null) {
                s=(String)sms;
            }
            return s;
        } catch (IOException ex) {
            System.out.println("Clase Client. Error. Conexion cerrada o internet."+ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Clase Client. Error de conversion de objeto. No es string o es nulo "+ex.getMessage());
        }
    return null;
    }

    public void closeConnection(){
        try {
            if (this.entrada != null) {
                this.entrada.close();
            }
            if (this.salida != null) {
                this.salida.close();
            }
            if (this.socket != null) {
                this.socket.close();
            }
        } catch (IOException e) {
            // Si ocurre un error al cerrar la conexión, se captura
            System.out.println("Clase Client. Error al cerrar la conexion");          
        }
    }  
    
    // Método para enviar un mensaje de cierre al servidor
    public void sendCloseMessageToServer(String message) {
        try {
            this.salida.writeObject(message);
            this.salida.flush();
        } catch (IOException ex) {
            System.out.println("Error al enviar el mensaje al servidor: " + ex.getMessage());
        }
    }

}
