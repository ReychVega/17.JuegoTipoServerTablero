package Client;

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

    public void closeConnection() {
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

}
