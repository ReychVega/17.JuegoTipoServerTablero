package Server;

import Domain.User;
import Utility.Utility;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

/*@author reych
 *Esta clase llamada Handler extiende de la clase Thread 
 *  y se utiliza para manejar la comunicación con un cliente
 *  en el servidor.
 * Representa el manejo de la comunicación con un cliente en el servidor

 * Variables de instancia:
 *send: Un objeto PrintStream utilizado para enviar datos al cliente a través del socket.
 *receive: Un objeto BufferedReader utilizado para recibir datos del cliente a través del socket.
 *socket: El socket utilizado para la comunicación con el cliente.
 *lectura: Una cadena que almacena los datos leídos del cliente.
 *sesionIniciada: Un booleano que indica si la sesión con el cliente está iniciada o no.
 *salida: Un objeto ObjectOutputStream utilizado para enviar objetos al cliente a través del socket.
 *entrada: Un objeto ObjectInputStream utilizado para recibir objetos del cliente a través del socket.
 *mensaje: Un arreglo de cadenas utilizado para almacenar los mensajes recibidos del cliente.*/

public class Handler extends Thread {

    private PrintStream send;
    private BufferedReader receive;
    private Socket socket;
    private String lectura;
    private boolean sesionIniciada;
    public ObjectOutputStream salida;
    public ObjectInputStream entrada;
    private String[] mensaje;

    //atributos db Data
    private ArrayList<User> usersList;
    private Utility utility;

    /*El constructor recibe un objeto Socket como parámetro, que representa la conexión establecida con el cliente.
     *Inicializa las variables de instancia socket, send y receive con los objetos proporcionados por el socket.
     *Establece sesionIniciada como true para indicar que la sesión está iniciada.
     */
    public Handler(Socket socket) throws IOException {
        this.socket = socket;
        this.salida = new ObjectOutputStream(this.socket.getOutputStream());
        this.send = new PrintStream(this.socket.getOutputStream());
        this.receive = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.sesionIniciada = true;
        initInstances();
    } // constructor

    private void initInstances() {
        utility = new Utility();
        usersList = DBget.DBget.getUsers("");
    }

    /*Método run():
     *Este método se ejecuta cuando se inicia el hilo del manejador (Clase handler).
     *Este bucle se ejecuta mientras sesionIniciada sea true.
     */
// Dentro de la clase Handler
    @Override
    public void run() {
        try {
            entrada = new ObjectInputStream(socket.getInputStream()); // Inicializar el ObjectInputStream

            while (sesionIniciada) {
                Object receivedObject = entrada.readObject();

                //Registro
                if (receivedObject instanceof User) {
                    User userRegister = (User) receivedObject;

                    //verificamos que no exista un usuario igual
                    if (utility.search(usersList, userRegister.getUser()) == false) {

                        DBsave.DBsave.addUser("user, password",
                                ("'" + userRegister.getUser() + "', '" + userRegister.getPassword() + "'"));

                        sendMessageToClient("Registered User.");
                    } else {
                        sendMessageToClient("Existing user");
                    }
                }

            }

        } catch (IOException e) {
            System.out.println("Cliente cierra el programa");
        } catch (ClassNotFoundException ex) {
            System.out.println("ex=" + ex.getMessage());
        } finally {
            try {
                if (entrada != null) {
                    entrada.close();
                }
                if (salida != null) {
                    salida.close();
                }
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

   private void sendMessageToClient(String message) {
    try {
        this.salida.writeObject(message);
        this.salida.flush();
    } catch (IOException ex) {
        System.out.println("Error al enviar el mensaje al cliente: " + ex.getMessage());
    }
}
    
} // fin clase 
