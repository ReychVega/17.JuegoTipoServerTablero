package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*@author reych
 *Esta clase llamada Client extiende de la clase Thread 
 *  y se utiliza para representar un cliente que se conecta al servidor
 */
public class Client extends Thread {
/*Atributos de clase
 *receive: Un objeto BufferedReader utilizado para recibir datos del servidor a través del socket.
 *send: Un objeto PrintStream utilizado para enviar datos al servidor a través del socket.
 *socket: El socket utilizado para la comunicación con el servidor.
 *salida: Un objeto ObjectOutputStream utilizado para enviar objetos al servidor a través del socket.
 *entrada: Un objeto ObjectInputStream utilizado para recibir objetos del servidor a través del socket.
 *mensaje: Un arreglo de cadenas utilizado para almacenar mensajes del servidor.
 *smsCliente: Una cadena que almacena el mensaje del cliente.*/
    private BufferedReader receive;
    private PrintStream send;
    private Socket socket;
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private String[] mensaje;
    private String smsCliente;

    
   /*Constructor:
    *El constructor recibe una dirección IP y el número de puerto del servidor al que se desea conectar.
    *Crea un nuevo objeto Socket y se conecta al servidor utilizando la dirección IP y el número de puerto proporcionados.
    *Inicializa los objetos receive y send con los objetos BufferedReader y PrintStream, respectivamente, obtenidos del socket.
    *Llama al método start() para iniciar el hilo del cliente.*/
    public Client(String ip, int socketPortNumber) throws UnknownHostException, IOException {
        this.socket = new Socket(ip, socketPortNumber);
        this.receive = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.send = new PrintStream(this.socket.getOutputStream());
        this.start();
    } // constructor

    public BufferedReader getReceive() {
        return receive;
    }

    public void setReceive(BufferedReader receive) {
        this.receive = receive;
    }

    public PrintStream getSend() {
        return send;
    }

    public void setSend(PrintStream send) {
        this.send = send;
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
        salida = salida;
    }

    public ObjectInputStream getEntrada() {
        return entrada;
    }

    public String getSmsCliente() {
        return smsCliente;
    }

    public void setSmsCliente(String smsCliente) {
        this.smsCliente = smsCliente;
    }

    
    /*Método run():
     *Este método se ejecuta cuando se inicia el hilo del cliente.
     *Dentro de un bucle while, se lee una línea de texto del servidor utilizando el BufferedReader receive y se almacena en la variable lectura.
     *Puede agregar lógica adicional para procesar y responder a los mensajes recibidos del servidor.
     *El bucle se ejecuta de forma indefinida hasta que se interrumpa el hilo.*/
    @Override
    public void run() {
        System.out.println("Cliente Escuchando");
        while (true) {
            try {

                String lectura = this.receive.readLine();

                //cerramos conexiones
                salida.close();
                entrada.close();
                socket.close();

            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }

        } // while
    } // run

} // fin clase

