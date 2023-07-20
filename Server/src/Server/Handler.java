package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;

/*@author reych
 *Esta clase llamada Handler extiende de la clase Thread 
 *  y se utiliza para manejar la comunicación con un cliente
 *  en el servidor.
 * Representa el manejo de la comunicación con un cliente en el servidor
 */
public class Handler extends Thread {
/*Variables de instancia:
 *send: Un objeto PrintStream utilizado para enviar datos al cliente a través del socket.
 *receive: Un objeto BufferedReader utilizado para recibir datos del cliente a través del socket.
 *socket: El socket utilizado para la comunicación con el cliente.
 *lectura: Una cadena que almacena los datos leídos del cliente.
 *sesionIniciada: Un booleano que indica si la sesión con el cliente está iniciada o no.
 *salida: Un objeto ObjectOutputStream utilizado para enviar objetos al cliente a través del socket.
 *entrada: Un objeto ObjectInputStream utilizado para recibir objetos del cliente a través del socket.
 *mensaje: Un arreglo de cadenas utilizado para almacenar los mensajes recibidos del cliente.*/ 
    private PrintStream send;
    private BufferedReader receive;
    private Socket socket;
    private String lectura;
    private boolean sesionIniciada;
    public ObjectOutputStream salida;
    public ObjectInputStream entrada;
    private String [] mensaje;
 
    /*El constructor recibe un objeto Socket como parámetro, que representa la conexión establecida con el cliente.
     *Inicializa las variables de instancia socket, send y receive con los objetos proporcionados por el socket.
     *Establece sesionIniciada como true para indicar que la sesión está iniciada.
    */
     public Handler(Socket socket) throws IOException {
        this.socket = socket;
        this.send = new PrintStream(this.socket.getOutputStream());
        this.receive = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    	this.sesionIniciada=true;
    } // constructor
     
     
    /*Método run():
     *Este método se ejecuta cuando se inicia el hilo del manejador (Clase handler).
     *Dentro de un bucle while, se lee una línea de texto del cliente utilizando el BufferedReader receive y se almacena en la variable lectura.
     *Este bucle se ejecuta mientras sesionIniciada sea true.
     *Se puede agregar lógica adicional para procesar y responder a los mensajes recibidos del cliente
     */ 
    @Override
    public void run() {
        try {
            System.out.println(sesionIniciada);
            while (sesionIniciada) {
                    this.lectura = this.receive.readLine();
            } // while

        } catch (IOException e) {
            System.out.println("Cliente cierra el programa");
            //manejar error
        }finally {
        try {
            this.receive.close();
            this.send.close();
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        }
    } // run

} // fin clase 
